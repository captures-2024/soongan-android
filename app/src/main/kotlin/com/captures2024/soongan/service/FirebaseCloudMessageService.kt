package com.captures2024.soongan.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.captures2024.soongan.R
import com.captures2024.soongan.SoonGanActivity
import com.captures2024.soongan.core.common.extension.checkGrantedPermission
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.utils.getNotificationType
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.UUID

class FirebaseCloudMessageService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d(TAG, "generate fcm token : $token")
        // sendToServer - signIn 후 진행
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onMessageReceived(message: RemoteMessage) {
        Log.d(TAG, "generate fcm Message : $message")

        if (!checkGrantedPermission(android.Manifest.permission.POST_NOTIFICATIONS)) return
        if (message.data.isEmpty()) return

        sendNotification(message.data)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun sendNotification(messageData: Map<String, String>) {
        val type = getNotificationType(messageData["type"])
        if (type == NotificationType.UNDEFINED) return

        val intent = Intent(this, SoonGanActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra("notification_type", type.name)
//            putExtra("hasNotification", "$count")
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            UUID.randomUUID().hashCode(),
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_noti_soongan)
            .setContentTitle(type.title)
            .setContentText(messageData["title"])
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(messageData["body"])
            )
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setGroup(GROUP_KEY)
            .setContentIntent(pendingIntent)

        val summaryNotification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("soongan")
            .setContentText("새로운 알림이 있습니다")
            .setSmallIcon(R.drawable.ic_noti_soongan)
            .setStyle(
                NotificationCompat.InboxStyle()
                    .setSummaryText("순간에서 확인하세요")
            )
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setGroup(GROUP_KEY)
            .setGroupSummary(true)
            .setContentIntent(pendingIntent)
            .build()

        with(getSystemService(NOTIFICATION_SERVICE) as NotificationManager) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "notification for ${type.name}",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply { description = "${type.name} notification channel" }

                createNotificationChannel(channel)
            }
        }

        NotificationManagerCompat.from(this).apply {
            if (!checkGrantedPermission(android.Manifest.permission.POST_NOTIFICATIONS)) return

            notify(SUMMARY_NOTIFY_ID, summaryNotification)
            notify((System.currentTimeMillis() / 7).toInt(), builder.build())
        }
    }

    companion object {
        private const val TAG = "FCMService"
        private const val GROUP_KEY = "com.captures2024.soongan.NOTIFICATION.KEY"
        private const val CHANNEL_ID = "soongan_channel_id"
        private const val SUMMARY_NOTIFY_ID = 0
    }
}
