package com.mycode.ticketbookingapp.homefragment.gridmovie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycode.ticketbookingapp.network.TMBDApi
import com.mycode.ticketbookingapp.network.TMBDConstants
import com.mycode.ticketbookingapp.network.Movie
import com.mycode.ticketbookingapp.reviewsFragment.ReviewData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await

class GridMovieViewModel: ViewModel() {


    private val _feed = MutableLiveData<List<Movie>>()

    val feed: LiveData<List<Movie>>
        get() = _feed

    private val _navigateToSelectedProperty = MutableLiveData<Int>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedProperty: LiveData<Int>
        get() = _navigateToSelectedProperty


    init {
        var viewModelJob = Job()
        val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

        coroutineScope.launch {
            var getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(TMBDConstants.ACTION, TMBDConstants.API_KEY)
            try {

                var listResult = getPropertiesDeferred.await()
                val genresList=getGenresList(listResult.items)
                _feed.value=genresList
                Log.d("Api Data",listResult.toString())

            }catch(e:Exception){
                Log.d("Exception","${e}")
            }
        }


        //var viewModelJob = Job()
       // val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)



    }


    fun getGenresList(l: List<Movie>): List<Movie> {
        val localMovies: MutableList<Movie> = mutableListOf()
        l.forEach {
            localMovies.add(
                Movie(it.id,it.poster_path,it.original_title,it.vote_average)
            )
            Log.d("Api Data",
                it.poster_path + " " +it.original_title+" " +it.vote_average
            )

        }
        return localMovies

    }

}