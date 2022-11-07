package ru.kpfu.itis.hometask5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.kpfu.itis.hometask5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        val controller = (supportFragmentManager.findFragmentById(R.id.container) as
                NavHostFragment).navController

        binding?.apply {
            bottomMenu.setupWithNavController(controller)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}
