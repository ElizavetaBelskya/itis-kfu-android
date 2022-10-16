package ru.kpfu.itis.hometask2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.hometask2.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {

    private var binding: FragmentRecyclerBinding? = null
    private var recyclerAdapter: TypeViewHolderAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        initAdapter()
        return binding?.root
    }


    private fun initAdapter() {
        recyclerAdapter = TypeViewHolderAdapter().apply {
            itemsList = TypesRepository.itemsList
            onClick = ::onClickListener
        }

        val recyclerManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding?.textItemsRv?.apply {
            layoutManager = recyclerManager
            adapter = recyclerAdapter
        }

    }

    private fun onClickListener(position: Int) {
        recyclerAdapter?.itemsList?.get(position)?.isClicked = true
        recyclerAdapter?.notifyItemChanged(position)
        val item = TypesRepository.itemsList[position]
        parentFragmentManager.beginTransaction()
            .replace(
                MainActivity.fragmentsContainerId,
                DescriptionFragment.getInstance(item.name, item.description, item.pathToImage),
                DescriptionFragment.DESCRIPTION_FRAGMENT_TAG
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        binding?.textItemsRv?.adapter = null
        recyclerAdapter = null
        binding = null
        super.onDestroyView()
    }


    companion object {
        const val RECYCLER_FRAGMENT_TAG = "REGISTRATION_FRAGMENT_TAG"
        @JvmStatic
        fun getInstance() = RecyclerFragment()

    }

}
