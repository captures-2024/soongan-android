package com.captures2024.soongan.data.repository.system.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.data.source.system.local.InAppBrowserLocalDataSource
import com.captures2024.soongan.data.source.system.remote.AppVersionRemoteDataSource
import com.captures2024.soongan.domain.repository.system.SystemRepository
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class SystemRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val inAppBrowserLocalDataSource: InAppBrowserLocalDataSource,
    private val appVersionRemoteDataSource: AppVersionRemoteDataSource,
) : SystemRepository {

    override val inAppBrowserUrl: SharedFlow<String>
        get() = inAppBrowserLocalDataSource.inAppBrowserUrl

    init {
        analyticsHelper.d { "SystemRepository::init" }
    }

    override suspend fun launchInAppBrowser(url: String) {
        inAppBrowserLocalDataSource.postInAppBrowserUrl(url)
    }

    override suspend fun checkAppUpdateAvailable(): Boolean {
        val serverVersion = appVersionRemoteDataSource.getAppVersion()
        val result = compareSemVer(serverVersion)

        return result > 0
    }

    private fun compareSemVer(serverVersion: String?): Int {
        val appVersion = BuildConfig.APP_VERSION

        fun parse(v: String?): List<Int> {
            return v?.removePrefix("v")
                ?.split('.')
                ?.map { seg ->
                    seg.takeWhile(Char::isDigit).toIntOrNull() ?: 0
                }
                ?.dropLastWhile { it == 0 }
                ?.ifEmpty { listOf(0) }
                ?: listOf(0)
        }

        val sv = parse(serverVersion)
        val av = parse(appVersion)
        val max = maxOf(sv.size, av.size)

        for (i in 0 until max) {
            val ai = sv.getOrNull(i) ?: 0
            val bi = av.getOrNull(i) ?: 0
            if (ai != bi) return ai - bi
        }

        return 0
    }
}
