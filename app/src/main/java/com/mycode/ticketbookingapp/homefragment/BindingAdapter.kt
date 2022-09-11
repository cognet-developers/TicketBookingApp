package com.mycode.ticketbookingapp.homefragment

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mycode.ticketbookingapp.R

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imge="https://image.tmdb.org/t/p/w300"+imgUrl
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
