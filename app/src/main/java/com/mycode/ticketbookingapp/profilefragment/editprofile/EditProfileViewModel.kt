package com.mycode.ticketbookingapp.profilefragment.editprofile

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Application
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.app.ActivityCompat.startIntentSenderForResult
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mycode.ticketbookingapp.database.AuthRepository
import com.mycode.ticketbookingapp.model.TicketBookingApp
import com.squareup.picasso.Picasso
import java.util.*

class EditProfileViewModel(application: Application): ViewModel() {

    var _image= MutableLiveData<Boolean>()
    val image: LiveData<Boolean>
        get()=_image

    private var _spinner= MutableLiveData<Boolean>()
        val spinner: LiveData<Boolean>
            get()=_spinner

        private var authRepository: AuthRepository

        val getData: LiveData<TicketBookingApp?>
            get()=authRepository.getUserDataMutableLiveData()

    val setData: LiveData<Boolean?>
        get()=authRepository.setUserDataMutableLiveData()

    val setImage:LiveData<String?>
     get()=authRepository.uploadedDataMutuableLiveData()


    lateinit var datePickerDialog:DatePickerDialog


        init {
            authRepository = AuthRepository(application)
            authRepository.getUserData()

        }
 @RequiresApi(Build.VERSION_CODES.N)
 fun Calender(){
            val today = Calendar.getInstance()
            val year = today.get(Calendar.YEAR)
            val month =today.get(Calendar.MONTH)
            val day = today.get(Calendar.DAY_OF_MONTH)


//            val datePickerDialog= DatePickerDialog(, { view, year, monthOfYear, dayOfMonth ->
//
//            }, year, month, day)
                datePickerDialog.datePicker.maxDate=Date().time
                datePickerDialog.show()
            }



    fun imageFormating() {
        _image.value=true
    }
    var str:String?=null

    fun updateData(username:String,email:String,password:String,location:String,mobileNumber:String,birthday:String,gender:String){
           if(setImage.value!=null) {
             str=setImage.value.toString()
           }else{
               str= getData.value?.profilePicture
           }

        val ticketBookingApp=TicketBookingApp(username,email,password,str!!,location,mobileNumber,birthday,gender)
            authRepository.setUserData(ticketBookingApp)
            _spinner.value=true
        }

    fun function(){
        authRepository.getUserData()
        _spinner.value=false
    }


    fun imageFormatingDone(dp:Uri){
        authRepository.uploadImageToFirebaseStorage(dp)
        _image.value=false
    }





}
