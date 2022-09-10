package com.mycode.ticketbookingapp.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    private val _navigateTo= MutableLiveData<Boolean>()
    val navigateTo: LiveData<Boolean>
        get()=_navigateTo

    fun alreadyHaveAccountNavigating(){
        _navigateTo.value=true

    }

    fun alreadyHaveAccountDone(){
        _navigateTo.value=false
    }

}