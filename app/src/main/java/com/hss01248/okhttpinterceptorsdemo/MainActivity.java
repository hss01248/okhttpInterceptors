package com.hss01248.okhttpinterceptorsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hss01248.image.quality.Magick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new Magick().getJPEGImageQuality()
    }
}