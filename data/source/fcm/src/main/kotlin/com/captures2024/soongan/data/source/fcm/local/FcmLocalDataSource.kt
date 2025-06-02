package com.captures2024.soongan.data.source.fcm.local

interface FcmLocalDataSource {
    suspend fun getFcm(): String
}
