package com.mycode.ticketbookingapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class GenresListProperty(val items: List<Movies>)

data class GenresListProperty1(val results: List<Movies>)

@Parcelize
data class Movies(
    val id:Int,
    @Json(name = "poster_path")
    val poster_path:String?,
    val backdrop_path:String?,
    val original_title:String,
    val vote_average:Float,
):Parcelable

data class GenresList(val genres:List<genres>)

@Parcelize
data class genres(
    val id:Int=0,
    val name:String="",
):Parcelable