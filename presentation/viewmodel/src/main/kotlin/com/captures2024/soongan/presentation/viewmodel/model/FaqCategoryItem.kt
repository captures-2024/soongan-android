package com.captures2024.soongan.presentation.viewmodel.model

sealed interface FaqCategoryItem {

    sealed interface Default : FaqCategoryItem {
        data object First : Default
        data object Second : Default
    }

    sealed interface Contest : FaqCategoryItem {
        data object First : Contest
        data object Second : Contest
        data object Third : Contest
        data object Fourth : Contest
        data object Fifth : Contest
    }

    sealed interface Copyright : FaqCategoryItem {
        data object First : Copyright
        data object Second : Copyright
        data object Third : Copyright
    }
}
