package com.captures2024.soongan.core.viewmodel.state

import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.viewmodel.utils.AppRootRoute

data class AppRootUIState(
    val isLoading: Boolean = false,
    val rootRouteState: AppRootRoute = AppRootRoute.LANDING,
    private val memberInfo: UserInfoDto = UserInfoDto.defaultBuilder(),
) : UIState {

    override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
        LogElementArgument("isLoading", isLoading.toString()),
        LogElementArgument("rootRouteState", rootRouteState.toString()),
        LogElementArgument("memberInfo", memberInfo.toString()),
    )

    fun isGuestMode(): Boolean = memberInfo.email.isEmpty()

    fun getNickname(): String = memberInfo.nickname ?: ""

    fun patchMemberInfo(
        nickname: String,
        birthYear: Int,
    ): UserInfoDto = memberInfo.copy(
        nickname = nickname,
        birthYear = birthYear,
    )
}