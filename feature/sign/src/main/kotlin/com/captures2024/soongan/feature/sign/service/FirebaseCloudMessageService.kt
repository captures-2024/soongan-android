package com.captures2024.soongan.feature.sign.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.captures2024.soongan.feature.sign.R
import com.captures2024.soongan.feature.sign.SignActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber


class FirebaseCloudMessageService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.tag(TAG).d("token = $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.body?.let(this::sendNotification)
    }

    private fun sendNotification(messageBody: String) {
        Timber.tag(TAG).d("messageBody = $messageBody")
        val intent = Intent(this, SignActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

//        val channelId = getString(R.string.default_notification_channel_id)
        val channelId = "test"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setContentTitle(getString(R.string.fcm_message))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)


        with(getSystemService(NOTIFICATION_SERVICE) as NotificationManager) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    "title",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                createNotificationChannel(channel)
            }

            notify(0, notificationBuilder.build())
        }
    }
    companion object {
        private const val TAG = "FCMService"
    }
}