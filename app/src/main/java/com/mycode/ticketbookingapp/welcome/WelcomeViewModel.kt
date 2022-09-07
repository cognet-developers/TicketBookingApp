package com.mycode.ticketbookingapp.welcome

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.mycode.ticketbookingapp.database.AuthRepository

class WelcomeViewModel(private var application: Application): ViewModel() {
    private var authRepository: AuthRepository
private val _navigateTo= MutableLiveData<Boolean>()
    val navigateTo:LiveData<Boolean>
    get()=_navigateTo


    fun alreadyHaveAccountNavigating(){
        _navigateTo.value=true

    }

    fun alreadyHaveAccountDone(){
        _navigateTo.value=false
    }


    val firebaseUser:LiveData<FirebaseUser?>
        get()=authRepository.getFirebaseUserMutableLiveData()

    init{
        authRepository= AuthRepository(application)
    }

    fun signInWithGoogle(idToken: String) {
        Log.i("MainActivity",idToken)
        authRepository.firebaseAuthWithGoogle(idToken)
    }

}