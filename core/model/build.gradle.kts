import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.jvm.kotlin)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.kotlin.serialization.json)
}