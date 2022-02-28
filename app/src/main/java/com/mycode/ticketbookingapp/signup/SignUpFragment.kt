package com.mycode.ticketbookingapp.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mycode.ticketbookingapp.HomePage
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentSignUpBinding
import com.mycode.ticketbookingapp.model.TicketBookingApp


class SignUpFragment : Fragment() {
    private var ticketBookingApp:TicketBookingApp = TicketBookingApp()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentSignUpBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_sign_up,container,false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        val application = requireNotNull(this.activity).application
        val viewModelFactory= SignUpViewModelFactory(application)
        val signUpViewModel = ViewModelProvider(this,viewModelFactory).get(SignUpViewModel::class.java)

        binding.signUpViewModel=signUpViewModel
        binding.ticketbookingapp=ticketBookingApp
        binding.lifecycleOwner=this


        signUpViewModel.firebaseUser.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                Toast.makeText(application,"Welcome ${ticketBookingApp.username}!",Toast.LENGTH_LONG).show()
                val intent= Intent(application, HomePage::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        })

        signUpViewModel.navigateToSignIn.observe(viewLifecycleOwner, Observer {
            if(it){
                this.findNavController().navigate(SignUpFragmentDirections.actionSignupPageToSigninPage())
                signUpViewModel.navigateToSignInDone()
            }
        })
        return binding.root
    }




}