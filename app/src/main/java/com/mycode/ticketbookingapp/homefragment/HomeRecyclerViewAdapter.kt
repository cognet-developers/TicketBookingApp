package com.mycode.ticketbookingapp.homefragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.ActivityGridView2Binding
import com.mycode.ticketbookingapp.databinding.ActivityTopBinding
import com.mycode.ticketbookingapp.databinding.ListCardViewBinding
import com.mycode.ticketbookingapp.databinding.ListItemsOfBackdropBinding

class HomeRecyclerViewAdapter: RecyclerView.Adapter<HomeRecyclerViewHolder>() {

    var items= listOf<HomeRecyclerViewModel>()

    @SuppressLint("NotifyDataSetChanged")
    set(value){
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        return when(viewType){
            R.layout.activity_top -> HomeRecyclerViewHolder.TopViewHolder(
                ActivityTopBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )
            R.layout.activity_grid_view_2-> HomeRecyclerViewHolder.BottomViewHolder(
                ActivityGridView2Binding.inflate(LayoutInflater.from(parent.context),parent,false)
            )

            else -> {  throw IllegalArgumentException("Invalid ViewType") }
        }
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        when(holder){
            is HomeRecyclerViewHolder.TopViewHolder -> holder.bind(items[position] as HomeRecyclerViewModel.Top)
            is HomeRecyclerViewHolder.BottomViewHolder -> holder.bind(items[position] as HomeRecyclerViewModel.Bottom)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is HomeRecyclerViewModel.Top -> R.layout.activity_top
            is HomeRecyclerViewModel.Bottom -> R.layout.activity_grid_view_2
        }
    }

}