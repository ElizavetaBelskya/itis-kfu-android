package ru.kpfu.itis.hometask6

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.kpfu.itis.hometask6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var receiver: AirplaneModeReceiver? = null

    private var binding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val fragment = MainFragment.getInstance()
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fragment_container, fragment,
                    MainFragment.MAIN_FRAGMENT_TAG
                )
                .commit()
        }

        receiver = AirplaneModeReceiver().apply {
            this.makeEnabled = { fragment.makeEnabled() }
            this.makeNonEnabled = {fragment.makeNonEnabled()}
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        applicationContext.registerReceiver(receiver, intentFilter)


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        applicationContext.unregisterReceiver(receiver)
    }


}
