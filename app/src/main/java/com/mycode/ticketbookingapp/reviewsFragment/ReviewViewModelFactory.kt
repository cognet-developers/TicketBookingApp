package com.mycode.ticketbookingapp.reviewsFragment

import android.app.Activity
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycode.ticketbookingapp.homefragment.gridview.GridViewViewModel
import com.mycode.ticketbookingapp.profilefragment.ProfileViewModel

class ReviewViewModelFactory(private val application: Application,private val activity: Activity) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReviewsViewModel::class.java)) {
            return ReviewsViewModel(application,activity) as T
            //Returns the values from the fragment
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}
