package com.mycode.ticketbookingapp.homefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mycode.ticketbookingapp.databinding.ListMovienameBinding
import com.mycode.ticketbookingapp.network.Movies


class Adapter(val movieListener: MovieListener): ListAdapter<Movies,Adapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ListMovienameBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movies, movieListener: MovieListener) {
            binding.viewModel=item
            binding.clicklistener=movieListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding=ListMovienameBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genresProperty = getItem(position)
        holder.bind(genresProperty,movieListener)
    }

}

class MovieListener(val clickListener: (moviesId:Int) -> Unit){
    fun onClick(model: Int)=clickListener(model)
}

class DiffCallback : DiffUtil.ItemCallback<Movies>() {
    override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem.id == newItem.id
    }
}
