package ru.kpfu.itis.hometask6

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHandler(var context: Context) {

    private var notificationManager: NotificationManager? = null

    init {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun createNotification(title: String, shortText: String): Notification {

        createNotificationChannel()

        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }

        val pendingIntent = PendingIntent.getActivity(
            context, 0 , Intent(context, MainActivity::class.java), flag
        )

        val notification = NotificationCompat.Builder(context, ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_baseline_ads_click_24)
            .setSilent(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(shortText)
            .setContentIntent(pendingIntent).build()

        return notification

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager?.getNotificationChannel(ID) == null) {
                val name : CharSequence = "My notification channel"
                val description = "Channel for my notifications"
                val importance = NotificationManager.IMPORTANCE_LOW
                val channel = NotificationChannel(ID, name, importance)
                channel.description = description
                notificationManager?.createNotificationChannel(channel)
            }
        }
    }

    fun notify(id: Int, notification: Notification?) {
        notificationManager?.notify(id, notification)
    }

    companion object {
        const val ID = "My notifications"
    }


}
