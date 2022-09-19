package com.mycode.ticketbookingapp.reviewsFragment

import com.mycode.ticketbookingapp.network.Movies

data class ReviewData(
    val name: String,
    val movies: List<Movies>,
)