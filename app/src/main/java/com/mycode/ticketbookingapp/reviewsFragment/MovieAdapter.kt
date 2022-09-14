package com.mycode.ticketbookingapp.reviewsFragment

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getAttributionTag
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.mycode.ticketbookingapp.databinding.ActivityRecyclerViewBinding
import com.mycode.ticketbookingapp.databinding.ListMovienameBinding
import com.mycode.ticketbookingapp.homefragment.MovieListener
import com.mycode.ticketbookingapp.homefragment.gridview.GridViewActivity.Companion.USER_KEY
import com.mycode.ticketbookingapp.homefragment.moviedescription.MovieDescriptionActivity
import com.mycode.ticketbookingapp.network.Movies


class MovieAdapter(val movieLists: List<Movies>,val clickListener: MovieListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bind(movieLists.get(position),clickListener);
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(ListMovienameBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return movieLists.size
    }

}
class MovieViewHolder(val binding: ListMovienameBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(movielist: Movies, clickListener: MovieListener): Unit {
        binding.viewModel= movielist
        binding.clicklistener=clickListener
    }

}

class ReviewAdapter(val topic: List<ReviewData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        val USER_KEY="key"
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(topic.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ActivityRecyclerViewBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return topic.size
    }

    class ViewHolder(val binding: ActivityRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(topic: ReviewData): Unit {

            binding.viewModel= topic
            binding.recylv.adapter=MovieAdapter(topic.movies,MovieListener {
                val intent = Intent(itemView.context, MovieDescriptionActivity::class.java)
                intent.putExtra(USER_KEY, it.toString())
                Log.d("id",it.toString())
                itemView.context.startActivity(intent)
            })
        }
    }
}




