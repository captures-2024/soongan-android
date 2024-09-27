plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.hilt)
}

android {
    namespace = "com.captures2024.soongan.core.datastore"
}

dependencies {
    implementation(libs.android.datastore.preferences)
}
