package com.mycode.ticketbookingapp.reviewsFragment

import com.mycode.ticketbookingapp.network.Movie

data class ReviewData (
    val name:String,
    val movies:List<Movie>
        )