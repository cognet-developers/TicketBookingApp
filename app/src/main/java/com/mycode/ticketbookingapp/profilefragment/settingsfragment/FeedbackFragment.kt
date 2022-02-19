package com.mycode.ticketbookingapp.profilefragment.settingsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentFeedbackBinding


class FeedbackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentFeedbackBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_feedback,container,false)
        return binding.root
    }

    fun sendfeedback(){
        Toast.makeText(context,"Your feedback has been sent successfully",Toast.LENGTH_SHORT).show()
    }

}