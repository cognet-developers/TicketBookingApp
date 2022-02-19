package com.mycode.ticketbookingapp.signin



import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.mycode.ticketbookingapp.database.AuthRepository
import com.mycode.ticketbookingapp.model.TicketBookingApp

class SignInViewModel(application: Application):ViewModel(){
    private val _navigateToSignIn= MutableLiveData<Boolean>()
    val navigateToSignIn:LiveData<Boolean>
        get()=_navigateToSignIn



    fun navigateToSignIn(){
        _navigateToSignIn.value=true
    }

    fun navigateToSignInDone(){
        _navigateToSignIn.value=false
    }
    private var authRepository: AuthRepository
    val firebaseUser:LiveData<FirebaseUser?>
        get()=authRepository.getFirebaseUserMutableLiveData()

//    val firebaseData: LiveData<String?>
//    get()=authRepository.setUserDataMutableLiveData()

    init{
        authRepository= AuthRepository(application)


    }


    fun register(username:String,email:String,password:String){
        authRepository.register(username,email, password)

    }




}