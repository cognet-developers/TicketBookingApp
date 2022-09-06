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
	private val _navigateToSignUp= MutableLiveData<Boolean>()
	val navigateToSignUp:LiveData<Boolean>
		get()=_navigateToSignUp



	fun navigateToSignUp(){
		_navigateToSignUp.value=true
	}

	fun navigateToSignUpDone(){
		_navigateToSignUp.value=false
	}
	private var authRepository: AuthRepository
	val firebaseUser:LiveData<FirebaseUser?>
		get()=authRepository.getFirebaseUserMutableLiveData()

	init{
		authRepository= AuthRepository(application)
	}

	fun login(email:String,password:String){
		authRepository.login(email, password)
	}
}