package com.mycode.ticketbookingapp.profilefragment.editprofile

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.mycode.ticketbookingapp.MainActivity
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.ActivityEditprofileBinding
import com.mycode.ticketbookingapp.model.TicketBookingApp
import kotlinx.android.synthetic.main.activity_editprofile.*
import kotlinx.android.synthetic.main.fragment_profile.*


class EditProfile : AppCompatActivity() {

    private var ticketBookingApp: TicketBookingApp = TicketBookingApp()
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val binding = DataBindingUtil.setContentView<ActivityEditprofileBinding>(
                this,
                R.layout.activity_editprofile
            )

            supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F3FFDE07")))
            supportActionBar?.title = "EditProfile"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)


            val application: Application = requireNotNull(this).application
            val viewModelFactory = EditProfileViewFactory(application)
            val editProfileViewModel =
                ViewModelProvider(this, viewModelFactory).get(EditProfileViewModel::class.java)
            binding.ticketBookingApp=ticketBookingApp
            binding.editProfileViewModel = editProfileViewModel
            binding.lifecycleOwner = this

            editProfileViewModel.setData.observe(this, Observer {
                if(it!=null){
                    loading_spinner.visibility=View.GONE
                    Toast.makeText(this,"Your profile is updated successfully",Toast.LENGTH_LONG).show()
                    editProfileViewModel.function()
                }
            })

            editProfileViewModel.spinner.observe(this, Observer {
                if(it==true){
                    loading_spinner.visibility=View.VISIBLE
               }
            })

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


////        findViewById<ProgressBar>(R.id.loading_spinner).visibility = View.GONE
////        findViewById<EditText>(R.id.userdp).setShowSoftInputOnFocus(false)
////


////




////                        if (user.profilepic != "") {
////                            findViewById<EditText>(R.id.name).setText(user.username)
////                            findViewById<EditText>(R.id.email).setText(user.email)
////                            Picasso.with(this@EditProfile).load(user.profilepic)
////                                .into(findViewById<CircleImageView>(R.id.userdp1))
////                            findViewById<EditText>(R.id.userdp).alpha = 0f
////                        } else {
////                            findViewById<EditText>(R.id.name).setText(user.username)
////                            findViewById<EditText>(R.id.email).setText(user.email)
////                        }
////                    }
////                }
////
////                override fun onCancelled(error: DatabaseError) {
////                    TODO("Not yet implemented")
////                }
////            })
////
////
////        findViewById<Button>(R.id.savechanges).setOnClickListener {
////            savechanges()
////
////
////    }
////        findViewById<CircleImageView>(R.id.userdp1).setOnClickListener {
////            val intent = Intent(Intent.ACTION_PICK)
////            intent.type = "image/*"
////            startActivityForResult(intent, 0)
////        }
////    }
////
////    var selectedPhotoUri: Uri? = null
////
////    @RequiresApi(Build.VERSION_CODES.P)
////    //If the image is selected to start the activity of gallery and assigning it to @selectedPhotoUri
////    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
////        super.onActivityResult(requestCode, resultCode, data)
////
////        if (requestCode == 0 && resultCode == AppCompatActivity.RESULT_OK && data != null) {
////            Log.d("RegisterActivity", "Photo was selected")
////
////            selectedPhotoUri = data.data
////
////            Picasso.with(this@EditProfile).load(selectedPhotoUri).into(findViewById<CircleImageView>(
////                R.id.userdp1
////            )).toString()
////            findViewById<EditText>(R.id.userdp).alpha = 0f //to no inflate image view
////
////            uploadImageToFirebaseStorage()
////
////        }
////    }
////
////    private fun uploadImageToFirebaseStorage() {
////        findViewById<ProgressBar>(R.id.loading_spinner).visibility = View.VISIBLE
////
////         val filename = UUID.randomUUID().toString()
////         val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
////
////        ref.putFile(selectedPhotoUri!!)
////            .addOnSuccessListener {
////                Log.d("EditProfile", "Successfully uploaded image: ${it.metadata?.path}")
////
////                ref.downloadUrl.addOnSuccessListener {
////                    Log.d("EditProfile", "File Location:$it")
////                    savechanges1(it.toString())
////                }
////            }
////    }
////
////private fun savechanges1(profilepicture:String){
////    val uid=FirebaseAuth.getInstance().uid
////    val ref1 = FirebaseDatabase.getInstance().getReference("/User/$uid")
////
////    ref1.addListenerForSingleValueEvent(object : ValueEventListener {
////        @RequiresApi(Build.VERSION_CODES.P)
////        override fun onDataChange(p0: DataSnapshot) {
////            val user = p0.getValue(UsersModel::class.java)
////            if (user != null) {
////                val fieldname = user.uid
////                val ref2 = database.getReference()
////                ref2.child("User/$fieldname/profilepic").setValue(profilepicture)
////
////            findViewById<ProgressBar>(R.id.loading_spinner).visibility = View.GONE
////
////            Toast.makeText(this@EditProfile, "Profile picture is updated", Toast.LENGTH_LONG).show()
////
////           }
////        }
////
////        override fun onCancelled(error: DatabaseError) {
////            TODO("Not yet implemented")
////        }
////    })
////}
////
////    private fun savechanges() {
////        findViewById<ProgressBar>(R.id.loading_spinner).visibility = View.VISIBLE
////
////        val uid=FirebaseAuth.getInstance().uid
////        val ref1 = FirebaseDatabase.getInstance().getReference("/User/$uid")
////
////        ref1.addListenerForSingleValueEvent(object : ValueEventListener {
////            @RequiresApi(Build.VERSION_CODES.P)
////            override fun onDataChange(p0: DataSnapshot) {
////                    val user = p0.getValue(UsersModel::class.java)
////                    if (user != null) {
////                        val fieldname = user.uid
////                        val ref2 = database.getReference()
////                        val Name = findViewById<EditText>(R.id.name).text.toString()
////                        val Email = findViewById<EditText>(R.id.email).text.toString()
////                        val location = findViewById<EditText>(R.id.location).text.toString()
////                        val mobileno = findViewById<EditText>(R.id.mobileno).text.toString()
////                        val birthday = findViewById<TextView>(R.id.birthday).text.toString()
////                        val gender = findViewById<EditText>(R.id.gender).text.toString()
////
////                        if (Name != user.username) {
////                            ref2.child("User/$fieldname/username").setValue(Name)
////                        }
////
////                        if (Email != user.email) {
////                            ref2.child("User/$fieldname/email").setValue(Email)
////                        }
////
////                        if (location != "") {
////                            ref2.child("User/$fieldname/location").setValue(location)
////                        }
////
////                        if (mobileno != "") {
////                            ref2.child("User/$fieldname/mobile no").setValue(mobileno)
////                        }
////
////                        if (birthday != "") {
////                            ref2.child("User/$fieldname/birthday").setValue(birthday)
////                        }
////
////                        if (gender != "") {
////                            ref2.child("User/$fieldname/birthday").setValue(gender)
////                        }
////                    }
////
////                findViewById<ProgressBar>(R.id.loading_spinner).visibility = View.GONE
////                Toast.makeText(this@EditProfile,"Profile is updated successfully",Toast.LENGTH_LONG).show()
////            }
////            override fun onCancelled(error: DatabaseError) {
////                TODO("Not yet implemented")
////            }
////        })
//   }
//}
//
//
//
//
//
//

