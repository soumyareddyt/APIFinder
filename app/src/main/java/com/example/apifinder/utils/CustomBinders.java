package com.example.apifinder.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Binders to use the helper methods from the xml
 */
public class CustomBinders {

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);
    }
}
