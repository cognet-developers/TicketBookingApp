package com.mycode.ticketbookingapp.homefragment.moviedescription

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
import java.util.stream.Collectors
import kotlin.system.exitProcess

class MovieDescriptionViewModel(application: Application, id: String):ViewModel() {
    private val _selectedProperty = MutableLiveData<MovieDetails>()

    val selectedProperty: LiveData<MovieDetails>
        get() = _selectedProperty

    private val _selectedPropertyGenres = MutableLiveData<String>()

    val selectedPropertyGenres: LiveData<String>
        get() = _selectedPropertyGenres




    var viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getMovieDetails(id)
    }

    fun getMovieDetails(id:String){
        coroutineScope.launch {
            val getMovieDetails =
                TMBDApi.retrofitService.getMovieDetails(id, TMBDConstants.API_KEY)

            val getTrailerVideo =
                TMBDApi.retrofitService.getMovieTrailer(id,TMBDConstants.API_KEY)
            try {

                val listResult = getMovieDetails.await()
                _selectedProperty.value=listResult
                Log.d("ApiData", listResult.toString())

                val genre=getGenres(listResult.genres)
                _selectedPropertyGenres.value=genre.toString()

                val videoResult = getTrailerVideo.await()
                val local:MutableList<String> = mutableListOf()
                videoResult.results.forEach {
                     local.add(it.key)
                }

                val key=local[0]
                Log.d("Videokey",key)

            } catch (e: Exception) {
                Log.d("Exception", "${e}")
            }
        }
    }
    fun getGenres(genres: List<genres>):List<String> {
        val local: MutableList<String> = mutableListOf()
        var index = 0
        genres.forEach {
            if(index<3) {
                val str: String = it.name
                local.add(str)
                Log.d(
                    "Api Data",
                    it.name
                )
            }
            index=index+1

        }
     return local
    }


}
