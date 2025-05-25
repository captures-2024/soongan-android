package com.captures2024.soongan.presentation.viewmodel.model

sealed interface FaqCategoryItem {

    sealed interface Default : FaqCategoryItem {
        data object First : Default
        data object Second : Default
    }

    sealed interface Contest : FaqCategoryItem {
        data object First : Default
        data object Second : Default
        data object Third : Default
        data object Fourth : Default
        data object Fifth : Default
    }

    sealed interface Copyright : FaqCategoryItem {
        data object First : Default
        data object Second : Default
        data object Third : Default
    }
}
