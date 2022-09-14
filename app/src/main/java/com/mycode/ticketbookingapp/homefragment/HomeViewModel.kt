package com.mycode.ticketbookingapp.homefragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.engine.Resource
import com.mycode.ticketbookingapp.network.Movies
import com.mycode.ticketbookingapp.network.TMBDApi
import com.mycode.ticketbookingapp.network.TMBDConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await

class HomeViewModel : ViewModel() {


    private val _homeFeed = MutableLiveData<List<HomeRecyclerViewModel>>()
    val homeFeed: LiveData<List<HomeRecyclerViewModel>>
        get() = _homeFeed


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init{
        getHomeFeed()
        Log.d("Started","Home")
    }
    fun getHomeFeed(){

        coroutineScope.launch {
            val homeFeedList = mutableListOf<HomeRecyclerViewModel>()
            val getPropertiesDeferred = TMBDApi.retrofitService.getTrending("day",TMBDConstants.API_KEY)
            try {

                val listResult = getPropertiesDeferred.await()
                val genresList=getTrend(listResult.results)
                homeFeedList.add(HomeRecyclerViewModel.Top(genresList))
                homeFeedList.add(HomeRecyclerViewModel.Bottom(TMBDConstants.latestMovies,"Latest Movies",TMBDConstants.IMG_BASE_URL+"","movie"))
                homeFeedList.add(HomeRecyclerViewModel.Bottom(TMBDConstants.topRated,"Top Rated Movies",TMBDConstants.IMG_BASE_URL+"","movie"))
                homeFeedList.add(HomeRecyclerViewModel.Bottom(TMBDConstants.popular,"Popular Movies",TMBDConstants.IMG_BASE_URL+"","movie"))
                homeFeedList.add(HomeRecyclerViewModel.Bottom(TMBDConstants.latestTvShows,"Latest Tv Shows",TMBDConstants.IMG_BASE_URL+"","tv"))
                homeFeedList.add(HomeRecyclerViewModel.Bottom(TMBDConstants.topRated,"Top Rated Tv Shows",TMBDConstants.IMG_BASE_URL+"","tv"))
                homeFeedList.add(HomeRecyclerViewModel.Bottom(TMBDConstants.popular,"Popular Tv Shows",TMBDConstants.IMG_BASE_URL+"","tv"))
                _homeFeed.postValue(homeFeedList)
                Log.d("Api Data",genresList.toString())

            }catch(e:Exception){
                Log.d("Exception","${e}")
            }
        }
    }

   fun getTrend(l:List<Movies>):List<Item>{
       val localMovies:MutableList<Item> = mutableListOf()
       l.forEach {
               localMovies.add(Item(it.id, it.original_title, it.backdrop_path))
       }

       return localMovies
   }



}