package com.mycode.ticketbookingapp.reviewsFragment


import android.annotation.SuppressLint
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
import com.mycode.ticketbookingapp.databinding.FragmentHomeBinding
import com.mycode.ticketbookingapp.databinding.FragmentReviewsBinding
import com.mycode.ticketbookingapp.profilefragment.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_reviews.*

class ReviewsFragment : Fragment() {


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

  //       val viewModel: ReviewsViewModel by lazy {
//            ViewModelProvider(this,viewModelFactory).get(ReviewsViewModel::class.java)
//        }
        val binding: FragmentReviewsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_reviews,container,false)

        val viewModelFactory = ReviewViewModelFactory()
        val viewModel = ViewModelProvider(this,viewModelFactory).get(ReviewsViewModel::class.java)


        binding.reviewViewModel=viewModel

        //var topiclist: List<String>

        binding.lifecycleOwner = this

//        val layoutmanager =LinearLayoutManager(activity,LinearLayoutManager.VERTICAL ,false)
//        binding.recv.layoutManager=layoutmanager
//        //viewModel.listOfListMovies()

        val adapter = ReviewAdapter()

        binding.recv.adapter = adapter
        viewModel.feed.observe(viewLifecycleOwner, Observer {
            binding.loadingSpinnerR.visibility=View.GONE
            adapter.topic = it
            adapter.notifyDataSetChanged()


        })
        return binding.root
    }


}