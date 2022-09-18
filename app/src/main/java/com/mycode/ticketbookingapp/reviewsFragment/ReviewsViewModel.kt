package com.mycode.ticketbookingapp.reviewsFragment

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycode.ticketbookingapp.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.await

class ReviewsViewModel(): ViewModel() {
    private val _feed = MutableLiveData<List<ReviewData>>()

    val feed: LiveData<List<ReviewData>>
        get() = _feed

    var viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    val reviewdata:MutableList<ReviewData> = mutableListOf()



    init {
        listOfListMovies()
        Log.d("Review","Yes,It is initialized")
    }
        fun listOfListMovies(){
        coroutineScope.launch {

            val genrename=TMBDApi.retrofitService.getGenres(TMBDConstants.API_KEY)
            val listResult1=genrename.await()

            listResult1.genres.forEach {
                var getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(
                    it.id.toString(),
                    TMBDConstants.API_KEY
                )
                try {

                    val listResult = getPropertiesDeferred.await()
                    val genresList = getGenresList(listResult.items)
                    if(genresList.isNotEmpty()) {
                        reviewdata.add(ReviewData(it.name, genresList))
                    }
                    _feed.value=reviewdata
                    Log.i("Review",reviewdata.toString())

                    Log.d("Api Data", listResult.toString())

                } catch (e: Exception) {
                    Log.d("ExceptionR", "${e}")
                }
            }
        }


        }

    fun getGenresList(l: List<Movies>): List<Movies> {
        val localMovies: MutableList<Movies> = mutableListOf()
        l.forEach {
            localMovies.add(
                Movies(it.id,it.poster_path!!,it.backdrop_path!!,it.original_title,it.vote_average)
            )
            Log.d("Api Data3",
                it.poster_path + " " +it.original_title+" " +it.vote_average
            )

        }
        return localMovies

    }


}