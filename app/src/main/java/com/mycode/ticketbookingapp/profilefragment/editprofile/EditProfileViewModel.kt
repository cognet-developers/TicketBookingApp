package com.mycode.ticketbookingapp.profilefragment.editprofile

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.app.DatePickerDialog
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycode.ticketbookingapp.database.AuthRepository
import com.mycode.ticketbookingapp.model.TicketBookingApp
import kotlinx.android.synthetic.main.activity_editprofile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
class EditProfileViewModel(application: Application, activity: Activity): ViewModel() {
    private var alert: AlertDialog.Builder
    private var alert0: AlertDialog.Builder

    var _image= MutableLiveData<Boolean>()
    val image: LiveData<Boolean>
        get()=_image

    private var _spinner= MutableLiveData<Boolean>()
        val spinner: LiveData<Boolean>
            get()=_spinner

    private var _gender= MutableLiveData<String>()
    val gender: LiveData<String>
        get()=_gender

    private var _birthday= MutableLiveData<String>()
    val birthday: LiveData<String>
        get()=_birthday



        private var authRepository: AuthRepository

        val getData: LiveData<TicketBookingApp?>
            get()=authRepository.getUserDataMutableLiveData()

    val setData: LiveData<Boolean?>
        get()=authRepository.setUserDataMutableLiveData()

    val setImage:LiveData<String?>
     get()=authRepository.uploadedDataMutuableLiveData()

@SuppressLint("StaticFieldLeak")
       var act:Activity

        init {
            alert = AlertDialog.Builder(activity)
            alert0 = AlertDialog.Builder(activity)

            authRepository = AuthRepository(application)
            authRepository.getUserData()
             act = activity

        }




    fun Calendar(){
        val today = Calendar.getInstance()
        val year = today.get(Calendar.YEAR)
        val month =today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog= DatePickerDialog(act, { view, y, monthOfYear, dayOfMonth ->

            _birthday.value="$dayOfMonth/"+(monthOfYear+1)+"/$y"
        }, year, month, day)
        datePickerDialog.datePicker.maxDate= Date().time
        datePickerDialog.show()
    }


    fun imageFormating() {
        _image.value=true
    }
    var str:String?=null
    var str1:String?=null
    var str2:String?=null


    fun gender(){
        alert0.setTitle("Identity")
        val  options = arrayOf("Male","Female","Custom","Prefer not to say")
        alert0.setItems(options) { dialog, which ->
            dialog.dismiss()
            when (which) {
                0 -> {
                    _gender.value="Male"

                }
                1 -> {
                    _gender.value="Female"
                }
                2 -> {
                    _gender.value="Custom"
                }
                3->{
                    _gender.value="Prefer not to say"
                }


            }


        }

        alert0.show()

    }




    fun updateData(username:String,email:String,password:String,location:String,mobileNumber:String){
        viewModelScope.launch {
            if(setImage.value!=null) {
                str=setImage.value.toString()
            }else{
                str= getData.value?.profilePicture
            }

            if(getData.value?.birthday!=null){
                str1=getData.value?.birthday
            }else{
                str1=_birthday.value
            }

            if(getData.value?.gender!=null) {
                str2 = getData.value?.gender
            }else{
                str2=_gender.value
            }



            val ticketBookingApp=TicketBookingApp(username,email,password,str!!,location,mobileNumber,str1!!,str2!!)
            update(ticketBookingApp)
            _spinner.value=true

        }
         }

   private suspend fun update(ticketBookingApp: TicketBookingApp){
        withContext(Dispatchers.IO){
            authRepository.setUserData(ticketBookingApp)
        }
    }

    fun function(){
        authRepository.getUserData()
        _spinner.value=false
    }


    fun imageFormatingDone(dp:Uri){
        viewModelScope.launch {
            imageSet(dp)
            _image.value=false

        }
    }

    private suspend fun imageSet(dp:Uri){
        withContext(Dispatchers.IO) {
            authRepository.uploadImageToFirebaseStorage(dp)

        }
    }





}
