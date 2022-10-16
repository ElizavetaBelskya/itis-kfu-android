package ru.kpfu.itis.hometask2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.hometask2.databinding.RvItemTypeBinding

class TypeViewHolderAdapter: RecyclerView.Adapter<TypeViewHolderAdapter.TypeViewHolder>() {

    var itemsList: List<Type> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClick: ((Int) -> Unit) ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        return TypeViewHolder(
            RvItemTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        holder.bindItem(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size


    inner class TypeViewHolder(
        private val binding: RvItemTypeBinding,
    ): RecyclerView.ViewHolder(binding.item) {

        init {
            with(binding) {
                root.setOnClickListener {
                    onClick?.invoke(adapterPosition)
                }
            }
        }

        fun bindItem(item: Type) {
            with(binding) {
                tvName.text = item.name
                if (item.isClicked) {
                    itemView.setBackgroundColor(
                        ContextCompat.getColor(
                            root.context,
                            R.color.lime_green
                        )
                    )
                } else {
                    itemView.setBackgroundColor(
                        ContextCompat.getColor(
                            root.context,
                            R.color.white
                        )
                    )
                }
            }
        }

    }
}
