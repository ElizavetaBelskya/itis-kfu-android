package ru.kpfu.itis.hometask6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.kpfu.itis.hometask6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    var permissionsRequestHandler: PermissionsRequestHandler? = PermissionsRequestHandler(this)

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

    }


    override fun onDestroy() {
        super.onDestroy()
        permissionsRequestHandler = null
        binding = null
    }



}
