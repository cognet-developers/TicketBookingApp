package com.mycode.ticketbookingapp.streamfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentStreamMoviesBinding


class StreamFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentStreamMoviesBinding =
            DataBindingUtil.inflate(inflater,R.layout.fragment_stream_movies,container,false)
        return binding.root
    }


}