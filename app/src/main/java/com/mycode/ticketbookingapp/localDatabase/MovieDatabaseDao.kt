package com.mycode.ticketbookingapp.localDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDatabaseDao {
    @Query("select * from Review")
    fun getMovies(): LiveData<List<Review>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movie: List<Review>)
}
