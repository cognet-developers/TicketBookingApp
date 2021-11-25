package com.mycode.ticketbookingapp

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

import kotlinx.android.synthetic.main.genre.view.*
import kotlinx.android.synthetic.main.recyclerview.*
import java.util.*


class genre : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview)

        recycleView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true)
        fetchData()
    }
    private fun fetchData(){
        recycleView.adapter=GenreDetails()
    }
}

private class GenreDetails() : RecyclerView.Adapter<GenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val cellForRow=layoutInflater.inflate(R.layout.genre,parent,false)
        return GenreViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
//         holder.customView.moviename.text="okkanmani"
//        Picasso.with(holder.customView.context).load(R.drawable.ic_home).into(holder.customView.moviepic)
    }

    override fun getItemCount(): Int {
      return 5
    }

}
class GenreViewHolder(val customView: View):RecyclerView.ViewHolder(customView)
