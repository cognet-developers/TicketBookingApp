package com.mycode.ticketbookingapp.network

import com.squareup.moshi.Json

data class Movie(
    val id:Int,
    @Json(name = "poster_path")    val poster_path:String,
    val original_title:String,
    val vote_average:Float,
)
