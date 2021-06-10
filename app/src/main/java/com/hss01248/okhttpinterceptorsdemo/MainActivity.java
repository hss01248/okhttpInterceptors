package com.hss01248.okhttpinterceptorsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hss01248.image.quality.Magick;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new Magick().getJPEGImageQuality()
        OkHttpClient client = buildClient();
        Request request = new Request.Builder()
                .url("https://www.baidu.com/")
                .get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
        
    }

    private OkHttpClient buildClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        initBuild(builder);

        return builder.build();
    }

    private void initBuild(OkHttpClient.Builder builder) {
        //builder.addInterceptor(new Inter)

    }
}