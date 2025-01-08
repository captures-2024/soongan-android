package com.captures2024.soongan.core.data.utils

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun String?.toTextRequestBody(): RequestBody? = this?.toRequestBody("text/plain".toMediaType())

fun String?.toImageMultiPart(context: Context, name: String): MultipartBody.Part? {
    return this?.let {
        val file = UriUtil.uriToFile(context = context, contentUri = Uri.parse(this))

        MultipartBody.Part.createFormData(
            name,
            file.name,
            file.asRequestBody("image/*".toMediaType())
        )
    }
}