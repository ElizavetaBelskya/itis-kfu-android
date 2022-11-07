package ru.kpfu.itis.hometask5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kpfu.itis.hometask5.databinding.FragmentSecond2Binding


class SecondFragment2 : Fragment() {

    private var binding: FragmentSecond2Binding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecond2Binding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            tvName.text = arguments?.getString(nameText)
        }
    }

    companion object {
        const val nameText: String = "Default"
    }

}
