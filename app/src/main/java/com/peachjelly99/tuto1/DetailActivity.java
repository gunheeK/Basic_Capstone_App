package com.peachjelly99.tuto1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        findViewById(R.id.fragment1).setEnabled(false);
        findViewById(R.id.fragment2).setEnabled(false);
        findViewById(R.id.fragment3).setEnabled(false);
        findViewById(R.id.fragment4).setEnabled(false);

        Button main = (Button)findViewById(R.id.BackButton);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}