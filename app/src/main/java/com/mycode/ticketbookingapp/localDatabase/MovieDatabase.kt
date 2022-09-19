package com.mycode.ticketbookingapp.localDatabase

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.mycode.ticketbookingapp.network.Movies

@Database(entities = [Review::class], version = 1)
@TypeConverters(Convert::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDatabaseDao: MovieDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "movie_list_table="
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

class Convert {
    @TypeConverter
    fun fromList(value: List<Movies>?): String = Gson().toJson(value)

    @TypeConverter
    fun toList(value: String) = Gson().fromJson(value, Array<Movies>::class.java).toList()
}