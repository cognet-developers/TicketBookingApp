package com.mycode.ticketbookingapp.homefragment

import android.os.Parcelable
import com.mycode.ticketbookingapp.network.TMBDConstants
import kotlinx.android.parcel.Parcelize

sealed class HomeRecyclerViewModel {
    class Top(val items:List<Item>):HomeRecyclerViewModel()


//    class Bottom(val items:MutableList<HomeRecyclerViewModel.Item1>):HomeRecyclerViewModel()

    class Bottom(
        val constants:String="",
        val title:String="",
        val poster_path:String="",
        val type:String="",
        ):HomeRecyclerViewModel()
}

@Parcelize
class Item(
    val id:Int=0,
    val original_title:String="",
    val backdrop_path:String="",
):Parcelable