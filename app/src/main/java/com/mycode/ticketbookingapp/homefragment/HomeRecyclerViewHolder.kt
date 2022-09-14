package com.mycode.ticketbookingapp.homefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.mycode.ticketbookingapp.databinding.*
import com.mycode.ticketbookingapp.network.Movies

sealed class HomeRecyclerViewHolder(binding: ViewBinding):RecyclerView.ViewHolder(binding.root) {
    class TopViewHolder(private val binding:ActivityTopBinding):HomeRecyclerViewHolder(binding){
    fun bind(top:HomeRecyclerViewModel.Top){
        val adapter=TopAdapter()
        binding.bannerRecyclerView.adapter=adapter
        adapter.submitList(top.items)
      }
    }

    class BottomViewHolder(private val binding:ActivityGridView2Binding):HomeRecyclerViewHolder(binding){
        fun bind(bottom :HomeRecyclerViewModel.Bottom){

        }
    }
}

class TopAdapter(): ListAdapter<Item, TopAdapter.ViewHolder>(TopDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ListItemsOfBackdropBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.data=item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding= ListItemsOfBackdropBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genresProperty = getItem(position)
        holder.bind(genresProperty)
    }

}



class TopDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }
}
