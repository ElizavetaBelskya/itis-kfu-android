package ru.kpfu.itis.hometask2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.kpfu.itis.hometask2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (savedInstanceState == null) {
            val fragment = RegistrationFragment.getInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment, RegistrationFragment.REGISTRATION_FRAGMENT_TAG)
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val fragmentsContainerId: Int = R.id.fragment_container
    }

}
