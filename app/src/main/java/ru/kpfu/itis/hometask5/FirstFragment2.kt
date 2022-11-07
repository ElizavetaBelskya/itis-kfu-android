package ru.kpfu.itis.hometask5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.hometask5.databinding.FragmentFirst2Binding

class FirstFragment2 : Fragment() {

    private var binding: FragmentFirst2Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirst2Binding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            btnGoToLast.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment2_to_firstFragment3)
            }
        }


    }


}
