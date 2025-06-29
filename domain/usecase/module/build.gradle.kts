import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.hilt)
}

android {
    namespace = "com.captures2024.soongan.domain.usecase.module"
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(projects.domain.usecase.auth)
    implementation(projects.domain.usecase.authImpl)
    implementation(projects.domain.usecase.contest)
    implementation(projects.domain.usecase.contestImpl)
    implementation(projects.domain.usecase.fcm)
    implementation(projects.domain.usecase.fcmImpl)
    implementation(projects.domain.usecase.home)
    implementation(projects.domain.usecase.homeImpl)
    implementation(projects.domain.usecase.member)
    implementation(projects.domain.usecase.memberImpl)
    implementation(projects.domain.usecase.notification)
    implementation(projects.domain.usecase.notificationImpl)
    implementation(projects.domain.usecase.report)
    implementation(projects.domain.usecase.reportImpl)
    implementation(projects.domain.usecase.system)
    implementation(projects.domain.usecase.systemImpl)
    implementation(projects.domain.usecase.token)
    implementation(projects.domain.usecase.tokenImpl)
}
