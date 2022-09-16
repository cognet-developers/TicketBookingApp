package com.mycode.ticketbookingapp.reviewsFragment


import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
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
import com.mycode.ticketbookingapp.homefragment.gridview.GridViewViewModel
import com.mycode.ticketbookingapp.homefragment.gridview.GridViewViewModelFactory

class ReviewsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val application:Application = requireNotNull(this.activity).application

        val activity:Activity= this.requireActivity()
        val viewModelFactory = ReviewViewModelFactory(application, activity)
        val viewModel: ReviewsViewModel by lazy {
            ViewModelProvider(this,viewModelFactory).get(ReviewsViewModel::class.java)
        }
        val binding = FragmentReviewsBinding.inflate(inflater)
        //var topiclist: List<String>

        binding.lifecycleOwner = this

        val layoutmanager =LinearLayoutManager(activity,LinearLayoutManager.VERTICAL ,false)
        binding.recv.layoutManager=layoutmanager
        //viewModel.listOfListMovies()

        val adapter = ReviewAdapter()

        binding.recv.adapter = adapter
        viewModel.feed.observe(viewLifecycleOwner, Observer {

            adapter.topic = it
            adapter.notifyDataSetChanged()


        })
        return binding.root
    }


}