package ru.kpfu.itis.hometask4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.kpfu.itis.hometask4.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {

    private var binding: FragmentRecyclerBinding? = null
    private var recyclerAdapter: ElementViewHolderAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        if (recyclerAdapter == null) {
            recyclerAdapter = binding?.root?.context?.let { Glide.with(it) }?.let {
                ElementViewHolderAdapter().apply {
                    itemsList = ListRepository.itemsList
                    glide = it
                }
            }
        }
        val recyclerManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding?.textItemsRv?.apply {
            layoutManager = recyclerManager
            adapter = recyclerAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding?.textItemsRv?.adapter = null
        recyclerAdapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val RECYCLER_FRAGMENT_TAG = "RECYCLER_FRAGMENT_TAG"
        @JvmStatic
        fun getInstance() = RecyclerFragment()
    }
}
