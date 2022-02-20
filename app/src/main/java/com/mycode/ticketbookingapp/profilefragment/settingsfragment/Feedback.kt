package com.mycode.ticketbookingapp.profilefragment.settingsfragment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.ActivityFeedbackBinding


class Feedback : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        val binding=DataBindingUtil.setContentView<ActivityFeedbackBinding>(this,R.layout.activity_feedback)
        binding.root
    }

}