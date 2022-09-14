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
                val trendList=getTrend(listResult.results)
                homeFeedList.add(HomeRecyclerViewModel.Top(trendList))
                val latestList1=getLatest1()
                val latestList2=getLatest2()
                val latestList3=getLatest3()
                homeFeedList.add(HomeRecyclerViewModel.Bottom(latestList1))
                homeFeedList.add(HomeRecyclerViewModel.Bottom(latestList2))
                homeFeedList.add(HomeRecyclerViewModel.Bottom(latestList3))
                _homeFeed.postValue(homeFeedList)
                Log.d("Api Data",trendList.toString())

            }catch(e:Exception){
                Log.d("Exception","${e}")
            }
        }
    }

    fun getLatest1():List<Item1>{
        val localMovies:MutableList<Item1> = mutableListOf()

        localMovies.add(Item1(TMBDConstants.latestMovies,"Latest Movies",TMBDConstants.IMG_BASE_URL+"/b9ykj4v8ykjRoGB7SpI1OuxblNU.jpg","latest"))
        localMovies.add(Item1(TMBDConstants.topRated,"Top Rated Movies",TMBDConstants.IMG_BASE_URL+"/ixgnqO8xhFMb1zr8RRFsyeZ9CdD.jpg","latest"))

        return localMovies
    }


    fun getLatest2():List<Item1>{
        val localMovies:MutableList<Item1> = mutableListOf()
        localMovies.add(Item1(TMBDConstants.popular,"Popular Movies",TMBDConstants.IMG_BASE_URL+"/MbP1pIUKQcZaC1XCwSomuiLrva.jpg","latest"))
        localMovies.add(Item1(TMBDConstants.tamil,"Latest Tamil",TMBDConstants.IMG_BASE_URL+"/pBRkO5GHJqDB9D0fbumL5235JfJ.jpg","language"))

        return localMovies
    }

    fun getLatest3():List<Item1>{
        val localMovies:MutableList<Item1> = mutableListOf()
        localMovies.add(Item1(TMBDConstants.english,"Latest English",TMBDConstants.IMG_BASE_URL+"/9f5sIJEgvUpFv0ozfA6TurG4j22.jpg","language"))
        localMovies.add(Item1(TMBDConstants.hindi,"Latest Hindi",TMBDConstants.IMG_BASE_URL+"/3O3oTeFERsfNHjwkMHQV26uMf7x.jpg","language"))


        return localMovies
    }

   fun getTrend(l:List<Movies>):List<Item>{
       val localMovies:MutableList<Item> = mutableListOf()
       l.forEach {
               localMovies.add(Item(it.id,it.original_title, it.backdrop_path))
       }

       return localMovies
   }



}