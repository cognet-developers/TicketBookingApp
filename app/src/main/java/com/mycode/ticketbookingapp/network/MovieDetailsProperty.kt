package com.mycode.ticketbookingapp.network

data class MovieDetailsProperty(
        val id:Int=0,
        val original_language:String="",
        val original_title:String="",
        val overview:String="",
        val poster_path:String="",
        val release_data:String="",
        val runtime:Int=0,
        val tagline:String="",
)