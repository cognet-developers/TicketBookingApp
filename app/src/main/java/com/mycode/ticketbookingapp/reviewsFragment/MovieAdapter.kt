package com.mycode.ticketbookingapp.reviewsFragment

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycode.ticketbookingapp.databinding.ActivityRecyclerViewBinding
import com.mycode.ticketbookingapp.homefragment.gridview.Adapter
import com.mycode.ticketbookingapp.homefragment.gridview.MovieListener
import com.mycode.ticketbookingapp.homefragment.moviedescription.MovieDescriptionActivity

class ReviewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var topic =  listOf<ReviewData>()
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
            val adapter= Adapter(MovieListener {
                val intent = Intent(itemView.context, MovieDescriptionActivity::class.java)
                intent.putExtra(USER_KEY, it.toString())
                Log.d("id",it.toString())
                itemView.context.startActivity(intent)
            })
            binding.recylv.adapter=adapter
            adapter.submitList(topic.movies)

        }
    }
}




