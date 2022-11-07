package ru.kpfu.itis.hometask5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.kpfu.itis.hometask5.databinding.FragmentThird3Binding

class ThirdFragment3 : Fragment() {

    private var binding: FragmentThird3Binding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_menu).visibility = View.VISIBLE
        binding = FragmentThird3Binding.inflate(inflater, container, false)
        return binding?.root
    }


}
