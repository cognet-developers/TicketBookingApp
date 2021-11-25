package com.mycode.ticketbookingapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mycode.ticketbookingapp.ui.home.SettingsActivity


class feedback : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_feedback)
        findViewById<Button>(R.id.btn_feedback).setOnClickListener{
            sendfeedback()
            val intent= Intent(this, SettingsActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
    private fun sendfeedback(){
        val feedback=findViewById<EditText>(R.id.etfeedback).text.toString()
        val uid= FirebaseAuth.getInstance().uid?: ""
        val ref= FirebaseDatabase.getInstance().getReference("/User/$uid")
        ref.child("Feedback").setValue(feedback).addOnSuccessListener {
            Log.d("Feedback","Feedback sent successful")
        }
    }

}