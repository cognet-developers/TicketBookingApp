package com.mycode.ticketbookingapp.localDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycode.ticketbookingapp.network.Movies


@Entity
data class Review(
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "movie")
    var movie: List<Movies>,
)


