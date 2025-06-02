package com.captures2024.soongan.data.source.utils

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

object UriUtil {
    fun uriToFile(
        context: Context,
        contentUri: Uri,
    ): File {
        val fileName = generateFileName(context, contentUri)

        val tempFile = File(context.cacheDir, fileName)
        tempFile.createNewFile()

        kotlin.runCatching {
            val inputStream = context.contentResolver.openInputStream(contentUri)
            val outputStream = FileOutputStream(tempFile)

            inputStream?.use { input ->
                copy(input, outputStream)
            }

            outputStream.flush()
        }.onFailure {
            it.printStackTrace()
        }

        return tempFile
    }

    private fun generateFileName(context: Context, uri: Uri): String {
        val fileType: String? = context.contentResolver.getType(uri)
        val ext = MimeTypeMap.getSingleton().getExtensionFromMimeType(fileType) ?: ""
        return "soongan_image.$ext"
    }

    private fun copy(source: InputStream, target: OutputStream) {
        val buf = ByteArray(8192)
        var length: Int
        while (source.read(buf).also { length = it } > 0) {
            target.write(buf, 0, length)
        }
    }
}
