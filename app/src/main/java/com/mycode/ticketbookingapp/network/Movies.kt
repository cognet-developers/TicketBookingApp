package com.mycode.ticketbookingapp.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class GenresListProperty(val items: List<Movies>)

data class GenresListProperty1(val results: List<Movies>)

@Parcelize
data class Movies(
    val id:Int,
    val poster_path:String?,
    val backdrop_path:String?,
    val original_title:String,
):Parcelable

data class GenresList(val genres:List<genres>)

@Parcelize
data class genres(
    val id:Int=0,
    val name:String="",
):Parcelable