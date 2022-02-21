package com.mycode.ticketbookingapp

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.mycode.ticketbookingapp.model.TicketBookingApp
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


@SuppressLint("SetTextI18n")
@BindingAdapter("userDetails")
fun TextView.setUserDetails(item: String?) {
    item?.let {
        text=item
    }
}

@BindingAdapter("imgUrl")
fun bindImage(circleImageView: CircleImageView, imgUrl:String?) {
    imgUrl?.let{
           if(it!=""){
             Picasso.with(circleImageView.context).load(imgUrl).into(circleImageView)
        }
    }
}


