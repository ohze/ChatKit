package com.stfalcon.chatkit.sample.features.demo

import android.widget.ImageView

import com.squareup.picasso.Picasso
import com.stfalcon.chatkit.commons.ImageLoader

class PicasoImageLoader : ImageLoader {
    override fun loadImage(imageView: ImageView, url: String) {
        Picasso.get().load(url).into(imageView)
    }
}
