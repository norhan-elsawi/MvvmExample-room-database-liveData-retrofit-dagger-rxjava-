package com.ibtikar.myfirstmvvmapplication.dataBindingDi.adapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso


class ImageBindingAdapter constructor(var picasso: Picasso) {


    private val baseImageUrl: String = "http://560057.youcanlearnit.net/services/images/"

    @BindingAdapter("bind:imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        picasso.load("$baseImageUrl$imageUrl").fit().into(view)
    }
}