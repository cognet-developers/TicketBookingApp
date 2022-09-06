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

    private lateinit var signinViewModel: SignInViewModel
    companion object {
        private const val TAG="signIn"
        private const val RC_SIGN_IN = 78
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("122689396536-q8i8uighf5fdno6rc47gnr2kltomb38u.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        val navController=this.findNavController(R.id.myNavHostFragment)

        btngoogle.setOnClickListener{
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }



        appBarConfiguration= AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfiguration)

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.w(TAG, "Signup" + requestCode)

        if (requestCode == RC_SIGN_IN) {

            // this task is responsible for getting ACCOUNT SELECTED
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                //Toast.makeText(this, "processing", Toast.LENGTH_SHORT).show()
                signinViewModel.signInWithGoogle(account.idToken!!)

                Toast.makeText(this, "Signed In Successfully", Toast.LENGTH_SHORT).show()

            } catch (e: ApiException) {
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

}