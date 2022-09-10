package com.mycode.ticketbookingapp.homefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycode.ticketbookingapp.databinding.MovienameBinding
import com.mycode.ticketbookingapp.network.List

class Adapter(val movieLists: kotlin.collections.List<List>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(movieLists.get(position));
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(MovienameBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return movieLists.size;
    }



}
class ViewHolder(val binding: MovienameBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(movielist: List): Unit {
        binding.viewModel= movielist
    }

}