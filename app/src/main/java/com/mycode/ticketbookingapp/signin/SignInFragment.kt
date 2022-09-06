package com.mycode.ticketbookingapp.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.mycode.ticketbookingapp.HomePage
import com.mycode.ticketbookingapp.MainActivity
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentSignInBinding
import com.mycode.ticketbookingapp.model.TicketBookingApp
import kotlinx.android.synthetic.main.fragment_welcome.*

class SignInFragment : Fragment() {
    private var ticketBookingApp:TicketBookingApp= TicketBookingApp()
    companion object {
        private const val TAG="signIn"
        private const val RC_SIGN_IN = 78
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding: FragmentSignInBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in,container,false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        val application = requireNotNull(this.activity).application
        val viewModelFactory= SignInViewModelFactory(application)
        val signInViewModel = ViewModelProvider(this,viewModelFactory).get(SignInViewModel::class.java)





        binding.signInViewModel=signInViewModel
        binding.ticketbookingapp=ticketBookingApp
        binding.lifecycleOwner=this

        signInViewModel.firebaseUser.observe(viewLifecycleOwner, Observer{
            if(it!=null){
                Toast.makeText(application,"Welcome back!",Toast.LENGTH_LONG).show()
                val intent= Intent(application, HomePage::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        })

        signInViewModel.navigateToSignUp.observe(viewLifecycleOwner, Observer {
            if(it){
                this.findNavController().navigate(SignInFragmentDirections.actionSigninPageToSignupPage())
                signInViewModel.navigateToSignUpDone()
            }
        })


        return binding.root
    }



}