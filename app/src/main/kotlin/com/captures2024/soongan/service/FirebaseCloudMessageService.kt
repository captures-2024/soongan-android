package com.captures2024.soongan.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.app.NotificationCompat
import com.captures2024.soongan.R
import com.captures2024.soongan.SoonGanActivity
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.extension.checkGrantedPermission
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseCloudMessageService : FirebaseMessagingService() {
    private val simpleName = this::class.simpleName

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    override fun onNewToken(token: String) {
        analyticsHelper.d { "[$simpleName] onNewToken - token: $token" }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        analyticsHelper.d { "[$simpleName] onMessageReceived - message: $message" }

        if (!checkGrantedPermission(android.Manifest.permission.POST_NOTIFICATIONS)) return

        message.notification?.let { sgNotification ->
            val pendingIntent = createPendingIntent(message.data)
            sendNotification(sgNotification, pendingIntent)
        }
    }

    @Suppress("UnusedParameter")
    private fun createPendingIntent(messageData: Map<String, String>): PendingIntent {
        // server 타입명 동기화 필요
//        val type = getNotificationType(messageData["notificationType"])

        val intent = Intent(this, SoonGanActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            putExtra("link", messageData["link"])
//            putExtra("type", type)
//            putExtra("postId", messageData["postId"])
//            putExtra("timeStamp", messageData["timestamp"])
        }

        return PendingIntent.getActivity(
            this,
            UUID.randomUUID().hashCode(),
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    private fun sendNotification(
        sgNotification: RemoteMessage.Notification,
        pendingIntent: PendingIntent,
    ) {
        val smallIcon = R.drawable.ic_noti_soongan
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val bitmap = sgNotification.imageUrl?.let { uriToBitmap(it) }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(smallIcon)
            .setContentTitle(sgNotification.title)
            .setContentText(sgNotification.body)
            .setLargeIcon(bitmap)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap)
                    .bigLargeIcon(null as Bitmap?),
            )
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
            .build()

        with(getSystemService(NOTIFICATION_SERVICE) as NotificationManager) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH,
                ).apply { description = CHANNEL_DESCRIPTION }

                createNotificationChannel(channel)
            }

            notify((System.currentTimeMillis() / 7).toInt(), notification)
        }
    }

    private fun uriToBitmap(imageUri: Uri): Bitmap? = runCatching {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, imageUri))
        } else {
            MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
        }
    }.getOrNull()

    companion object {
        private const val CHANNEL_ID = "soongan_channel_id"
        private const val CHANNEL_NAME = "soongan_Notification"
        private const val CHANNEL_DESCRIPTION = "Channel for soongan Notification"
    }
}
