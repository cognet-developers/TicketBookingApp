package com.mycode.ticketbookingapp.reviewsFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import com.mycode.ticketbookingapp.localDatabase.MovieDatabase.Companion.getInstance
import com.mycode.ticketbookingapp.localRepository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ReviewsViewModel(application: Application) : ViewModel() {

    var viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val database = getInstance(application)
    val movieRepository = MovieRepository(database)

    init {
        coroutineScope.launch {
            movieRepository.getMovie()
        }
    }

    val feed = movieRepository.movies
}






