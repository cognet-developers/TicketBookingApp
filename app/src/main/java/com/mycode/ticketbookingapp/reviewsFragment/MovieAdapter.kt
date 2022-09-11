package com.mycode.ticketbookingapp.reviewsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycode.ticketbookingapp.databinding.MovienameBinding
import com.mycode.ticketbookingapp.network.Movie

class MovieAdapter(val movieLists: List<Movie>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
        return movieLists.size;
    }

}
class MovieViewHolder(val binding: MovienameBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(movielist: Movie): Unit {
        binding.viewModel= movielist
        // binding.clicklistener=clickListener
    }

}
