package ru.kpfu.itis.hometask5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.kpfu.itis.hometask5.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var recyclerAdapter: ItemViewHolderAdapter? = null
    private var binding: FragmentBottomSheetBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = ItemViewHolderAdapter().apply {
            itemsList = ElementsRepository.itemsList
            onClick = ::onClickListener
        }


        val recyclerManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding?.recycler?.apply {
            layoutManager = recyclerManager
            adapter = recyclerAdapter
        }

    }

    private fun onClickListener(action: Int) {
        findNavController().navigate(action)
    }

}
