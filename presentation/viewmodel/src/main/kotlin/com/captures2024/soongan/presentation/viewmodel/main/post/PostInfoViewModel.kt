package com.captures2024.soongan.presentation.viewmodel.main.post

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.core.navigator.screen.main.post.PostInfoNavigator
import com.captures2024.soongan.domain.usecase.contest.DeletePostLikeUseCase
import com.captures2024.soongan.domain.usecase.contest.DeletePostUseCase
import com.captures2024.soongan.domain.usecase.contest.GetPostInfoUseCase
import com.captures2024.soongan.domain.usecase.contest.PutPostLikeUseCase
import com.captures2024.soongan.domain.usecase.member.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.PostSingleButtonDialogUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import com.captures2024.soongan.presentation.viewmodel.model.ContestType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostInfoViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val postSingleButtonDialogUseCase: PostSingleButtonDialogUseCase,
    private val currentMemberFlowUseCase: GetCurrentMemberFlowUseCase,
    private val getPostInfoUseCase: GetPostInfoUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val putPostLikeUseCase: PutPostLikeUseCase,
    private val deletePostLikeUseCase: DeletePostLikeUseCase,
) : BaseViewModel<PostInfoViewModel.State, PostInfoViewModel.Effect, PostInfoViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {
    data class State(
        val postId: Long,
        val postInfo: PostInfoDto?,
        val isShowMenuBottomSheet: Boolean,
        val isShowReportBottomSheet: Boolean,
        internal val currentMemberNickname: String?,
    ) : UIState {
        val isMyPost: Boolean
            get() = postInfo?.nickname == currentMemberNickname
    }

    sealed interface Effect : UISideEffect {

        data object NavigateToBack : Effect

        data class NavigateToEditPost(
            val postId: Long,
            val url: String,
            val title: String,
        ) : Effect

        data class NavigateToImageViewer(
            val url: String,
        ) : Effect
    }

    sealed interface Intent : UIIntent {
        data object Init : Intent

        data object OnResumeView : Intent

        data object OnClickBack : Intent

        data object OnClickMenu : Intent

        data object OnClickHeart : Intent

        data object OnClickPhoto : Intent

        data object OnDismissRequestMenuBottomSheet : Intent

        data object OnClickDelete : Intent

        data object OnClickEditPost : Intent

        data object OnClickReport : Intent

        data object OnDismissRequestReportBottomSheet : Intent

        data object OnDoneReport : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val route = savedStateHandle.toRoute<PostInfoNavigator>()

        return State(
            postId = route.id,
            postInfo = null,
            currentMemberNickname = null,
            isShowMenuBottomSheet = false,
            isShowReportBottomSheet = false,
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }
            is Intent.OnResumeView -> launch { handleOnResumeView() }
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnClickMenu -> blockGuestModeLogic { handleOnClickMenu() }
            is Intent.OnClickHeart -> blockGuestModeLogic {
                loadingLaunch { handleOnClickHeart() }
            }
            is Intent.OnClickPhoto -> handleOnClickPhoto()
            is Intent.OnDismissRequestMenuBottomSheet -> handleOnDismissRequestMenuBottomSheet()
            is Intent.OnClickDelete -> loadingLaunch { handleOnClickDelete() }
            is Intent.OnClickEditPost -> handleOnClickEditPost()
            is Intent.OnClickReport -> handleOnClickReport()
            is Intent.OnDismissRequestReportBottomSheet -> handleOnDismissRequestReportBottomSheet()
            is Intent.OnDoneReport -> handleOnDoneReport()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    private suspend fun handleInit() {
        launch { collectMemberInfo() }

        fetchInitData()
    }

    private suspend fun handleOnResumeView() {
        fetchInitData()
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnClickMenu() {
        showMenuBottomSheet()
    }

    private suspend fun handleOnClickHeart() {
        val postInfo = currentState.postInfo ?: return

        val result = when (postInfo.isLiked) {
            true -> deletePostLikeUseCase(
                postId = currentState.postId,
                contestType = ContestType.WEEKLY.name,
            ).getOrNull()

            false -> putPostLikeUseCase(
                postId = currentState.postId,
                contestType = ContestType.WEEKLY.name,
            ).getOrNull()
        }

        if (result == null) {
            postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
            return
        }

        reduce {
            copy(
                postInfo = postInfo.copy(
                    isLiked = !postInfo.isLiked,
                    likeCount = result.likeCount,
                ),
            )
        }
    }

    private fun handleOnClickPhoto() {
        postSideEffect(
            sideEffect = Effect.NavigateToImageViewer(
                url = currentState.postInfo?.imageUrl ?: AppConst.EMPTY_STRING,
            ),
        )
    }

    private fun handleOnDismissRequestMenuBottomSheet() {
        dismissMenuBottomSheet()
    }

    private suspend fun handleOnClickDelete() {
        val postId = currentState.postId

        val result = deletePostUseCase(
            postId = postId,
        ).getOrNull()

        if (result == null) {
            postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
            return
        }

        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnClickEditPost() {
        val postInfo = currentState.postInfo ?: return

        postSideEffect(
            sideEffect = Effect.NavigateToEditPost(
                postId = postInfo.postId,
                url = postInfo.imageUrl,
                title = postInfo.title,
            ),
        )
    }

    private fun handleOnClickReport() {
        showReportBottomSheet()
    }

    private fun handleOnDismissRequestReportBottomSheet() {
        dismissReportBottomSheet()
    }

    private fun handleOnDoneReport() {
        postSideEffect(Effect.NavigateToBack)
    }

    private suspend fun collectMemberInfo() {
        currentMemberFlowUseCase().collect { currentMember ->
            reduce {
                copy(
                    currentMemberNickname = currentMember?.nickname,
                )
            }
        }
    }

    private suspend fun fetchInitData() {
        getRemotePostInfo()
    }

    private suspend fun getRemotePostInfo() {
        val postInfo = getPostInfoUseCase(
            postId = currentState.postId,
        ).getOrNull()

        if (postInfo == null) {
            postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
        }

        reduce {
            copy(
                postInfo = postInfo,
            )
        }
    }

    private fun showMenuBottomSheet() {
        reduce {
            copy(
                isShowMenuBottomSheet = true,
            )
        }
    }

    private fun dismissMenuBottomSheet() {
        reduce {
            copy(
                isShowMenuBottomSheet = false,
            )
        }
    }

    private fun showReportBottomSheet() {
        reduce {
            copy(
                isShowReportBottomSheet = true,
            )
        }
    }

    private fun dismissReportBottomSheet() {
        reduce {
            copy(
                isShowReportBottomSheet = false,
            )
        }
    }
}
