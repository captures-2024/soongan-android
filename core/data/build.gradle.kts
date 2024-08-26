plugins {
    captures("library")
}

android {
    namespace = "com.captures2024.soongan.core.data"
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(project(":core:model"))

    implementation(libs.bundles.retrofit)
    implementation(platform(libs.okhttp.bom))
}