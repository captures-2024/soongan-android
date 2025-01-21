package com.captures2024.soongan.feature.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.members.GetMemberInfoUseCase
import com.captures2024.soongan.core.domain.usecase.members.IsVerifiedNicknameUseCase
import com.captures2024.soongan.core.domain.usecase.members.PatchProfileUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetMyGalleryUseCase
import com.captures2024.soongan.core.model.UserProfile
import com.captures2024.soongan.core.viewmodel.model.PaginationStatus
import com.captures2024.soongan.feature.profile.state.profile.EditingState
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent.BottomSheetI
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent.EditI
import com.captures2024.soongan.feature.profile.state.profile.ProfileIntent.ProfileI
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect.BottomSheetSE
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect.EditSE
import com.captures2024.soongan.feature.profile.state.profile.ProfileSideEffect.ProfileSE
import com.captures2024.soongan.feature.profile.state.profile.ProfileUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getMemberInfoUseCase: GetMemberInfoUseCase,
    private val getMyGalleryUseCase: GetMyGalleryUseCase,
    private val patchProfileUseCase: PatchProfileUseCase,
    private val isVerifiedNicknameUseCase: IsVerifiedNicknameUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ProfileUIState, ProfileSideEffect, ProfileIntent>(savedStateHandle = savedStateHandle) {

    init {
        intent(ProfileI.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): ProfileUIState {
        return ProfileUIState()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
            message = "handleClientException"
        )
    }

    override suspend fun handleIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileI -> handleProfileIntent(intent)

            is BottomSheetI -> handleBottomSheetIntent(intent)

            is EditI -> handleEditIntent(intent)
        }
    }

    private fun handleProfileIntent(intent: ProfileI) {
        when (intent) {
            ProfileI.Init -> initSyncData()

            ProfileI.RefreshMyGallery -> fetchProfileGallery(page = 0, isRefreshing = true)

            ProfileI.LoadNextPage -> fetchProfileGallery(page = currentState.nextPage)

            ProfileI.OnClickMenu -> onClickMenu()

            ProfileI.OnClickNotification -> onClickNotification()

            is ProfileI.OnClickPhoto -> postSideEffect(ProfileSE.NavigateToHomePost(intent.postId))

            ProfileI.OnClickRegistrationText -> postSideEffect(ProfileSE.NavigateToRegistrationPost)
        }
    }

    private fun handleBottomSheetIntent(intent: BottomSheetI) {
        when (intent) {
            BottomSheetI.OnClickEdit -> onClickEdit()

            BottomSheetI.OnClickFAQ -> TODO()

            BottomSheetI.OnClickNotificationSetting -> TODO()

            BottomSheetI.OnClickSignOut -> TODO()

            BottomSheetI.OnClickTermsAndPolicy -> TODO()

            BottomSheetI.OnClickWithdraw -> TODO()

            BottomSheetI.OnCloseBottomSheet -> onCloseBottomSheet()
        }
    }

    private fun handleEditIntent(intent: EditI) {
        when (intent) {
            EditI.OnBackPressed -> postSideEffect(EditSE.NavigateToBack)

            EditI.OnClickEditButton -> onClickEditButton()

            EditI.OnClickProfileImage -> onClickProfileImage()

            EditI.OnClickDefaultProfileImage -> onClickDefaultProfileImage()

            EditI.OpenPhotoPicker -> openPhotoPicker()

            EditI.OnCloseEditBottomSheet -> onCloseEditBottomSheet()

            is EditI.OnProfileImageChanged -> onProfileImageChanged(intent)

            is EditI.OnNicknameChanged -> onNicknameChanged(intent)

            is EditI.OnIntroductionChanged -> onIntroductionChanged(intent)
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

    private fun onClickMenu() {
        reduce {
            copy(isOpenBottomSheet = true)
        }
    }

    private fun onClickNotification() {
        reduce {
            copy(hasNotification = false)
        }

        postSideEffect(ProfileSE.NavigateToNotification)
    }


    /** Handle ProfileMenuBottomSheet **/
    private fun onClickEdit() {
        reduce {
            copy(
                editingState = EditingState(editingProfile = currentState.userProfile),
                isOpenBottomSheet = false
            )
        }

        postSideEffect(BottomSheetSE.NavigateToEditProfile)

        launch {
            delay(100)
            reduce {
                copy(
                    isOpenBottomSheet = true
                )
            }
        }
    }

    private fun onCloseBottomSheet() {
        reduce {
            copy(isOpenBottomSheet = false)
        }
    }


    /** Handle EditScreen **/
    private fun onClickProfileImage() {
        reduce {
            copy(
                isOpenProfileImageBottomSheet = true
            )
        }
    }

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
        postSideEffect(EditSE.OpenMediaPicker)
    }

    private fun onCloseEditBottomSheet() {
        reduce {
            copy(
                isOpenProfileImageBottomSheet = false
            )
        }
    }

    private fun onProfileImageChanged(intent: EditI.OnProfileImageChanged) {
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

    private fun onNicknameChanged(intent: EditI.OnNicknameChanged) {
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

    private fun onIntroductionChanged(intent: EditI.OnIntroductionChanged) {
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

                postSideEffect(EditSE.NavigateToBack)
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