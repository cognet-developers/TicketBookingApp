package com.mycode.ticketbookingapp.homefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mycode.ticketbookingapp.databinding.MovienameBinding
import com.mycode.ticketbookingapp.network.GenresListProperty


class Adapter(): ListAdapter<GenresListProperty,Adapter.ViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<GenresListProperty>() {
        override fun areItemsTheSame(oldItem: GenresListProperty, newItem: GenresListProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GenresListProperty, newItem: GenresListProperty): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: MovienameBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GenresListProperty) {
            binding.viewModel=item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding=MovienameBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genresProperty = getItem(position)
        holder.bind(genresProperty)
    }

}