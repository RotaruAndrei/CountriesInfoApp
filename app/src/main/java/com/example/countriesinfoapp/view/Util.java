package com.example.countriesinfoapp.view;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.countriesinfoapp.R;

public class Util {

    public static void insertImage (ImageView view, String url, CircularProgressDrawable c){

        RequestOptions rq = new RequestOptions()
                .placeholder(c)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(view.getContext())
                .setDefaultRequestOptions(rq)
                .load(url)
                .centerCrop()
                .fitCenter()
                .into(view);

    }

    public static CircularProgressDrawable getCircularDrawable (Context c){

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(c);
        circularProgressDrawable.setCenterRadius(50f);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.start();

        return circularProgressDrawable;
    }
}
