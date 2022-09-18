package com.mycode.ticketbookingapp.homefragment.gridview

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.ActivityGridViewBinding
import com.mycode.ticketbookingapp.homefragment.moviedescription.MovieDescriptionActivity


class GridViewActivity : AppCompatActivity() {

    private lateinit var constant:String
    private lateinit var name:String

    companion object{
        val USER_KEY="key"
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityGridViewBinding>(this, R.layout.activity_grid_view)

        constant=intent.getStringExtra("1").toString()
        name=intent.getStringExtra("2").toString()

        supportActionBar?.setTitle(name)

        val viewModelFactory = GridViewViewModelFactory(constant)
        Log.d("Type",constant)
        val gridViewViewModel = ViewModelProvider(this, viewModelFactory).get(GridViewViewModel::class.java)
        binding.gridViewViewModel = gridViewViewModel
        binding.lifecycleOwner = this

       val adapter= Adapter(MovieListener { it ->
           val intent = Intent(this, MovieDescriptionActivity::class.java)
           intent.putExtra(USER_KEY, it.toString())
           Log.d("id",it.toString())
           startActivity(intent)

       })

        binding.recyclerView.adapter=adapter

        gridViewViewModel.feed.observe(this, Observer {
            it?.let {
                binding.loadingSpinnerG.visibility= View.GONE
                adapter.submitList(it)
                adapter.notifyDataSetChanged()

            }
        })



    }

}