package com.mycode.ticketbookingapp.ui.auth.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mycode.ticketbookingapp.*
import com.mycode.ticketbookingapp.databinding.ActivityLoginBinding


class SignInActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        val binding:ActivityLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel= ViewModelProviders.of(this).get(SignInViewModel::class.java)
        binding.viewmodel=viewModel
        viewModel.authListener=this


    }

    override fun onStarted() {
        toast("Login Started")
    }

    override fun onSuccess() {
        toast("Data stored")
    }

    override fun onFailure(message:String) {
        toast(message)
    }
//        findViewById<Button>(R.id.Login).setOnClickListener{
//            performLogin()
//        }
//
//        findViewById<TextView>(R.id.SignupPage).setOnClickListener{
//            val intent= Intent(this, SignupActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun performLogin(){
//        val email=findViewById<EditText>(R.id.EmailId1).text.toString()
//        val password=findViewById<EditText>(R.id.password1).text.toString()
//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Please enter you email address or password", Toast.LENGTH_LONG)
//                .show()
//            return
//        }
//        Log.d("Login","Email and Password: "+email+"$password")
//
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
//            .addOnCompleteListener{
//                if(!it.isSuccessful) return@addOnCompleteListener
//                Toast.makeText(this,"Logged Successfully",Toast.LENGTH_LONG).show()
//                Log.d("Login","${it.result?.user?.uid}")
//                val intent= Intent(this, HomePage::class.java)
////                intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(intent)
//                finish()
//            }
//
//            .addOnFailureListener{
//                Toast.makeText(this,"${it.message}",Toast.LENGTH_LONG).show()
//            }
//
//
//    }
//
//    @Override
//    override fun onBackPressed() {
//        val intent = Intent(this@LoginActivity, WelcomeActivity::class.java)
//        startActivity(intent)
//        finish()
//    }

}