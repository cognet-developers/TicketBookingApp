package com.mycode.ticketbookingapp.signup

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.mycode.ticketbookingapp.database.AuthRepository

class SignUpViewModel(application: Application): ViewModel() {
    private var authRepository: AuthRepository
    private val _navigateToSignUp= MutableLiveData<Boolean>()
    val navigateToSignUp:LiveData<Boolean>
        get()=_navigateToSignUp


    fun navigateToSignUp(){
        _navigateToSignUp.value=true
    }

    fun navigateToSignUpDone(){
        _navigateToSignUp.value=false
    }

    val firebaseUser: LiveData<FirebaseUser?>
        get()= authRepository.getFirebaseUserMutableLiveData()


    init{
        authRepository= AuthRepository(application)
    }


    fun login(email:String,password:String){
        authRepository.login(email, password)
    }


}

