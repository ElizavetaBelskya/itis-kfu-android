package ru.kpfu.itis.hometask4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.kpfu.itis.hometask4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (savedInstanceState == null) {
            val fragment = RecyclerFragment.getInstance()
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fragment_container, fragment,
                    RecyclerFragment.RECYCLER_FRAGMENT_TAG
                )
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}
