package com.captures2024.soongan.core.data.utils

import okhttp3.Headers

data class BaseAPIResult<T>(
    val headers: Headers,
    val body: T?

)
