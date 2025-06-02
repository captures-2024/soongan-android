package com.captures2024.soongan.data.source.utils

import okhttp3.Headers

data class BaseAPIResult<T>(
    val headers: Headers,
    val body: T?,
)
