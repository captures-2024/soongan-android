package com.captures2024.soongan.presentation.viewmodel.model

enum class FaqCategory {
    DEFAULT,
    CONTEST,
    COPYRIGHT,
    ;

    fun getCategoryItems(): List<FaqCategoryItem> = when (this) {
        DEFAULT -> listOf(
            FaqCategoryItem.Default.First,
            FaqCategoryItem.Default.Second,
        )
        CONTEST -> listOf(
            FaqCategoryItem.Contest.First,
            FaqCategoryItem.Contest.Second,
            FaqCategoryItem.Contest.Third,
            FaqCategoryItem.Contest.Fourth,
            FaqCategoryItem.Contest.Fifth,
        )
        COPYRIGHT -> listOf(
            FaqCategoryItem.Copyright.First,
            FaqCategoryItem.Copyright.Second,
            FaqCategoryItem.Copyright.Third,
        )
    }
}
