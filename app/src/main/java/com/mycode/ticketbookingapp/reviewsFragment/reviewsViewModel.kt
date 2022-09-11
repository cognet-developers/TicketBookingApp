package com.mycode.ticketbookingapp.reviewsFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycode.ticketbookingapp.network.Movie
import com.mycode.ticketbookingapp.network.TMBDApi
import com.mycode.ticketbookingapp.network.TMBDConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await

class reviewsViewModel: ViewModel() {
    private val _feed = MutableLiveData<List<ReviewData>>()

    val feed: LiveData<List<ReviewData>>
        get() = _feed

    init {
        var viewModelJob = Job()
        val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
        val reviewdata:MutableList<ReviewData> = mutableListOf()



        coroutineScope.launch {
            var getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(TMBDConstants.ACTION, TMBDConstants.API_KEY)
            try {

                var listResult = getPropertiesDeferred.await()
                val genresList=getGenresList(listResult.items)
                reviewdata.add(ReviewData("ACTION",genresList))
                Log.d("Api Data",listResult.toString())

            }catch(e:Exception){
                Log.d("Exception","${e}")
            }
        }
        coroutineScope.launch {
            var getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(TMBDConstants.ANIMATION, TMBDConstants.API_KEY)
            try {

                var listResult = getPropertiesDeferred.await()
                val genresList=getGenresList(listResult.items)
                reviewdata.add(ReviewData("ANIMATION",genresList))
                //_feed.value=genresList
                Log.d("Api Data",genresList.toString())

            }catch(e:Exception){
                Log.d("Exception","${e}")
            }
        }
        coroutineScope.launch {
            var getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(TMBDConstants.FAMILY, TMBDConstants.API_KEY)
            try {

                var listResult = getPropertiesDeferred.await()
                val genresList=getGenresList(listResult.items)
                reviewdata.add(ReviewData("FAMILY",genresList))
                //_feed.value=genresList
                Log.d("Api Data",listResult.toString())

            }catch(e:Exception){
                Log.d("Exception","${e}")
            }
        }

        coroutineScope.launch {
            var getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(TMBDConstants.COMEDY, TMBDConstants.API_KEY)
            try {

                var listResult = getPropertiesDeferred.await()
                val genresList=getGenresList(listResult.items)
                reviewdata.add(ReviewData("COMEDY",genresList))
                //_feed.value=genresList
                Log.d("Api Data",listResult.toString())

            }catch(e:Exception){
                Log.d("Exception","${e}")
            }
        }

        coroutineScope.launch {
            var getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(TMBDConstants.HISTORY, TMBDConstants.API_KEY)
            try {

                var listResult = getPropertiesDeferred.await()
                val genresList=getGenresList(listResult.items)
                reviewdata.add(ReviewData("HISTORY",genresList))
                //_feed.value=genresList
                Log.d("Api Data",listResult.toString())

            }catch(e:Exception){
                Log.d("Exception","${e}")
            }
        }

        coroutineScope.launch {
            var getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(TMBDConstants.FANTASY, TMBDConstants.API_KEY)
            try {

                var listResult = getPropertiesDeferred.await()
                val genresList=getGenresList(listResult.items)
                reviewdata.add(ReviewData("FANTASY",genresList))
                //_feed.value=genresList
                Log.d("Api Data",listResult.toString())

            }catch(e:Exception){
                Log.d("Exception","${e}")
            }
        }

        _feed.value=reviewdata
    }
    fun getGenresList(l: List<Movie>): List<Movie> {
        val localMovies: MutableList<Movie> = mutableListOf()
        l.forEach {
            localMovies.add(
                Movie(it.id,it.poster_path,it.original_title,it.vote_average)
            )
            Log.d("Api Data3",
                it.poster_path + " " +it.original_title+" " +it.vote_average
            )

        }
        return localMovies

    }
}