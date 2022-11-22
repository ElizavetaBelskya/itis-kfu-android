package ru.kpfu.itis.hometask7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.kpfu.itis.hometask7.databinding.FragmentMyDialogBinding
import ru.kpfu.itis.hometask7.databinding.FragmentRecyclerBinding

class MyDialogFragment() : BottomSheetDialogFragment() {

    private var state = ""

    private var binding: FragmentMyDialogBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        state = arguments?.getString(SORT_STATE_KEY)!!
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyDialogBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            if (state.startsWith(getString(R.string.sorted_id))) {
                btnSortById.text = state
                btnSortByTitle.text = getString(R.string.not_sorted_title)
            } else if (state.startsWith(getString(R.string.sorted_title))) {
                btnSortByTitle.text = state
                btnSortById.text = getString(R.string.not_sorted_id)
            }


            btnSortById.setOnClickListener {

                state = when(state) {
                    getString(R.string.sorted_id_asc) -> {
                        getString(R.string.sorted_by_id_desc)
                    }

                    getString(R.string.sorted_by_id_desc) -> {
                        getString(R.string.sorted_id_asc)
                    }
                    else -> {
                        getString(R.string.sorted_id_asc)
                    }
                }
                btnSortById.text = state
                btnSortByTitle.text = getString(R.string.not_sorted_title)
                setFragmentResult(REQUEST_KEY, bundleOf(SORT_STATE_KEY to state))
                dismiss()
            }

            btnSortByTitle.setOnClickListener {

                state = when(state) {
                    getString(R.string.sorted_title_asc) -> {
                        getString(R.string.sorted_title_desc)
                    }
                    getString(R.string.sorted_title_desc) -> {
                        getString(R.string.sorted_title_asc)
                    }
                    else ->
                        getString(R.string.sorted_title_asc)
                }
                btnSortByTitle.text = state
                btnSortById.text = getString(R.string.not_sorted_id)
                setFragmentResult(REQUEST_KEY, bundleOf( SORT_STATE_KEY to state))
                dismiss()
            }
        }
    }


    companion object {
        const val REQUEST_KEY = "request"
        const val SORT_STATE_KEY = "listState"
        const val DIALOG_FRAGMENT_TAG = "DIALOG_FRAGMENT_TAG"
        @JvmStatic
        fun getInstance(thisState: String) = MyDialogFragment().apply {
            arguments = Bundle().apply {
                putString(SORT_STATE_KEY, thisState)
            }
        }
    }
}
