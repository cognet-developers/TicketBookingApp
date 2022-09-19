package com.mycode.ticketbookingapp.homefragment.gridview

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycode.ticketbookingapp.network.GenresListProperty1
import com.mycode.ticketbookingapp.network.Movies
import com.mycode.ticketbookingapp.network.TMBDApi
import com.mycode.ticketbookingapp.network.TMBDConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.await
import java.time.Year

@RequiresApi(Build.VERSION_CODES.O)
class GridViewViewModel(constant:String) : ViewModel() {

    private val _feed = MutableLiveData<List<Movies>>()

    val feed: LiveData<List<Movies>>
        get() = _feed

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init{
        getGenres(constant)
    }
   @RequiresApi(Build.VERSION_CODES.O)
   fun getGenres(constant: String){

                coroutineScope.launch {
                    Log.d("Type",constant)
                    val getPropertiesDeferred: Call<GenresListProperty1>
                    if(constant=="ta") {
                        getPropertiesDeferred = TMBDApi.retrofitService.getLatestMoviesByLanguage(
                            TMBDConstants.API_KEY,
                            constant,
                            Year.now().toString()
                        )
                    }else if(constant=="en"){
                        getPropertiesDeferred = TMBDApi.retrofitService.getLatestMoviesByLanguage(
                            TMBDConstants.API_KEY,
                            constant,
                            Year.now().toString()
                        )

                    }else if(constant=="hi"){
                        getPropertiesDeferred = TMBDApi.retrofitService.getLatestMoviesByLanguage(
                            TMBDConstants.API_KEY,
                            constant,
                            Year.now().toString()
                        )

                    }else{
                         getPropertiesDeferred = TMBDApi.retrofitService.getLatestMovies(constant, TMBDConstants.API_KEY)
                    }
                    Log.d("Year",Year.now().toString())
                    try {

                        val listResult = getPropertiesDeferred.await()
                        val genresList=getLatestList(listResult.results)
                        _feed.value=genresList
                       Log.d("Api Data",genresList.toString())

                    }catch(e:Exception){
                        Log.d("Exception Grid","${e}")
                        }
                }
    }

    fun getLatestList(l: List<Movies>): List<Movies> {
        val localMovies: MutableList<Movies> = mutableListOf()
        l.forEach {
            if(it.backdrop_path!=null && it.poster_path!=null){
                localMovies.add(
                    Movies(
                        it.id,
                        it.poster_path,
                        it.backdrop_path,
                        it.original_title,
                    )
                )
                Log.d(
                    "Api Data",
                    it.poster_path + " " + it.original_title + " "
                )
            }
            }
        return localMovies

    }

}