package com.mycode.ticketbookingapp.reviewsFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentReviewsBinding

class reviewsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentReviewsBinding =
            DataBindingUtil.inflate(inflater,R.layout.fragment_reviews,container,false)
        return binding.root
    }


}