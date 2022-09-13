package com.mycode.ticketbookingapp.homefragment

import com.mycode.ticketbookingapp.network.TMBDConstants

sealed class HomeRecyclerView {
    class top(
        val id:Int=0,
        val original_title:String="",
        val backdrop_path:String="",
    ):HomeRecyclerView()

    class bottom(
        val constants:TMBDConstants,
        val title:String,
        val poster_path:String,
        val type:String,
    ):HomeRecyclerView()
}