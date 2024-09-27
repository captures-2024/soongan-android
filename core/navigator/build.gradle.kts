import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.captures2024.soongan.core.navigator"
}


dependencies {
    implementation(libs.kotlin.serialization.json)
}