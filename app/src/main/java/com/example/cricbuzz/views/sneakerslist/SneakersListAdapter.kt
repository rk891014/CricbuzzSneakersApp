package com.example.cricbuzz.views.sneakerslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.databinding.ItemSneakerListBinding
import com.example.cricbuzz.domain.Sneaker
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject


@FragmentScoped
class SneakersListAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<Sneaker, SneakersListAdapter.ViewHolder>(SneakersListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemSneakerListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Sneaker, clickListener: ClickListener) {
            binding.data = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSneakerListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SneakersListDiffCallback : DiffUtil.ItemCallback<Sneaker>() {

    override fun areItemsTheSame(oldItem: Sneaker, newItem: Sneaker): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Sneaker, newItem: Sneaker): Boolean {
        return oldItem == newItem
    }

}

class ClickListener @Inject constructor() {

    var onItemClick: ((Sneaker) -> Unit)? = null
    var onItemAddCartClick: ((Sneaker) -> Unit)? = null

    fun onClick(data: Sneaker) {
        onItemClick?.invoke(data)
    }
    fun onAddCartClick(data: Sneaker) {
        onItemAddCartClick?.invoke(data)
    }
}