package com.peachjelly99.tuto1;

import static com.peachjelly99.tuto1.Fragment1.*;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManualModeActivity extends AppCompatActivity {
    public static Dialog light, water, air, heating;
    public static SwitchCompat switchCompat1;
    public static SwitchCompat switchCompat2;
    public static SwitchCompat switchCompat3;
    public static SwitchCompat switchCompat4;

    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_mode);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button main = (Button)findViewById(R.id.button20);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        switchCompat1=findViewById(R.id.Light);
        switchCompat2=findViewById(R.id.WaterLevel);
        switchCompat3=findViewById(R.id.Fan);
        switchCompat4=findViewById(R.id.Heater);



        light = new Dialog(ManualModeActivity.this);
        light.requestWindowFeature(Window.FEATURE_NO_TITLE);
        light.setContentView(R.layout.dialog01);

        water = new Dialog(ManualModeActivity.this);       // Dialog 초기화
        water.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        water.setContentView(R.layout.dialog02);

        air = new Dialog(ManualModeActivity.this);       // Dialog 초기화
        air.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        air.setContentView(R.layout.dialog03);

        heating = new Dialog(ManualModeActivity.this);       // Dialog 초기화
        heating.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        heating.setContentView(R.layout.dialog04);

        findViewById(R.id.Light).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog01(); // 아래 showDialog01() 함수 호출
            }
        });

        findViewById(R.id.WaterLevel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog02(); // 아래 showDialog01() 함수 호출
            }
        });

        findViewById(R.id.Fan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog03(); // 아래 showDialog01() 함수 호출
            }
        });
        findViewById(R.id.Heater).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog04(); // 아래 showDialog01() 함수 호출
            }
        });
    }
    public void showDialog01(){
        light.show();

        Button noBtn = light.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchCompat1.setChecked(false);
                light.dismiss();
                b=0;
                createPost(a,b,c,d,e);
            }
        });
        light.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchCompat1.setChecked(true);
                light.dismiss();
                b=1;
                createPost(a,b,c,d,e);
            }
        });

    }
    public void showDialog02(){
        water.show();

        Button noBtn = water.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchCompat2.setChecked(false);
                water.dismiss();
                c=0;
                createPost(a,b,c,d,e);
            }
        });
        water.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchCompat2.setChecked(true);
                water.dismiss();
                c=1;
                createPost(a,b,c,d,e);
            }
        });
    }

    public void showDialog03(){
        air.show();

        Button noBtn = air.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchCompat3.setChecked(false);
                air.dismiss();
                d=0;
                createPost(a,b,c,d,e);
            }
        });
        air.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchCompat3.setChecked(true);
                air.dismiss();
                d=1;
                createPost(a,b,c,d,e);
            }
        });
    }

    public void showDialog04(){
        heating.show();

        Button noBtn = heating.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchCompat4.setChecked(false);
                heating.dismiss();
                e=0;
                createPost(a,b,c,d,e);
            }
        });
        heating.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchCompat4.setChecked(true);
                heating.dismiss();
                e=1;
                createPost(a,b,c,d,e);
            }
        });
    }
    public static void createPost(int a,int b,int c,int d, int e){ //a=autoMode,b~e =relay1~4
        Post post=new Post(CO2,temp,humi,waterLevel,a,b,c,d,e);

        Call<Post> call=jsonPlaceHolderApi.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    return;
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            }
        });
    }
}