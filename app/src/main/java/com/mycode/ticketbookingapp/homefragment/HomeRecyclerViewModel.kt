package com.mycode.ticketbookingapp.homefragment

import android.os.Parcelable
import com.mycode.ticketbookingapp.network.Movies
import com.mycode.ticketbookingapp.network.TMBDConstants
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

sealed class HomeRecyclerViewModel {
    class Top(val items:List<Item>):HomeRecyclerViewModel()

   class Bottom(val items:List<Item1>):HomeRecyclerViewModel()

}

@Parcelize
class Item(
    val id:Int,
    val original_title:String,
    val backdrop_path:String,
):Parcelable

@Parcelize
class Item1(
    val constant:String="",
    val title:String="",
    val poster_path:String="",
    val category:String="",
):Parcelable
