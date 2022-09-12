package com.mycode.ticketbookingapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

//import com.squareup.moshi.Json

data class GenresListProperty(val items: List<Movies>)

@Parcelize
data class Movies(
    val id:Int,
    @Json(name = "poster_path") val poster_path:String,
    val original_title:String,
    val vote_average:Float,
):Parcelable
