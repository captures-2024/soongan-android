package com.captures2024.soongan.core.data.source.fcm.local

interface FcmLocalDataSource {
    suspend fun getFcm(): String
}
