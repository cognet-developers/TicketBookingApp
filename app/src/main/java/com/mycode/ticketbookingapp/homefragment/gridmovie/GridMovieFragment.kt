package com.mycode.ticketbookingapp.homefragment.gridmovie

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentGridMovieBinding
import com.mycode.ticketbookingapp.homefragment.Adapter
import com.mycode.ticketbookingapp.network.List


class GridMovieFragment : Fragment() {


    private val viewModel: GridMovieViewModel by lazy {
        ViewModelProvider(this).get(GridMovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGridMovieBinding =
            DataBindingUtil.inflate(inflater,R.layout.fragment_grid_movie,container,false)

        val activity: Activity = requireNotNull(this.activity)

        binding.lifecycleOwner = this

        var layoutManager = GridLayoutManager(activity, 2)
        binding.recyclverview.layoutManager=layoutManager


        var movielists: kotlin.collections.List<List>
        viewModel.feed.observe(viewLifecycleOwner, Observer {

            movielists=it
            // display(model)
            val adapter = Adapter(movielists)

            binding.recyclverview.adapter = adapter

        })



//        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer{
//
//
//            if ( null != it ) {
//                // Must find the NavController from the Fragment
//                this.findNavController().navigate(GridMovieFragmentDirections.actionGridMovieFragmentToMoviediscFragment(it))
//                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
//                viewModel.movieDetailscomplete()
//            }
//        })


        return binding.root
    }

}