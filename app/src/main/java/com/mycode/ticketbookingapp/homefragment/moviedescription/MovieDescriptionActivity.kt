package com.mycode.ticketbookingapp.homefragment.moviedescription

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.ActivityMovieDescriptionBinding
import com.mycode.ticketbookingapp.homefragment.gridview.GridViewActivity
import kotlinx.android.synthetic.main.activity_movie_description.*


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

        val viewModelFactory = MovieDescriptionViewModelFactory(id)
        Log.d("id", id)
        val movieDescriptionViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieDescriptionViewModel::class.java)
        binding.movieDescriptionViewModel=movieDescriptionViewModel
        binding.lifecycleOwner = this

        movieDescriptionViewModel.selectedProperty.observe(this, Observer {
            if(it!=null){
                binding.loadingSpinnerM.visibility= View.GONE
            }
        })

            binding.movieimg.setOnClickListener{

                Log.d("Videokey", "https://www.youtube.com/watch?v="+movieDescriptionViewModel.selectedvid.value)
                val utubeintent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+movieDescriptionViewModel.selectedvid.value))
                startActivity(utubeintent)
            }



    }

}