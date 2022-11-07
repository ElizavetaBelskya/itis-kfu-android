package ru.kpfu.itis.hometask5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.hometask5.databinding.ItemBinding

class ItemViewHolderAdapter: RecyclerView.Adapter<ItemViewHolderAdapter.ItemViewHolder>() {

    var itemsList = listOf<Element>()

    var onClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size


    inner class ItemViewHolder(
        private val binding: ItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                root.setOnClickListener {
                    onClick?.invoke(itemsList[adapterPosition].action)
                }
            }

        }

        fun bindItem(item: Element) {
            with(binding) {
                tvTitle.text = item.title
                ivIcon.setImageDrawable(ContextCompat.getDrawable(root.context, item.picture))
            }
        }


    }
}
