package com.mycode.ticketbookingapp.homefragment.gridview

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
import retrofit2.await

class GridViewViewModel(application: Application, constant:String,type: String) : ViewModel() {

    private val _feed = MutableLiveData<List<Movies>>()

    val feed: LiveData<List<Movies>>
        get() = _feed

    private val _navigateToSelectedProperty = MutableLiveData<Int?>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedProperty: LiveData<Int?>
        get() = _navigateToSelectedProperty

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init{
        getGenres(constant,type)
    }
   fun getGenres(constant: String,genreType:String){

                coroutineScope.launch {
                    Log.d("Type",genreType)
                    val getPropertiesDeferred = TMBDApi.retrofitService.getLatest(constant,genreType, TMBDConstants.API_KEY)
                    try {

                        val listResult = getPropertiesDeferred.await()
                        val genresList=getLatestList(listResult.results)
                        _feed.value=genresList
                       Log.d("Api Data",genresList.toString())

                    }catch(e:Exception){
                        Log.d("Exception","${e}")
                        }
                }
    }

    fun getLatestList(l: List<Movies>): List<Movies> {
        val localMovies: MutableList<Movies> = mutableListOf()
        l.forEach {
            localMovies.add(
                Movies(it.id,it.poster_path,it.backdrop_path,it.original_title,it.vote_average)
            )
            Log.d("Api Data",
                it.poster_path + " " +it.original_title+" " +it.vote_average
            )

        }
        return localMovies

    }

}