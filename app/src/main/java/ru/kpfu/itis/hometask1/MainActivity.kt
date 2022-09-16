package ru.kpfu.itis.hometask1

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ru.kpfu.itis.hometask1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (savedInstanceState == null) {
            val fragment = MenuFragment.getInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment, MenuFragment.MENU_FRAGMENT_TAG)
                .commit()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        val fragmentsContainerId: Int = R.id.fragment_container
        fun getInstance() = MainActivity()
    }


}
