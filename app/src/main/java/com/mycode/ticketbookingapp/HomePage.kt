package com.mycode.ticketbookingapp


import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class HomePage : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#F3FFDE07")))
        verifyUserLoginDetails()
        navigate()
    }

    private fun verifyUserLoginDetails() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {    //Else intent to register page
//            var intent = Intent(this, WelcomeActivity::class.java)
//    /
            finish()
        }
    }

    private fun navigate(){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController=findNavController(R.id.fragmentContainerView)
        val appBarConfiguration= AppBarConfiguration(setOf(
            R.id.home,
            R.id.theatres,
            R.id.stream_movies,
            R.id.fragment_stream_movies
        ))

        setupActionBarWithNavController(navController,appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
    }
}