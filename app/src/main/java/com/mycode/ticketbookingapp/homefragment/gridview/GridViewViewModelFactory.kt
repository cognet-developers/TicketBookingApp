package com.mycode.ticketbookingapp.homefragment.gridview

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GridViewViewModelFactory(private val application: Application, private val constant:String,private val category: String) : ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.N)
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GridViewViewModel::class.java)) {
            return GridViewViewModel(application,constant,category) as T
            //Returns the values from the fragment
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}







