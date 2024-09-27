plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
}

android {
    namespace = "com.captures2024.soongan.core.domain"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))

    implementation(libs.javax.inject)
}
