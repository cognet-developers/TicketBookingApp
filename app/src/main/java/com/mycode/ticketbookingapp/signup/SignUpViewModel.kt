package com.mycode.ticketbookingapp.signup

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.mycode.ticketbookingapp.database.AuthRepository
import com.mycode.ticketbookingapp.model.TicketBookingApp

class SignUpViewModel(application: Application): ViewModel() {
    private var authRepository: AuthRepository
    private val _navigateToSignIn= MutableLiveData<Boolean>()
    val navigateToSignIn:LiveData<Boolean>
        get()=_navigateToSignIn

    fun navigateToSignIn(){
        _navigateToSignIn.value=true
    }

    fun navigateToSignInDone(){
        _navigateToSignIn.value=false
    }

    val firebaseUser: LiveData<FirebaseUser?>
        get()= authRepository.getFirebaseUserMutableLiveData()


    init{
        authRepository= AuthRepository(application)
    }

    fun register(username:String,email:String,password:String){
        authRepository.register(username,email, password)


    }
}

