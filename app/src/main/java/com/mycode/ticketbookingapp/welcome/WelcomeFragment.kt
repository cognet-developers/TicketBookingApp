package com.mycode.ticketbookingapp.welcome


import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
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
import com.mycode.ticketbookingapp.databinding.FragmentWelcomeBinding
import com.mycode.ticketbookingapp.signup.SignUpViewModel
import com.mycode.ticketbookingapp.signup.SignUpViewModelFactory

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

    val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_welcome,container,false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        val application = requireNotNull(this.activity).application
        val viewModelFactory= WelcomeViewModelFactory(application)
        val welcomeViewModel = ViewModelProvider(this,viewModelFactory).get(WelcomeViewModel::class.java)


        binding.welcomeViewModel=welcomeViewModel
        binding.lifecycleOwner=this

    welcomeViewModel.navigateTo.observe(viewLifecycleOwner, Observer {
             if(it) {
                 this.findNavController().navigate(WelcomeFragmentDirections.actionWelcomePageToSigninPage())
                 welcomeViewModel.alreadyHaveAccountDone()
             }

    })


    return binding.root
}




}