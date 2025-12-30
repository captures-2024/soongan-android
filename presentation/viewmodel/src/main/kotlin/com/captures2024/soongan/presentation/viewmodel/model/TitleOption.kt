package com.captures2024.soongan.presentation.viewmodel.model

data class TitleOption(
    val round: Int = 1,
    val subject: String = "",
) {
    // check ScrollTitlePicker clickable
    val hasValidSubject = subject.isNotBlank()
}
