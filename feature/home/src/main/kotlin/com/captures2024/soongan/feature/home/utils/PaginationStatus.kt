package com.captures2024.soongan.feature.home.utils

enum class PaginationStatus {
    LOADING, // loading first page
    INACTIVE, // no loading
    PAGINATING, // loading next page
    EXHAUST, // loaded all pages
    ERROR, // fail loaded
    EMPTY, // success loaded - no content
}