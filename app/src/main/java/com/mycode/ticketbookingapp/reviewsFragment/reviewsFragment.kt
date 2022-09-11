package com.mycode.ticketbookingapp.reviewsFragment


import android.app.Activity
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
import com.mycode.ticketbookingapp.homefragment.Adapter
import com.mycode.ticketbookingapp.homefragment.gridmovie.GridMovieViewModel
import com.mycode.ticketbookingapp.network.Movie

class reviewsFragment : Fragment() {
    private val viewModel: reviewsViewModel by lazy {
        ViewModelProvider(this).get(reviewsViewModel::class.java)
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
        val activity: Activity = requireNotNull(this.activity)

        var movielists: List<ReviewData>
        val layoutmanager =LinearLayoutManager(activity,LinearLayoutManager.VERTICAL ,false)
        binding.recv.layoutManager=layoutmanager

        viewModel.feed.observe(viewLifecycleOwner, Observer {

            movielists=it
            // display(model)
            val adapter = ReviewAdapter(movielists)

            binding.recv.adapter = adapter

        })
        return binding.root
    }


}