package com.mycode.ticketbookingapp.homefragment

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.mycode.ticketbookingapp.databinding.*
import com.mycode.ticketbookingapp.homefragment.gridview.GridViewActivity
import com.mycode.ticketbookingapp.homefragment.gridview.GridViewActivity.Companion.USER_KEY
import com.mycode.ticketbookingapp.homefragment.moviedescription.MovieDescriptionActivity
import com.mycode.ticketbookingapp.network.Movies
import com.mycode.ticketbookingapp.reviewsFragment.ReviewAdapter

sealed class HomeRecyclerViewHolder(binding: ViewBinding):RecyclerView.ViewHolder(binding.root) {
    class TopViewHolder(private val binding:ActivityTopBinding):HomeRecyclerViewHolder(binding){
    fun bind(top:HomeRecyclerViewModel.Top){
        val adapter=TopAdapter(MovieListener {
            val intent = Intent(itemView.context, MovieDescriptionActivity::class.java)
            intent.putExtra(ReviewAdapter.USER_KEY, it.toString())
            Log.d("id",it.toString())
            itemView.context.startActivity(intent)
        })
        binding.bannerRecyclerView.adapter=adapter
        adapter.submitList(top.items)
      }
    }

    class BottomViewHolder(private val binding:ActivityGridView2Binding):HomeRecyclerViewHolder(binding){

        fun bind(bottom :HomeRecyclerViewModel.Bottom){
            val adapter=BottomAdapter(GridClickListener {
                val intent = Intent(itemView.context, GridViewActivity::class.java)
                intent.putExtra("1",it.constant)
                intent.putExtra("2",it.title)
                intent.putExtra("3",it.category)
                Log.d("id",it.toString())
                itemView.context.startActivity(intent)
            })
            binding.gridRecyclerView.adapter=adapter
            adapter.submitList(bottom.items)
        }
    }
}

class TopAdapter(val movieListener: MovieListener): ListAdapter<Item, TopAdapter.ViewHolder>(TopDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ListItemsOfBackdropBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item,movieListener: MovieListener) {
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
        holder.bind(genresProperty,movieListener)
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

class BottomAdapter(val gridClickListener:GridClickListener): ListAdapter<Item1, BottomAdapter.ViewHolder>(BottomDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: ListCardViewBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item1,gridClickListener: GridClickListener) {
            binding.data=item
            binding.gridClickListener=gridClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding= ListCardViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genresProperty = getItem(position)
        holder.bind(genresProperty,gridClickListener)
    }

}


class GridClickListener(val clickListener: (movie:Item1) -> Unit){
    fun onClick(model:Item1)=clickListener(model)
}


class BottomDiffCallback : DiffUtil.ItemCallback<Item1>() {
    override fun areItemsTheSame(oldItem: Item1, newItem: Item1): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Item1, newItem: Item1): Boolean {
        return oldItem.constant == newItem.constant
    }
}























