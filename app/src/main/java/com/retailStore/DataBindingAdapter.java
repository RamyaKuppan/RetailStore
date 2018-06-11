package com.retailStore;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

public class DataBindingAdapter {

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
