package com.mycode.ticketbookingapp.localRepository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mycode.ticketbookingapp.localDatabase.MovieDatabase
import com.mycode.ticketbookingapp.localDatabase.Review
import com.mycode.ticketbookingapp.network.Movies
import com.mycode.ticketbookingapp.network.TMBDApi
import com.mycode.ticketbookingapp.network.TMBDConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class MovieRepository(private val database: MovieDatabase) {
    private lateinit var reviewdata: LiveData<List<Review>>
    private val reviewdata1: MutableList<Review> = mutableListOf()


    val _movie = MutableLiveData<List<Review>>()
    val movie: MutableLiveData<List<Review>>
        get() = _movie

    val movies: LiveData<List<Review>> =
        Transformations.map(database.movieDatabaseDao.getMovies()) {
            it
        }

    suspend fun getMovie() {

        withContext(Dispatchers.IO) {
            val genrename = TMBDApi.retrofitService.getGenres(TMBDConstants.API_KEY)
            val listResult1 = genrename.await()

//              reviewdata= database.movieDatabaseDao.getMovies()
//
//               if(reviewdata.value!!.isNotEmpty()){
//                    movie.postValue(reviewdata.value)
//               }

            listResult1.genres.forEach {

                val getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(
                    it.id.toString(),
                    TMBDConstants.API_KEY
                )
                try {

                    val listResult = getPropertiesDeferred.await()
                    val movielist = getGenresList(listResult.items)
                    if (movielist.isNotEmpty()) {
                        reviewdata1.add(Review(it.name, movielist))
                    }
                } catch (e: Exception) {
                    Log.d("ExceptionMR", "${e}")

                }
            }
            database.movieDatabaseDao.insertAllMovies(reviewdata1)
//               movie.postValue(reviewdata1)
        }

    }


    private fun getGenresList(l: List<Movies>): List<Movies> {
        val localMovies: MutableList<Movies> = mutableListOf()
        l.forEach {
            localMovies.add(
                Movies(it.id, it.poster_path!!, it.backdrop_path!!, it.original_title)
            )
            Log.d(
                "Api Data3",
                it.poster_path + " " + it.original_title + " "
            )

        }
        return localMovies
    }

}