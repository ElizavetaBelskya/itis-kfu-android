package ru.kpfu.itis.hometask4

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.kpfu.itis.hometask4.databinding.RvItemFirstTypeBinding
import ru.kpfu.itis.hometask4.databinding.RvItemSecondTypeBinding
import ru.kpfu.itis.hometask4.databinding.RvItemThirdTypeBinding
import kotlin.random.Random

class ElementViewHolderAdapter (): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var glide: RequestManager? = null
    var itemsList = mutableListOf<RecyclerModel>()
//        set(value) {
//            val callback = ModifiedCallback(field, value)
//            val diffResult = DiffUtil.calculateDiff(callback)
//            field = value
//            diffResult.dispatchUpdatesTo(this)
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RecyclerTypes.FAVOURITE_TYPE.index -> {
                FirstTypeViewHolder(
                    RvItemFirstTypeBinding.inflate(LayoutInflater.from(parent.context),
                    parent, false)
                )
            }

            RecyclerTypes.MENU_TYPE.index -> {
                SecondTypeViewHolder(
                    RvItemSecondTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            else -> {
                ThirdTypeViewHolder(
                    RvItemThirdTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemsList[position].type.index
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (itemsList[position].type) {
            RecyclerTypes.FAVOURITE_TYPE -> {
                (holder as? FirstTypeViewHolder)?.bindItem(itemsList[position])
            }

            RecyclerTypes.MENU_TYPE-> {
                (holder as? SecondTypeViewHolder)?.bindItem(itemsList[position])
            }

            else -> {
                (holder as? ThirdTypeViewHolder)?.bindItem(itemsList[position])

            }

        }
    }


    override fun getItemCount() = itemsList.size

    inner class FirstTypeViewHolder(
        private val binding: RvItemFirstTypeBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                ivIllustration.setOnClickListener {
                    itemsList[adapterPosition].state = (itemsList[adapterPosition].state + 1) % 3
                    notifyItemChanged(adapterPosition)
//                    var newList = mutableListOf<RecyclerModel>()
//                    newList.addAll(itemsList)
//                    newList[adapterPosition].state = (itemsList[adapterPosition].state + 1) % 3
                    //setNewList(newList)
                }
            }
        }

        fun bindItem(item: RecyclerModel) {
            binding.apply {
                tvName.text = item.name
                glide?.load(item.pathToImage)?.into(ivIllustration)
                var state = itemsList[adapterPosition].state
                when (state) {
                    1 ->
                        glide?.load(itemsList[adapterPosition].pathToImage)?.into(ivIllustration)
                    2 ->
                        glide?.load("https://www.apkmirror.com/wp-content/uploads/2021/08/42/611dac7ba5468.png")?.into(ivIllustration)
                    0 ->
                        glide?.load("https://st.depositphotos.com/37050820/58882/v/450/depositphotos_588825948-stock-illustration-spotify-vector-logo-vector-illustration.jpg")?.into(ivIllustration)
                }

            }

        }

    }

    inner class SecondTypeViewHolder(
        private val binding: RvItemSecondTypeBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: RecyclerModel) {
            binding.apply {
                tvName.text = item.name
                glide?.load(item.pathToImage)?.into(ivIllustration)
            }
        }
    }

    inner class ThirdTypeViewHolder(
        private val binding: RvItemThirdTypeBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            with (binding) {
                root.setOnClickListener {
                    val model = RecyclerModelGenerator().generateModel(itemsList.size);
                    val typeOfRandomModel = model.type

                    val minIndex =
                        itemsList.indices.first { itemsList[it].type == typeOfRandomModel }
                    val maxIndex =
                        itemsList.indices.last { itemsList[it].type == typeOfRandomModel }
                    val randomIndex = minIndex + Random.nextInt(maxIndex - minIndex + 2)
                    itemsList.add(randomIndex, model)
                    notifyDataSetChanged()
//                    var newList = mutableListOf<RecyclerModel>()
//                    newList.addAll(itemsList)
//                    newList.add(randomIndex, model)
//                    setNewList(newList)
                }
            }
        }

        fun bindItem(item: RecyclerModel) {
            binding.apply {
                if (itemsList[itemsList.indexOf(item)-1].type != RecyclerTypes.ALBUM_TYPE) {
                    tvTitle.visibility = View.VISIBLE
                } else {
                    tvTitle.visibility = View.GONE
                }
                tvAlbum.text = item.name
                tvAuthor.text = item.information
                glide?.load(item.pathToImage)?.into(ivIllustration)
            }
        }
    }

//    fun setNewList(newList: List<RecyclerModel>) {
//        val callback = ModifiedCallback(itemsList, newList)
//        val diffResult = DiffUtil.calculateDiff(callback)
//        itemsList.clear()
//        itemsList.addAll(newList)
//        diffResult.dispatchUpdatesTo(this@ElementViewHolderAdapter)
//    }


}
