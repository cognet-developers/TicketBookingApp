package com.mycode.ticketbookingapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class GenresListProperty(val genre:List<list>)

data class list(
    val id:Int,
    val poster_path:String,
    val original_title:String,
    val vote_average:Float,
)
