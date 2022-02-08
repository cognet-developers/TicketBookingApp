package com.mycode.ticketbookingapp.profilefragment.editprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentEditprofileBinding



class EditProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        val binding: FragmentEditprofileBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_editprofile,container,false)
      return binding.root
    }


}