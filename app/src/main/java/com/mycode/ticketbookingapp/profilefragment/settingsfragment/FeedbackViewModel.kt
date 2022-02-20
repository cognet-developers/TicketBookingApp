package com.mycode.ticketbookingapp.profilefragment.settingsfragment

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.mycode.ticketbookingapp.database.AuthRepository

class FeedbackViewModel(application: Application): ViewModel() {
    private var authRepository: AuthRepository
    init{
        authRepository= AuthRepository(application)
    }

    fun sendfeedback(feedback:String){
       // Toast.makeText(context,"Your feedback has been sent successfully", Toast.LENGTH_SHORT).show()
    }
}