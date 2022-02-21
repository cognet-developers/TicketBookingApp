package com.mycode.ticketbookingapp.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mycode.ticketbookingapp.HomePage
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentSignUpBinding
import com.mycode.ticketbookingapp.model.TicketBookingApp
import com.mycode.ticketbookingapp.signin.SignInFragmentDirections


class SignUpFragment : Fragment() {
    private var ticketBookingApp:TicketBookingApp = TicketBookingApp("","","","")
    private var authenticated:Boolean = false
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
                val intent= Intent(application, HomePage::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        })

        signUpViewModel.navigateToSignUp.observe(viewLifecycleOwner, Observer {
            if(it){
                this.findNavController().navigate(SignUpFragmentDirections.actionSignupPageToSigninPage())
                signUpViewModel.navigateToSignUpDone()
            }
        })
        return binding.root
    }




}