package com.mycode.ticketbookingapp

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mycode.ticketbookingapp.network.TMBDConstants
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_editprofile.view.*


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

@BindingAdapter("imageUrl")
fun bindImage1(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imge=TMBDConstants.IMG_BASE_URL+imgUrl

//        val imgUri = imge.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imge)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}






