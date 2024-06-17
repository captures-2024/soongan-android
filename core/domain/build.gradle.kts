plugins {
    captures("library")
}

android {
    namespace = "com.captures2024.soongan.core.domain"
}

dependencies {
    implementation(project(":core:model"))
}
