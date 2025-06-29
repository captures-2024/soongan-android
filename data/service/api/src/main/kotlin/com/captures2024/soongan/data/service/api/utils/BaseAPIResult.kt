package com.captures2024.soongan.data.service.api.utils

data class BaseAPIResult<T>(
    val headers: Map<String, List<String>>,
    val body: T?,
)
