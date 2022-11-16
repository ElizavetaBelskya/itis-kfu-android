package ru.kpfu.itis.hometask6

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationRegulator(private val context: Context) {

    private var notificationManager: NotificationManager? = null

    init {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun createNotification(pendingIntent: PendingIntent, title: String?, shortText: String?, longText: String?) {

        createNotificationChannel()

        var builder = NotificationCompat.Builder(context, ID).setSmallIcon(R.drawable.ic_baseline_ads_click_24)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle(title)
            .setContentText(shortText)
            .setContentIntent(pendingIntent)

        if (!longText.isNullOrBlank()) {
            builder = builder
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText(longText))
        }

        notificationManager?.notify(100,
            builder
                .build())

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name : CharSequence = "My notification channel"
            val description = "Channel for my notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH
            if (notificationManager?.getNotificationChannel(ID) == null) {
                val channel = NotificationChannel(ID, name, importance)
                channel.description = description
                val notificationManager = context.getSystemService(
                    NotificationManager::class.java
                )

                notificationManager.createNotificationChannel(channel)
            }

        }
    }

    companion object {
        const val ID = "My notifications"
    }


}
