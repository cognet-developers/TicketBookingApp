package com.mycode.ticketbookingapp.homefragment.moviedescription

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.ActivityMovieDescriptionBinding
import com.mycode.ticketbookingapp.homefragment.Adapter
import com.mycode.ticketbookingapp.homefragment.MovieListener
import com.mycode.ticketbookingapp.homefragment.gridview.GridViewActivity
import com.mycode.ticketbookingapp.homefragment.gridview.GridViewActivityArgs
import com.mycode.ticketbookingapp.homefragment.gridview.GridViewViewModel
import com.mycode.ticketbookingapp.homefragment.gridview.GridViewViewModelFactory


class MovieDescriptionActivity:AppCompatActivity() {


 private lateinit var id:String
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.setContentView<ActivityMovieDescriptionBinding>(
            this,
            R.layout.activity_movie_description
        )


        supportActionBar!!.hide()

        id=intent.getStringExtra(GridViewActivity.USER_KEY).toString()

        val application: Application = requireNotNull(this).application
        val viewModelFactory = MovieDescriptionViewModelFactory(application, id)
        Log.d("id", id)
        val movieDescriptionViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieDescriptionViewModel::class.java)
        binding.movieDescriptionViewModel=movieDescriptionViewModel
        binding.lifecycleOwner = this

    }

}