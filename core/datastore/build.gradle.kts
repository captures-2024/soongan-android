plugins {
    captures("library")
}

android {
    namespace = "com.captures2024.soongan.core.datastore"
}

dependencies {
    implementation(libs.android.datastore.preferences)
}
