package com.stfalcon.chatkit.sample.features.demo;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;

public class PicasoImageLoader implements ImageLoader {
    @Override
    public void loadImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);
    }
}
