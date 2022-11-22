package ru.kpfu.itis.hometask7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.hometask7.databinding.RvItemBinding

class CityListAdapter: ListAdapter<City, CityListAdapter.CityViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }


    inner class CityViewHolder(private val binding: RvItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: City) {
            with(binding) {
                tvId.text = item.id.toString()
                tvTitle.text = item.title
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<City>() {

        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem == newItem
        }

    }



}
