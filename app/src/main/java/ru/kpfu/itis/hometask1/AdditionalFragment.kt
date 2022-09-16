package ru.kpfu.itis.hometask1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ru.kpfu.itis.hometask1.databinding.FragmentAdditionalBinding

class AdditionalFragment : Fragment() {

    private var viewBinding: FragmentAdditionalBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAdditionalBinding.inflate(inflater, container, false)
        viewBinding?.apply {
            var c = arguments?.getInt("COUNTER") ?: 0
            var backgroundColor = arguments?.getString("COLOR")?: "White"

            if (c > 0) {
                tvCounter?.text = resources.getString(R.string.tv_counter_text) + c
                tvCounter?.visibility = View.VISIBLE
            }

            when (backgroundColor) {
                "White" -> {
                    root.background = ContextCompat.getDrawable(requireContext(), R.color.white)
                }
                "Pink" -> {
                    root.background =
                        ContextCompat.getDrawable(requireContext(), R.color.purple_200)
                }
            }


        }

        return viewBinding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

    companion object {
        const val ADDITIONAL_FRAGMENT_TAG = "ADDITIONAL_FRAGMENT_TAG"

        @JvmStatic
        fun newInstance(counter: Int, color: String) =
            AdditionalFragment().apply {
                arguments = Bundle().apply {
                    putInt("COUNTER", counter)
                    putString("COLOR", color)
                }
            }
    }

}
