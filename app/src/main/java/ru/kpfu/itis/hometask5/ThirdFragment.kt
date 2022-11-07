package ru.kpfu.itis.hometask5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.kpfu.itis.hometask5.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private var binding: FragmentThirdBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentThirdBinding.inflate(inflater, container, false)
       return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_menu).visibility = View.VISIBLE
        binding?.apply {
            btnGoToFirst.setOnClickListener {
                findNavController().navigate(R.id.action_thirdFragment_to_thirdFragment2)
            }
        }
    }


}
