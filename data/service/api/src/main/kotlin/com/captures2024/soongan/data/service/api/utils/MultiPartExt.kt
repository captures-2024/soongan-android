package com.captures2024.soongan.data.service.api.utils

import android.content.Context
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import androidx.core.net.toUri

// fun String?.toTextRequestBody(): RequestBody? = this?.toRequestBody("text/plain".toMediaType())

fun String?.toImageMultiPart(context: Context, name: String): MultipartBody.Part? {
    return this?.let {
        val file = UriUtil.uriToFile(context = context, contentUri = this.toUri())

        MultipartBody.Part.createFormData(
            name,
            file.name,
            file.asRequestBody("image/*".toMediaType()),
        )
    }
}
