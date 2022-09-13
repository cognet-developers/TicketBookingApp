package com.mycode.ticketbookingapp.reviewsFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycode.ticketbookingapp.network.Movies
import com.mycode.ticketbookingapp.network.TMBDApi
import com.mycode.ticketbookingapp.network.TMBDConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await

class ReviewsViewModel: ViewModel() {
    private val _feed = MutableLiveData<List<ReviewData>>()

    val feed: LiveData<List<ReviewData>>
        get() = _feed

    var viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    val reviewdata:MutableList<ReviewData> = mutableListOf()
    val genrename:List<String> = listOf(TMBDConstants.ACTION,TMBDConstants.COMEDY,TMBDConstants.FANTASY,TMBDConstants.HISTORY,TMBDConstants.CRIME,TMBDConstants.MUSIC,TMBDConstants.DOCUMENTARY)

    val topic:List<String> = listOf("ACTION","COMEDY","FANTASY","HISTORY","CRIME","MUSIC","DOCUMENTARY")

    var index:Int=0

    init {
        listOfListMovies()
    }
        fun listOfListMovies(){
        coroutineScope.launch {
            genrename.forEach {
                var getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(
                    it,
                    TMBDConstants.API_KEY
                )
                try {

                    var listResult = getPropertiesDeferred.await()
                    val genresList = getGenresList(listResult.items)
                    reviewdata.add(ReviewData(topic[index], genresList))
                    Log.d("Api Data", listResult.toString())
                    index=index+1

                } catch (e: Exception) {
                    Log.d("Exception", "${e}")
                }
            }
        }
        Log.i("Review",reviewdata.toString())

        _feed.value=reviewdata
    }

    fun getGenresList(l: List<Movies>): List<Movies> {
        val localMovies: MutableList<Movies> = mutableListOf()
        l.forEach {
            localMovies.add(
                Movies(it.id,it.poster_path,it.original_title,it.vote_average)
            )
            Log.d("Api Data3",
                it.poster_path + " " +it.original_title+" " +it.vote_average
            )

        }
        return localMovies

    }
}