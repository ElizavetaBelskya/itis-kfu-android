package ru.kpfu.itis.hometask2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import ru.kpfu.itis.hometask2.databinding.FragmentChangeableBinding

class ChangeableFragment : Fragment() {

    private var binding: FragmentChangeableBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangeableBinding.inflate(inflater, container, false)
        createNewLayout()
        return binding?.root
    }


    private fun createNewLayout() {
        binding?.apply {
            (view1.layoutParams as? ConstraintLayout.LayoutParams)?.apply {
                height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                endToEnd =  ConstraintLayout.LayoutParams.UNSET
                bottomToTop = view3.id
                endToStart = view2.id
            }

            (view2.layoutParams as? ConstraintLayout.LayoutParams)?.apply {
                height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                topToBottom = ConstraintLayout.LayoutParams.UNSET
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                bottomToTop = view4.id
                startToEnd = view1.id
                startToStart = ConstraintLayout.LayoutParams.UNSET
            }

            (view3.layoutParams as? ConstraintLayout.LayoutParams)?.apply {
                height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                topToBottom = view1.id
                endToStart = view4.id
                endToEnd = ConstraintLayout.LayoutParams.UNSET
                bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                bottomToTop = ConstraintLayout.LayoutParams.UNSET
            }

            (view4.layoutParams as? ConstraintLayout.LayoutParams)?.apply {
                height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                topToBottom = view2.id
                startToEnd = view3.id
                startToStart = ConstraintLayout.LayoutParams.UNSET
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {

        const val CHANGEABLE_FRAGMENT_TAG = "CHANGEABLE_FRAGMENT_TAG"

        @JvmStatic
        fun getInstance() = ChangeableFragment()
    }
}
