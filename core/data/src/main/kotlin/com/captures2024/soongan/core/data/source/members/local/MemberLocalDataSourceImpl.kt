package com.captures2024.soongan.core.data.source.members.local

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.ReportHistoryDto
import com.captures2024.soongan.core.model.dto.UserInfoDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MemberLocalDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
) : MemberLocalDataSource {

    private val _loginInfo: MutableStateFlow<UserInfoDto?> = MutableStateFlow(null)
    override val loginInfo: StateFlow<UserInfoDto?>
        get() = _loginInfo.asStateFlow()

    init {
        analyticsHelper.d { "MemberLocalDataSource::init" }
    }

    override fun updateUserInfo(userInfoDto: UserInfoDto) {
        analyticsHelper.d { "updateUserInfo - userInfoDto: $userInfoDto" }

        _loginInfo.update { userInfoDto }
    }

    override fun updateUserInfo(birthYear: Int) {
        analyticsHelper.d { "updateUserInfo - birthYear: $birthYear" }

        _loginInfo.update {
            it?.copy(
                birthYear = birthYear,
            )
        }
    }

    override fun updateUserInfo(
        nickname: String?,
        selfIntroduction: String?,
        profileImageUrl: String?,
    ) {
        analyticsHelper.d { "updateUserInfo - nickname: $nickname, selfIntroduction: $selfIntroduction, profileImageUrl: $profileImageUrl" }

        _loginInfo.update {
            it?.copy(
                nickname = nickname,
                selfIntroduction = selfIntroduction,
                profileImageUrl = profileImageUrl,
            )
        }
    }

    override fun updateUserInfo(histories: List<ReportHistoryDto>) {
        analyticsHelper.d { "updateUserInfo - histories: $histories" }

        _loginInfo.update { it?.copy(reportHistories = histories) }
    }

    override fun clearInfo() {
        analyticsHelper.d { "clearInfo - entry" }

        _loginInfo.update { null }
    }
}
