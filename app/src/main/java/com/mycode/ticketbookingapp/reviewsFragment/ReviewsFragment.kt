package com.mycode.ticketbookingapp.reviewsFragment


import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentReviewsBinding
import com.mycode.ticketbookingapp.homefragment.MovieListener

class ReviewsFragment : Fragment() {
    private val viewModel: ReviewsViewModel by lazy {
        ViewModelProvider(this).get(ReviewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentReviewsBinding =
            DataBindingUtil.inflate(inflater,R.layout.fragment_reviews,container,false)
        //var topiclist: List<String>

        binding.lifecycleOwner = this

        val layoutmanager =LinearLayoutManager(activity,LinearLayoutManager.VERTICAL ,false)
        binding.recv.layoutManager=layoutmanager
        viewModel.feed.observe(viewLifecycleOwner, Observer {


            val adapter = ReviewAdapter(it)

            binding.recv.adapter = adapter

        })
        return binding.root
    }


}