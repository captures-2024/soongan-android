package com.captures2024.soongan.feature.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconArrowRightFromBracket
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFillPersonRunning
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillCircleQuestion
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillFile
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillGear
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillUser
import com.captures2024.soongan.core.designsystem.theme.SGColor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ProfileBtmShtViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,

    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ProfileBtmShtViewModel.State, ProfileBtmShtViewModel.Effect, ProfileBtmShtViewModel.Intent>(
    savedStateHandle = savedStateHandle
) {

    internal data class State(
        val isLoading: Boolean = false,
        val depthStatus: ProfileBtmShtDepthStatus = ProfileBtmShtDepthStatus.Idle,
        val pushSettings: PushSettingState = PushSettingState(),
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("depthStatus", depthStatus.toString()),
            LogElementArgument("pushSettings", pushSettings.toString()),
        )
    }

    internal sealed interface Effect : UISideEffect {
        data object SendRequestNavigateToEditProfile : Effect

        data object CloseBottomSheet : Effect

        data object NavigateToHome : Effect
    }

    sealed interface Intent : UIIntent {
        data class OnClickMenuItem(
            val item: ProfileBtmShtMenuItem,
        ) : Intent

        data object OnBackIdle : Intent

        data class OnCheckProcess(
            val type: ProfileBtmShtCheckType,
        ) : Intent

        data object OnDoneProcess : Intent

        data class OnPushSettingChanged(
            val type: PushSettingType,
        ) : Intent

        data object OnCloseBottomSheet : Intent
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
            is Intent.OnClickMenuItem -> handleClickContentItem(intent)

            is Intent.OnBackIdle -> reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Idle) }

            is Intent.OnCheckProcess -> onCheckProcess(intent)

            is Intent.OnDoneProcess -> postSideEffect(Effect.NavigateToHome)

            is Intent.OnPushSettingChanged -> onPushSettingChanged(intent)

            is Intent.OnCloseBottomSheet -> onCloseBottomSheet()
        }
    }

    private fun handleClickContentItem(intent: Intent.OnClickMenuItem) {
        when (intent.item) {
            ProfileBtmShtMenuItem.EDIT -> postSideEffect(Effect.SendRequestNavigateToEditProfile)

            ProfileBtmShtMenuItem.FAQ -> TODO()

            ProfileBtmShtMenuItem.PUSH -> reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Push) }

            ProfileBtmShtMenuItem.TERMS_AND_POLICY -> TODO()

            ProfileBtmShtMenuItem.SIGN_OUT -> reduce { copy(depthStatus = ProfileBtmShtDepthStatus.SignOut.Check) }

            ProfileBtmShtMenuItem.WITHDRAW -> reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Withdraw.Check) }
        }
    }

    private fun onCheckProcess(intent: Intent.OnCheckProcess) {
        when (intent.type) {
            ProfileBtmShtCheckType.SIGN_OUT -> {
                // 로그아웃 useCase
//                if(result == null)
//                    reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Error) }


                reduce { copy(depthStatus = ProfileBtmShtDepthStatus.SignOut.Done) }
            }

            ProfileBtmShtCheckType.WITHDRAW -> {
                // 회원탈퇴 useCase
//                if(result == null)
//                    reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Error) }


                reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Withdraw.Done) }
            }
        }
    }

    private fun onPushSettingChanged(intent: Intent.OnPushSettingChanged) {
        val pushSettings = currentState.pushSettings

        reduce {
            copy(pushSettings = pushSettings.switch(intent.type))
        }
    }

    private fun onCloseBottomSheet() {
        postSideEffect(Effect.CloseBottomSheet)

        reduce {
            copy(
                depthStatus = ProfileBtmShtDepthStatus.Idle
            )
        }
    }
}


/* 각 파일로 move 예정 */

enum class ProfileBtmShtMenuItem {
    EDIT, FAQ, PUSH, TERMS_AND_POLICY, WITHDRAW, SIGN_OUT
}

fun ProfileBtmShtMenuItem.textId() =
    when (this) {
        ProfileBtmShtMenuItem.EDIT -> R.string.profile_menu_bottom_sheet_edit_title
        ProfileBtmShtMenuItem.FAQ -> R.string.profile_menu_bottom_sheet_faq_title
        ProfileBtmShtMenuItem.PUSH -> R.string.profile_menu_bottom_sheet_notification_title
        ProfileBtmShtMenuItem.TERMS_AND_POLICY -> R.string.profile_menu_bottom_sheet_temp_and_policy_title
        ProfileBtmShtMenuItem.WITHDRAW -> R.string.profile_menu_bottom_sheet_withdraw_title
        ProfileBtmShtMenuItem.SIGN_OUT -> R.string.profile_menu_bottom_sheet_sign_out_title
    }

fun ProfileBtmShtMenuItem.color() =
    if (this == ProfileBtmShtMenuItem.WITHDRAW) SGColor.negative else SGColor.black

fun ProfileBtmShtMenuItem.icon() =
    when (this) {
        ProfileBtmShtMenuItem.EDIT -> MyIconPack.IconNonFillUser
        ProfileBtmShtMenuItem.FAQ -> MyIconPack.IconNonFillCircleQuestion
        ProfileBtmShtMenuItem.PUSH -> MyIconPack.IconNonFillGear
        ProfileBtmShtMenuItem.TERMS_AND_POLICY -> MyIconPack.IconNonFillFile
        ProfileBtmShtMenuItem.WITHDRAW -> MyIconPack.IconFillPersonRunning
        ProfileBtmShtMenuItem.SIGN_OUT -> MyIconPack.IconArrowRightFromBracket
    }

sealed interface ProfileBtmShtDepthStatus {
    data object Idle : ProfileBtmShtDepthStatus

    data object Push : ProfileBtmShtDepthStatus

    sealed interface SignOut : ProfileBtmShtDepthStatus {
        data object Check : SignOut

        data object Done : SignOut
    }

    sealed interface Withdraw : ProfileBtmShtDepthStatus {
        data object Check : Withdraw

        data object Done : Withdraw
    }

    data object Error: ProfileBtmShtDepthStatus
}

enum class ProfileBtmShtCheckType {
    SIGN_OUT, WITHDRAW
}

data class PushSettingState(
    val all: Boolean = false,
    val contest: Boolean = false,
    val activity: Boolean = false,
    val notice: Boolean = false,
) {
    fun switch(type: PushSettingType): PushSettingState {
        return when (type) {
            PushSettingType.ALL -> copy(all = !all)

            PushSettingType.CONTEST -> copy(contest = !contest)

            PushSettingType.ACTIVITY -> copy(activity = !activity)

            PushSettingType.NOTICE -> copy(notice = !notice)
        }
    }
}

enum class PushSettingType {
    ALL, CONTEST, ACTIVITY, NOTICE
}