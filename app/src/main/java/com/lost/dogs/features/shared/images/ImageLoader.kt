package com.lost.dogs.features.shared.images

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageLoader {
    fun loadImage(url: String, imageView: ImageView){
        Glide.with(imageView.context).load(url).into(imageView)
    }
}