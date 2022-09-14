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
import retrofit2.Call
import retrofit2.await

class GridViewViewModel(application: Application, constant:String,category: String) : ViewModel() {

    private val _feed = MutableLiveData<List<Movies>>()

    val feed: LiveData<List<Movies>>
        get() = _feed

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init{
        getGenres(constant,category)
    }
   fun getGenres(constant: String,category:String){

                coroutineScope.launch {
                    Log.d("Type",category+constant)
                    val getPropertiesDeferred: Call<GenresListProperty1>
                    if(category=="latest") {
                         getPropertiesDeferred = TMBDApi.retrofitService.getLatestMovies(constant, TMBDConstants.API_KEY)
                    }else{
                        getPropertiesDeferred=TMBDApi.retrofitService.getLatestMoviesByLanguage(TMBDConstants.API_KEY,constant)
                    }
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