package com.captures2024.soongan.core.viewmodel.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.members.GetMemberInfoUseCase
import com.captures2024.soongan.core.domain.usecase.members.IsVerifiedNicknameUseCase
import com.captures2024.soongan.core.domain.usecase.members.PatchProfileUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetMyGalleryUseCase
import com.captures2024.soongan.core.model.UserProfile
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.viewmodel.model.PaginationStatus
import com.captures2024.soongan.core.viewmodel.model.profile.EditingState
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtMenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getMemberInfoUseCase: GetMemberInfoUseCase,
    private val getMyGalleryUseCase: GetMyGalleryUseCase,
    private val patchProfileUseCase: PatchProfileUseCase,
    private val isVerifiedNicknameUseCase: IsVerifiedNicknameUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ProfileViewModel.State, ProfileViewModel.Effect, ProfileViewModel.Intent>(savedStateHandle = savedStateHandle) {

    data class State(
        val isLoading: Boolean = false,
        val userProfile: UserProfile = UserProfile(),
        val editingState: EditingState = EditingState(),
        val isRefreshing: Boolean = false,
        val paginationStatus: PaginationStatus = PaginationStatus.INACTIVE,
        val myPosts: List<GalleryPostDto> = emptyList(),
        val nextPage: Int = 0,
        val hasNextPage: Boolean = false,
        val hasNotification: Boolean = false,
        val isOpenBottomSheet: Boolean = false,
        val isOpenProfileImageBottomSheet: Boolean = false,
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("userProfile", userProfile.toString()),
            LogElementArgument("editingState", editingState.toString()),
            LogElementArgument("isRefreshing", isRefreshing.toString()),
            LogElementArgument("paginationStatus", paginationStatus.toString()),
            LogElementArgument("myPosts", myPosts.toString()),
            LogElementArgument("nextPage", nextPage.toString()),
            LogElementArgument("hasNextPage", hasNextPage.toString()),
            LogElementArgument("hasNotification", hasNotification.toString()),
            LogElementArgument("isOpenBottomSheet", isOpenBottomSheet.toString()),
            LogElementArgument("isOpenProfileImageBottomSheet", isOpenProfileImageBottomSheet.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

        sealed interface Profile : Effect {

            data object NavigateToNotification : Profile

            data class NavigateToHomePost(
                val postId: Int,
            ) : Profile

            data object NavigateToRegistrationPost : Profile

            data object NavigateToEditProfile : Profile
        }

        sealed interface Edit : Effect {

            data object NavigateToBack : Edit

            data object OpenMediaPicker : Edit
        }
    }

    sealed interface Intent : UIIntent {

        sealed interface Profile : Intent {

            data object Init : Profile

            data object RefreshMyGallery : Profile

            data object LoadNextPage : Profile

            data class OnClickPhoto(val postId: Int) : Profile

            data object OnClickRegistrationText : Profile

            data object OnClickNotification : Profile

            data object OnClickMenu : Profile

            data class OnMenuItemClicked(
                val menuItem: ProfileBtmShtMenuItem,
            ) : Profile

            data object OnCloseBottomSheet : Profile
        }

        sealed interface Edit : Intent {

            data object OnBackPressed : Edit

            data object OnClickProfileImage : Edit

            data object OnClickDefaultProfileImage : Edit

            data object OpenPhotoPicker : Edit

            data object OnCloseEditBottomSheet : Edit

            data class OnProfileImageChanged(
                val newProfileImage: String,
            ) : Edit

            data class OnNicknameChanged(
                val newNickname: String,
            ) : Edit

            data class OnIntroductionChanged(
                val newIntroduction: String,
            ) : Edit

            data object OnClickEditButton : Edit
        }
    }

    init {
        intent(Intent.Profile.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
            message = "handleClientException"
        )
    }

    override suspend fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Profile -> handleProfileIntent(intent)

            is Intent.Edit -> handleEditIntent(intent)
        }
    }

    private fun handleProfileIntent(intent: Intent.Profile) {
        when (intent) {
            Intent.Profile.Init -> initSyncData()

            Intent.Profile.RefreshMyGallery -> fetchProfileGallery(page = 0, isRefreshing = true)

            Intent.Profile.LoadNextPage -> fetchProfileGallery(page = currentState.nextPage)

            Intent.Profile.OnClickMenu -> reduce { copy(isOpenBottomSheet = true) }

            Intent.Profile.OnClickNotification -> onClickNotification()

            is Intent.Profile.OnClickPhoto -> postSideEffect(Effect.Profile.NavigateToHomePost(intent.postId))

            Intent.Profile.OnClickRegistrationText -> postSideEffect(Effect.Profile.NavigateToRegistrationPost)

            Intent.Profile.OnCloseBottomSheet -> reduce { copy(isOpenBottomSheet = false) }

            is Intent.Profile.OnMenuItemClicked -> onMenuItemClicked(intent)
        }
    }

    private fun handleEditIntent(intent: Intent.Edit) {
        when (intent) {
            Intent.Edit.OnBackPressed -> postSideEffect(Effect.Edit.NavigateToBack)

            Intent.Edit.OnClickEditButton -> onClickEditButton()

            Intent.Edit.OnClickProfileImage -> reduce { copy(isOpenProfileImageBottomSheet = true) }

            Intent.Edit.OnClickDefaultProfileImage -> onClickDefaultProfileImage()

            Intent.Edit.OpenPhotoPicker -> openPhotoPicker()

            Intent.Edit.OnCloseEditBottomSheet -> onCloseEditBottomSheet()

            is Intent.Edit.OnProfileImageChanged -> onProfileImageChanged(intent)

            is Intent.Edit.OnNicknameChanged -> onNicknameChanged(intent)

            is Intent.Edit.OnIntroductionChanged -> onIntroductionChanged(intent)
        }
    }


    /** Handle ProfileScreen **/
    private fun initSyncData() {
        fetchUserProfile()
        fetchProfileGallery(page = 0)
    }

    private fun fetchUserProfile() = launch {
        val memberInfo = getMemberInfoUseCase().getOrNull()

        memberInfo?.let {
            val userProfile = UserProfile(
                nickname = it.nickname ?: "user1",
                selfIntroduction = it.selfIntroduction ?: "본인을 소개해주세요",
                profileImageUrl = it.profileImageUrl
            )

            reduce {
                copy(
                    userProfile = userProfile,
                    editingState = EditingState(userProfile)
                )
            }
        }
    }

    private fun setUpLoading(isInitPage: Boolean, isRefreshing: Boolean) {
        if (isInitPage) {
            reduce {
                copy(
                    isRefreshing = isRefreshing,
                    paginationStatus = PaginationStatus.LOADING,
                    myPosts = emptyList()
                )
            }
        } else {
            reduce {
                copy(
                    paginationStatus = PaginationStatus.PAGINATING,
                )
            }
        }
    }

    private fun fetchProfileGallery(page: Int, isRefreshing: Boolean = false) {
        when (currentState.paginationStatus) {
            PaginationStatus.LOADING, PaginationStatus.PAGINATING -> return

            else -> Unit

        }

        val isInitPage = (page == 0)

        setUpLoading(isInitPage = isInitPage, isRefreshing = isRefreshing)

        launch {
            delay(1_500)

            val myGalleryDto = getMyGalleryUseCase(
                params = GetMyGalleryUseCase.Params(
                    page = page,
                    pageSize = PAGE_SIZE,
                )
            ).getOrNull()


            if (myGalleryDto == null) {
                analyticsHelper.d(message = "myGalleryDto is null")

                reduce {
                    copy(
                        isRefreshing = false,
                        paginationStatus = PaginationStatus.ERROR
                    )
                }

                return@launch
            }

            analyticsHelper.d(message = "myGalleryDto is ${myGalleryDto.posts}")
            reduce {
                copy(
                    isRefreshing = false,
                    paginationStatus = when {
                        !myGalleryDto.hasNext -> PaginationStatus.EXHAUST
                        myGalleryDto.posts.isEmpty() -> PaginationStatus.EMPTY
                        else -> PaginationStatus.INACTIVE
                    },
                    myPosts = myPosts + myGalleryDto.posts,
                    nextPage = page + 1,
                    hasNextPage = myGalleryDto.hasNext
                )
            }
        }
    }

    private fun onClickNotification() {
        reduce {
            copy(hasNotification = false)
        }

        postSideEffect(Effect.Profile.NavigateToNotification)
    }

    private fun onMenuItemClicked(intent : Intent.Profile.OnMenuItemClicked) {
        intent.menuItem
        reduce {
            copy(
                editingState = EditingState(editingProfile = currentState.userProfile),
                isOpenBottomSheet = false
            )
        }

        postSideEffect(Effect.Profile.NavigateToEditProfile)

        launch {
            delay(100)
            reduce {
                copy(
                    isOpenBottomSheet = true
                )
            }
        }
    }


    /** Handle EditScreen **/
    private fun onClickDefaultProfileImage() {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        profileImageUrl = null
                    )
                )
            )
        }
        onCloseEditBottomSheet()
        updateEditableState()
    }

    private fun openPhotoPicker() {
        onCloseEditBottomSheet()
        postSideEffect(Effect.Edit.OpenMediaPicker)
    }

    private fun onCloseEditBottomSheet() {
        reduce {
            copy(
                isOpenProfileImageBottomSheet = false
            )
        }
    }

    private fun onProfileImageChanged(intent: Intent.Edit.OnProfileImageChanged) {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        profileImageUrl = intent.newProfileImage
                    )
                )
            )
        }

        updateEditableState()
    }

    private fun onNicknameChanged(intent: Intent.Edit.OnNicknameChanged) {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        nickname = intent.newNickname
                    ),
                    isDuplicatedNickname = false
                )
            )
        }

        updateEditableState()
    }

    private fun onIntroductionChanged(intent: Intent.Edit.OnIntroductionChanged) {
        reduce {
            copy(
                editingState = editingState.copy(
                    editingProfile = editingState.editingProfile.copy(
                        selfIntroduction = intent.newIntroduction
                    )
                )
            )
        }

        updateEditableState()
    }

    private fun updateEditableState() {
        val isEditable = (currentState.userProfile != currentState.editingState.editingProfile)

        reduce {
            copy(
                editingState = editingState.copy(
                    isEditable = isEditable
                )
            )
        }
    }

    private fun onClickEditButton() = launch {
        val editedProfile = currentState.editingState.editingProfile
        val isAllowNickname = isVerifiedNicknameUseCase(editedProfile.nickname).getOrNull()

        analyticsHelper.d(message = "isAllowNickname : $isAllowNickname")

        when (isAllowNickname) {
            true -> {
                val isPatchedProfile = patchProfileUseCase(
                    nickname = editedProfile.nickname,
                    selfIntroduction = editedProfile.selfIntroduction,
                    profileImage = editedProfile.profileImageUrl,
                ).onSuccess {
                    reduce {
                        copy(
                            userProfile = editedProfile,
                            isOpenBottomSheet = false
                        )
                    }
                }

                analyticsHelper.d(message = "Patch Profile result : $isPatchedProfile")

                postSideEffect(Effect.Edit.NavigateToBack)
            }

            false -> {
                reduce {
                    copy(
                        editingState = editingState.copy(
                            isEditable = false,
                            isDuplicatedNickname = true
                        )
                    )
                }
            }

            null -> {
                reduce {
                    copy(
                        editingState = editingState.copy(
                            isEditable = false
                        )
                    )
                }

//                Toast.makeText(context, "not statusCode 200", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}