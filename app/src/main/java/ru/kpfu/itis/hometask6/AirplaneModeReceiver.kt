package ru.kpfu.itis.hometask6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AirplaneModeReceiver(): BroadcastReceiver() {

    var makeEnabled: () -> Unit? = {}
    var makeNonEnabled: () -> Unit? = {}

    override fun onReceive(context: Context, intent: Intent) {
        if (!intent.getBooleanExtra("state", false)) {
            makeEnabled.invoke()
        } else {
            makeNonEnabled.invoke()
        }
    }
}
