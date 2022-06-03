package com.peachjelly99.tuto1;

import static com.peachjelly99.tuto1.MainActivity.*;
import static com.peachjelly99.tuto1.ManualModeActivity.*;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment1 extends Fragment {
    private TextView t1;
    public static JsonPlaceHolderApi jsonPlaceHolderApi;
    public static int a=0;
    public static int b=0;
    public static int c=0;
    public static int d=0;
    public static int e=0;

    public static double temp=0;
    public static int humi=0;
    public static int waterLevel=0;
    public static int CO2=0;

    private boolean firstGetData=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_1, container, false);
        t1 = rootView.findViewById(R.id.textView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.interrupted()){
                    try{

                        Handler handler = new Handler(Looper.getMainLooper());

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                getPosts();
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://ziot.i4624.tk/ ").
                addConverterFactory(GsonConverterFactory.create()).build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void getPosts(){
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    t1.setText("Code : " + response.code());
                    return;
                }
                List<Post> gets = response.body();
                for (Post get : gets) {
                    String content = "";
                    content += "온도 : " + get.getTemperature() + "\n\n";
                    content += "습도 : " + get.getHumi() + "\n\n";
                    content += "CO2 : " + get.getCo2() + "\n\n";
                    content += "수위 : " + get.getWaterLevel();

                    a=get.getAutoMode();
                    b=get.getRelay1();
                    c=get.getRelay2();
                    d=get.getRelay3();
                    e=get.getRelay4();

                    temp=get.getTemperature();
                    humi=get.getHumi();
                    CO2=get.getCo2();
                    waterLevel=get.getWaterLevel();

                    if(!firstGetData) {
                        firstGetData = true;

                        if(a==1){
                            manualButton.setEnabled(false);
                            buttonEvent.setBackgroundColor(Color.rgb(105, 135, 118));
                            check = true;
                        }
                        if(b==1&&switchCompat1!=null){
                            switchCompat1.setChecked(true);
                            light.dismiss();
                        }
                        if(c==1&&switchCompat2!=null){
                            switchCompat2.setChecked(true);
                            light.dismiss();
                        }
                        if(d==1&&switchCompat3!=null){
                            switchCompat3.setChecked(true);
                            light.dismiss();
                        }
                        if(e==1&&switchCompat4!=null){
                            switchCompat4.setChecked(true);
                            light.dismiss();
                        }
                    }
                    t1.setText(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t1.setText(t.getMessage());
            }
        });
    }
}