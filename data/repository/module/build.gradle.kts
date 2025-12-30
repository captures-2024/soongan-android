import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.hilt)
}

android {
    namespace = "com.captures2024.soongan.data.repository.module"
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(projects.domain.repository.auth)
    implementation(projects.domain.repository.awards)
    implementation(projects.domain.repository.contest)
    implementation(projects.domain.repository.fcm)
    implementation(projects.domain.repository.home)
    implementation(projects.domain.repository.member)
    implementation(projects.domain.repository.notification)
    implementation(projects.domain.repository.report)
    implementation(projects.domain.repository.system)
    implementation(projects.domain.repository.token)

    implementation(projects.data.repository.authImpl)
    implementation(projects.data.repository.awardsImpl)
    implementation(projects.data.repository.contestImpl)
    implementation(projects.data.repository.fcmImpl)
    implementation(projects.data.repository.homeImpl)
    implementation(projects.data.repository.memberImpl)
    implementation(projects.data.repository.notificationImpl)
    implementation(projects.data.repository.reportImpl)
    implementation(projects.data.repository.systemImpl)
    implementation(projects.data.repository.tokenImpl)
}
