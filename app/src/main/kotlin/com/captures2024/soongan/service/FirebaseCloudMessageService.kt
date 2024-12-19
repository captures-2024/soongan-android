package com.captures2024.soongan.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.captures2024.soongan.R
import com.captures2024.soongan.SoonGanActivity
import com.captures2024.soongan.core.common.extension.checkGrantedPermission
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.UUID

class FirebaseCloudMessageService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d(TAG, "generate fcm token : $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d(TAG, "received fcm Message : $message")

        if (!checkGrantedPermission(android.Manifest.permission.POST_NOTIFICATIONS)) return

        message.notification?.let { sgNotification ->
            val pendingIntent = createPendingIntent(message.data)
            sendNotification(sgNotification, pendingIntent)
        }
    }

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
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun sendNotification(
        sgNotification: RemoteMessage.Notification,
        pendingIntent: PendingIntent,
    ) {
        val smallIcon = R.drawable.ic_noti_soongan
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(smallIcon)
            .setContentTitle(sgNotification.title)
            .setContentText(sgNotification.body)
//            .setStyle(NotificationCompat.BigTextStyle().bigText(sgNotification.body))
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setGroup(GROUP_KEY)
            .setContentIntent(pendingIntent)
            .build()

        val summaryNotification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(smallIcon)
            .setContentTitle(GROUP_TITLE)
            .setStyle(NotificationCompat.InboxStyle().setSummaryText(GROUP_TEXT))
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
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                ).apply { description = CHANNEL_DESCRIPTION }

                createNotificationChannel(channel)
            }

            notify((System.currentTimeMillis() / 7).toInt(), notification)
            notify(SUMMARY_ID, summaryNotification)
        }
    }

    companion object {
        private const val TAG = "FCMService"

        private const val GROUP_KEY = "com.captures2024.soongan.NOTIFICATION_KEY"
        private const val GROUP_TITLE = "soongan"
        private const val GROUP_TEXT = "순간에서 확인하세요"

        private const val CHANNEL_ID = "soongan_channel_id"
        private const val CHANNEL_NAME = "soongan_Notification"
        private const val CHANNEL_DESCRIPTION = "Channel for soongan Notification"

        private const val SUMMARY_ID = 0
    }
}
