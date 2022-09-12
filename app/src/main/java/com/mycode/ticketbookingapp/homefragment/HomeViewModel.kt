package com.mycode.ticketbookingapp.homefragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {


    private val _action= MutableLiveData<Boolean>()
    val action: LiveData<Boolean>
        get()=_action

    fun Action(){
        _action.value=true
    }

    fun ActionDone(){
        _action.value=false
    }



}