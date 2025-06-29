plugins {
    alias(libs.plugins.captures2024.soongan.data.source)
}

android {
    namespace = "com.captures2024.soongan.data.source.module"
}

dependencies {
    implementation(projects.data.source.auth)
    implementation(projects.data.source.authImpl)
    implementation(projects.data.source.contest)
    implementation(projects.data.source.contestImpl)
    implementation(projects.data.source.fcm)
    implementation(projects.data.source.fcmImpl)
    implementation(projects.data.source.home)
    implementation(projects.data.source.homeImpl)
    implementation(projects.data.source.member)
    implementation(projects.data.source.memberImpl)
    implementation(projects.data.source.notification)
    implementation(projects.data.source.notificationImpl)
    implementation(projects.data.source.report)
    implementation(projects.data.source.reportImpl)
    implementation(projects.data.source.system)
    implementation(projects.data.source.systemImpl)
    implementation(projects.data.source.token)
    implementation(projects.data.source.tokenImpl)
}
