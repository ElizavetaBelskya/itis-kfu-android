package ru.kpfu.itis.hometask5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.hometask5.databinding.FragmentFirst3Binding

class FirstFragment3 : Fragment() {
    private var binding: FragmentFirst3Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirst3Binding.inflate(inflater, container, false)
        return binding?.root
    }
}
