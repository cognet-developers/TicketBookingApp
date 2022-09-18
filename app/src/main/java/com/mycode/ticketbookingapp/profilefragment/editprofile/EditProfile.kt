package com.mycode.ticketbookingapp.profilefragment.editprofile

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.ActivityEditprofileBinding
import com.mycode.ticketbookingapp.model.TicketBookingApp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_editprofile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_welcome.view.*


class EditProfile : AppCompatActivity() {

    private var ticketBookingApp: TicketBookingApp = TicketBookingApp()
    private lateinit var editProfileViewModel:EditProfileViewModel
        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.N)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val binding = DataBindingUtil.setContentView<ActivityEditprofileBinding>(
                this,
                R.layout.activity_editprofile
            )

            birthdayText.showSoftInputOnFocus=false
            genderText.showSoftInputOnFocus=false

            supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F3FFDE07")))
            supportActionBar?.title = "EditProfile"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            val application: Application = requireNotNull(this).application
            val activity: Activity = this
            val viewModelFactory = EditProfileViewModelFactory(application, activity)
            editProfileViewModel =
                ViewModelProvider(this, viewModelFactory).get(EditProfileViewModel::class.java)
            binding.ticketBookingApp = ticketBookingApp
            binding.editProfileViewModel = editProfileViewModel
            binding.lifecycleOwner = this


            editProfileViewModel.getData.observe(this,Observer{
                if(it!=null){
                    loading_spinner.visibility=View.GONE
                }
            })
            editProfileViewModel.setData.observe(this, Observer {
                if (it ==true) {
                    loading_spinner.visibility = View.GONE
                    Toast.makeText(this, "Your profile is updated successfully", Toast.LENGTH_LONG)
                        .show()
                    editProfileViewModel.function()
                }
            })



            editProfileViewModel.image.observe(this, Observer {
                if (it == true) {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    startActivityForResult(intent, 0)
                }
            })




            editProfileViewModel.setImage.observe(this, Observer {
                if (it != null) {
                    loading_spinner.visibility = View.GONE
                }
            })

            editProfileViewModel.spinner.observe(this, Observer {
                if (it == true) {
                    loading_spinner.visibility = View.VISIBLE
                }
            })

        }



    var selectedPhotoUri: Uri? = null

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            Log.d("EditProfile", "Photo was selected")

            selectedPhotoUri = data.data


            editProfileViewModel.imageFormatingDone(selectedPhotoUri!!)
            Picasso.with(this).load(selectedPhotoUri).into(userdp1)
            loading_spinner.visibility=View.VISIBLE


        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean{
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }


}



