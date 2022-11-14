package ru.kpfu.itis.hometask6

import android.Manifest
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.TaskStackBuilder
import ru.kpfu.itis.hometask6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private var receiver: AirplaneModeReceiver? = null

    private var manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (savedInstanceState == null) {
            val fragment = MainFragment.getInstance()
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fragment_container, fragment,
                    MainFragment.MAIN_FRAGMENT_TAG
                )
                .commit()
        }
        setContentView(binding?.root)

        //прописать этот ресивер в манифест не получается, требует статический класс, а от этого уже другие проблемы начинаются

        receiver =  AirplaneModeReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        applicationContext.registerReceiver(receiver, intentFilter);


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        applicationContext.unregisterReceiver(receiver)
    }


    inner class AirplaneModeReceiver: BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            var fragment: MainFragment = supportFragmentManager.findFragmentByTag(MainFragment.MAIN_FRAGMENT_TAG) as MainFragment
            if (!intent.getBooleanExtra("state", false)) {
                fragment.binding?.apply {
                    etTitle.isEnabled = true
                    etShortMessage.isEnabled = true
                    etLongMessage.isEnabled = checkBox.isChecked
                    etTime.isEnabled = true
                    checkBox.isEnabled = true
                    btnCancel.isEnabled = true
                    btnShowTime.isEnabled = true
                    btnCreate.isEnabled =  !(etTitle.text.isNullOrEmpty() || etShortMessage.text.isNullOrEmpty() || etTime.text.isNullOrEmpty())
                }

            } else {
                fragment.binding?.apply {
                    etTitle.isEnabled = false
                    etShortMessage.isEnabled = false
                    etLongMessage.isEnabled = false
                    etTime.isEnabled = false
                    checkBox.isEnabled = false
                    btnCreate.isEnabled = false
                    btnCancel.isEnabled = false
                    btnShowTime.isEnabled = false
                }
            }
        }

    }



}
