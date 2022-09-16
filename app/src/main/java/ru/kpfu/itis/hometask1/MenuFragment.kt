package ru.kpfu.itis.hometask1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kpfu.itis.hometask1.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var viewBinding: FragmentMenuBinding? = null
    private var counter: Int = 0
    private var color: String = "White"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        counter = savedInstanceState?.getInt(KEY_COUNTER_VALUE)?:0
        color = savedInstanceState?.getString(KEY_BACKGROUND_COLOR)?:"White"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMenuBinding.inflate(inflater, container, false)
        return viewBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    private fun initClickListeners() {
        viewBinding?.apply {

            btnCounter.setOnClickListener {
                counter += 1
            }

            btnOpenFragment.setOnClickListener {
                parentFragmentManager.beginTransaction().addToBackStack(null).replace(
                    MainActivity.fragmentsContainerId,
                    AdditionalFragment.newInstance(counter, color),
                    AdditionalFragment.ADDITIONAL_FRAGMENT_TAG
                ).commit()
            }

            btnChangeBackground.setOnClickListener {
                when (color) {
                    "White" -> {
                        color = "Pink"
                    }
                    "Pink" -> {
                        color = "White"
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_BACKGROUND_COLOR, color)
        outState.putInt(KEY_COUNTER_VALUE, counter)
    }


    companion object {
        private const val KEY_BACKGROUND_COLOR = "KEY_BACKGROUND_COLOR"
        private const val KEY_COUNTER_VALUE = "KEY_CHUCK_NORRIS_FACT"
        const val MENU_FRAGMENT_TAG = "MENU_FRAGMENT_TAG"
        fun getInstance() = MenuFragment()

    }


}
