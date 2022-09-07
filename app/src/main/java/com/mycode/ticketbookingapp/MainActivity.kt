package com.mycode.ticketbookingapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.mycode.ticketbookingapp.databinding.ActivityMainBinding
import com.mycode.ticketbookingapp.signin.SignInFragment
import com.mycode.ticketbookingapp.signin.SignInViewModel
import com.mycode.ticketbookingapp.signin.SignInViewModelFactory
import kotlinx.android.synthetic.main.fragment_welcome.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration:AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        val navController=this.findNavController(R.id.myNavHostFragment)

        appBarConfiguration= AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfiguration)
    }


}