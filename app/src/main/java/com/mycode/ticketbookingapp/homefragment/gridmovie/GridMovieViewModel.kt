package com.mycode.ticketbookingapp.homefragment.gridmovie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycode.ticketbookingapp.network.TMBDApi
import com.mycode.ticketbookingapp.network.TMBDConstants
import com.mycode.ticketbookingapp.network.List
import com.mycode.ticketbookingapp.network.MovieDetailsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await

class GridMovieViewModel: ViewModel() {

    private val _feed = MutableLiveData<kotlin.collections.List<List>>()

    val feed: LiveData<kotlin.collections.List<List>>
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
                Log.d("Api Data",listResult.toString())

            }catch(e:Exception){
                Log.d("Exception","${e}")
            }
        }
        //var viewModelJob = Job()
       // val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

                coroutineScope.launch {
                    var getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(TMBDConstants.ACTION, TMBDConstants.API_KEY)
                    try {

                        var listResult = getPropertiesDeferred.await()
                        val genresList=getGenresList(listResult.items)
                        _feed.value=genresList
                       Log.d("Api Data",genresList.toString())

                    }catch(e:Exception){
                        Log.d("Exception","${e}")
                        }
                }
    }

    fun movieDetails(id: Int) {
        _navigateToSelectedProperty.value = id
    }
    fun movieDetailscomplete() {
        _navigateToSelectedProperty.value = null
    }

    fun getGenresList(l: kotlin.collections.List<List>): kotlin.collections.List<List> {
        val localMovies: MutableList<List> = mutableListOf()
        l.forEach {
            localMovies.add(
                List(it.id,it.poster_path,it.original_title,it.vote_average)
            )
            Log.d("Api Data",
                it.poster_path + " " +it.original_title+" " +it.vote_average
            )

        }
        return localMovies

    }

}