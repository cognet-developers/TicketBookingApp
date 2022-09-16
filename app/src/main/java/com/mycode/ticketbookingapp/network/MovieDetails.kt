package com.mycode.ticketbookingapp.network

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetails(
        val id:Int=0,
        val original_language:String="",
        val original_title:String="",
        val overview:String="",
        val backdrop_path:String="",
        var release_date:String="",
        val runtime:Int=0,
        val tagline:String="",
        val vote_average:String="",
        val vote_count:String="",
        val genres:List<genres> = listOf(),
):Parcelable{
        val voteAverage :String
                get() = vote_average+"k ratings"

        val runTime:String
                get() =runtime.toString()+" minutes"
}


data class videos(val results:List<video>)

@Parcelize
data class video(
        val key:String,
        val site:String
):Parcelable


