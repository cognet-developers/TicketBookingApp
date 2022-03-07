package com.mycode.ticketbookingapp.profilefragment

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.mycode.ticketbookingapp.database.AuthRepository
import com.mycode.ticketbookingapp.model.TicketBookingApp

class ProfileViewModel(application:Application,activity: Activity): ViewModel() {
    private var alert:AlertDialog.Builder

    private var _navigateToEditProfile=MutableLiveData<Boolean>()
    val navigateToEditProfile:LiveData<Boolean>
    get()=_navigateToEditProfile

    private var authRepository: AuthRepository
    val loggedUser: LiveData<Boolean?>
        get()=authRepository.getUserLoggedMutableLiveData()

    private val _navigateToSettings=MutableLiveData<Boolean?>()
    val navigateTosettings:LiveData<Boolean?>
        get()=_navigateToSettings

    val value=MediatorLiveData<TicketBookingApp>()

    val getData:LiveData<TicketBookingApp?>
    get()=authRepository.getUserDataMutableLiveData()



    init {
        authRepository = AuthRepository(application)
        alert = AlertDialog.Builder(activity)
        authRepository.getUserData()
        value.addSource(getData,value::setValue)




    }

    fun function(){
        authRepository.getUserData()
    }

    fun logOutAlertDialogBox(){

            alert.setMessage("Are you sure you want to Signout?")
                .setPositiveButton("YES", DialogInterface.OnClickListener { dialog, which ->
                    authRepository.signOut()
                }).setNegativeButton("NO", null)

            val alert1: AlertDialog= alert.create()
                alert1.show()
        }
    fun navigateToEditProfile(){
        _navigateToEditProfile.value=true
    }

    fun navigateToEditProfileDone(){
        _navigateToEditProfile.value=false
    }

    fun navigateTosettings(){

        _navigateToSettings.value=true
    }

    fun navigateTosettingsdone(){
        _navigateToSettings.value=false
    }




}