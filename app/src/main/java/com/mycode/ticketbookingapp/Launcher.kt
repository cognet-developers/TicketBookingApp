package com.mycode.ticketbookingapp

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mycode.ticketbookingapp.database.AuthRepository

class Launcher :AppCompatActivity(){
//    private lateinit var authRepository: AuthRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val applica = requireNotNull(this.application)
//        authRepository= AuthRepository(applica)
//        authRepository.getFirebaseUserMutableLiveData().observe(this, Observer {
//        })

        val uid = FirebaseAuth.getInstance().uid
        if(uid!=null) {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
            finish()
        }else{

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}