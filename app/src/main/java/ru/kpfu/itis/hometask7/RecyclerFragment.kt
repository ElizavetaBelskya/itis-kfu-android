package ru.kpfu.itis.hometask7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.hometask7.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {

    private var state = "Sorted by id ascending"

    private var recyclerAdapter: CityListAdapter? = null

    private var binding: FragmentRecyclerBinding? = null

    private var cityList = CityStorage.cityList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(MyDialogFragment.REQUEST_KEY) { _, bundle ->
            state = bundle.getString(MyDialogFragment.SORT_STATE_KEY)!!
            var newList = cityList

            if (state.startsWith(getString(R.string.sorted_id))) {
                when(state) {
                    getString(R.string.sorted_id_asc) -> {
                        newList = cityList.sortedBy { it.id }.toList()
                    }

                    getString(R.string.sorted_by_id_desc) -> {
                        newList = cityList.sortedBy { it.id }.asReversed().toList()
                    }
                }
            }

            if (state.startsWith(getString(R.string.sorted_title))) {
                when (state) {
                    getString(R.string.sorted_title_asc) -> {
                        newList = cityList.sortedBy { it.title }.toList()
                    }

                    getString(R.string.sorted_title_desc) -> {
                        newList = cityList.sortedBy { it.title }.asReversed().toList()
                    }
                }
            }

            recyclerAdapter?.submitList(newList)
        }
    }

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

        binding?.btnShowBottom?.setOnClickListener {
            val fragment = MyDialogFragment.getInstance(state)
            fragment.show(parentFragmentManager, MyDialogFragment.DIALOG_FRAGMENT_TAG)
        }

        binding?.openCamera?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(MainActivity.containerId, CameraFragment.getInstance(), CameraFragment.CAMERA_FRAGMENT_TAG).addToBackStack(null)
                .commit()
        }

    }

    private fun initAdapter() {
        if (recyclerAdapter == null) {
            recyclerAdapter = CityListAdapter()
        }

        val recyclerManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding?.recycler?.apply {
            layoutManager = recyclerManager
            recyclerAdapter?.submitList(cityList)
            adapter = recyclerAdapter
        }

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
