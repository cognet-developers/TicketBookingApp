package com.mycode.ticketbookingapp.homefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycode.ticketbookingapp.databinding.ListItemsBinding

//class Adapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        (holder as ViewHolder).bind(modelList.get(position));
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        return ViewHolder(ListItemsBinding.inflate(layoutInflater, parent, false))
//    }
//
//    override fun getItemCount(): Int {
//        return 10;
//    }
//}
//class ViewHolder(val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root){
//
//    fun bind(model: Model): Unit {
//        binding.viewModel= model
//    }
//
//}