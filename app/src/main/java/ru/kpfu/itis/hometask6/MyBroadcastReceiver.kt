package ru.kpfu.itis.hometask6

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class MyBroadcastReceiver: BroadcastReceiver() {


    override fun onReceive(context:  Context?, intent: Intent?) {
        intent?.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val flag =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        val pendingIntent = PendingIntent.getActivity(
            context, 0 , Intent(context, MainActivity::class.java), flag
        )

        val title = intent?.extras?.getString(MainFragment.TITLE)
        val shortText = intent?.extras?.getString(MainFragment.SHORT_TEXT)
        val longText = intent?.extras?.getString(MainFragment.LONG_TEXT)

        context?.apply {
            NotificationRegulator(context).createNotification(pendingIntent, title, shortText, longText)
        }

    }

}
