package com.mycode.ticketbookingapp.reviewsFragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycode.ticketbookingapp.databinding.ListItemsBinding
import com.mycode.ticketbookingapp.databinding.MovienameBinding
import com.mycode.ticketbookingapp.network.Movies


class MovieAdapter(val movieLists: List<Movies>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val movieProperty = movieLists.get(position)
//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(movieProperty)
//        }
        // holder.bind(movieProperty)
        (holder as MovieViewHolder).bind(movieLists.get(position));
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(MovienameBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
//        Log.i("Size",movieLists.size.toString())
        return movieLists.size;
    }

}
class MovieViewHolder(val binding: MovienameBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(movielist: Movies): Unit {
        binding.viewModel= movielist
        // binding.clicklistener=clickListener
    }

}

class ReviewAdapter(val topic: List<ReviewData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val movieProperty = movieLists.get(position)
//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(movieProperty)
//        }
        // holder.bind(movieProperty)
        (holder as ViewHolder).bind(topic.get(position));
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ListItemsBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return topic.size;
    }

}
class ViewHolder(val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(topic: ReviewData): Unit {
        binding.viewModel= topic
        binding.recylv.adapter=MovieAdapter(topic.movies)
        // binding.clicklistener=clickListener
    }
}

