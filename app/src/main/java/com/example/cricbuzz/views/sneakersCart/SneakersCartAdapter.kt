package com.example.cricbuzz.views.sneakersCart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.databinding.ItemSneakerCartBinding
import com.example.cricbuzz.domain.Sneaker
import com.example.cricbuzz.domain.SneakerCart
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class SneakersCartAdapter @Inject constructor(val cartClickListener: CartClickListener) :
    ListAdapter<SneakerCart, SneakersCartAdapter.ViewHolder>(SneakersCartDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,cartClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemSneakerCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SneakerCart, cartClickListener: CartClickListener) {
            binding.sneakerCart = item
            binding.executePendingBindings()
            binding.clickListener = cartClickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSneakerCartBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SneakersCartDiffCallback : DiffUtil.ItemCallback<SneakerCart>() {

    override fun areItemsTheSame(oldItem: SneakerCart, newItem: SneakerCart): Boolean {
        return oldItem.sneaker.id == newItem.sneaker.id
    }

    override fun areContentsTheSame(oldItem: SneakerCart, newItem: SneakerCart): Boolean {
        return oldItem == newItem
    }

}

class CartClickListener @Inject constructor() {

    var onItemRemoveCartClick: ((SneakerCart) -> Unit)? = null
    var onItemClick: ((Sneaker) -> Unit)? = null
    var onItemIncreament: ((SneakerCart) -> Unit)? = null
    var onItemDecreament: ((SneakerCart) -> Unit)? = null

    fun onClick(data: Sneaker) {
        onItemClick?.invoke(data)
    }
    fun onRemoveCartClick(data: SneakerCart) {
        onItemRemoveCartClick?.invoke(data)
    }
    fun onIncreaseItem(data: SneakerCart) {
        onItemIncreament?.invoke(data)
    }
    fun onDecreaseItem(data: SneakerCart) {
        onItemDecreament?.invoke(data)
    }
}