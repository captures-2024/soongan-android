plugins {
    alias(libs.plugins.captures2024.soongan.android.library)
    alias(libs.plugins.captures2024.soongan.android.library.compose)
}

android {
    namespace = "com.captures2024.soongan.presentation.designsystem.icon"
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    enabled = false
}

tasks.named("ktlintMainSourceSetCheck") {
    enabled = false
}
