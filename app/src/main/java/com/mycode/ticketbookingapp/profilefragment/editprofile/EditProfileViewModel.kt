package com.mycode.ticketbookingapp.profilefragment.editprofile

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycode.ticketbookingapp.database.AuthRepository
import com.mycode.ticketbookingapp.model.TicketBookingApp
import java.util.*

class EditProfileViewModel(application: Application): ViewModel() {



        private var _spinner= MutableLiveData<Boolean>()
        val spinner: LiveData<Boolean>
            get()=_spinner

        private var authRepository: AuthRepository

        val getData: LiveData<TicketBookingApp?>
            get()=authRepository.getUserDataMutableLiveData()

    val setData: LiveData<Boolean?>
        get()=authRepository.setUserDataMutableLiveData()


        init {
            authRepository = AuthRepository(application)
            authRepository.getUserData()
        }
 fun Calender(){
//            val today = Calendar.getInstance()
//            val year = today.get(Calendar.YEAR)
//            val month =today.get(Calendar.MONTH)
//            val day = today.get(Calendar.DAY_OF_MONTH)
//
//            val datePickerDialog= DatePickerDialog(, { view, year, monthOfYear, dayOfMonth ->
//                findViewById<TextView>(R.id.birthday).setText("$dayOfMonth/$monthOfYear/$year")
//            }, year, month, day)
//                datePickerDialog.datePicker.maxDate=Date().time
//                datePickerDialog.show()
            }


    fun updateData(username:String,email:String,password:String,location:String,mobileNumber:String,birthday:String,gender:String){
        val ticketBookingApp=TicketBookingApp(username,email,password,"",location,mobileNumber,birthday,gender)
            authRepository.setUserData(ticketBookingApp)
            _spinner.value=true
        }

    fun function(){
        authRepository.getUserData()
        _spinner.value=false
    }




}