import com.captures2024.soongan.plugin.implementation

plugins {
    alias(libs.plugins.captures2024.soongan.data.source.impl)
}

android {
    namespace = "com.captures2024.soongan.data.source.token.impl"
}

dependencies {
    implementation(projects.data.datastore)
    implementation(projects.data.source.token)

    implementation(libs.android.datastore.preferences)
}
