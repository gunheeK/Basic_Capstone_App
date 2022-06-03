package com.peachjelly99.tuto1;

import static com.peachjelly99.tuto1.Fragment1.*;
import static com.peachjelly99.tuto1.ManualModeActivity.createPost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;
    public static Button buttonEvent;
    public static boolean check = false;
    public static Button manualButton;
    public static Button plantButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        plantButton = (Button)findViewById(R.id.plant_button);
        plantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlantListActivity.class);
                startActivity(intent);
            }
        });

        manualButton = (Button)findViewById(R.id.manual_button);
        manualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManualModeActivity.class);
                startActivity(intent);
            }
        });

        buttonEvent = (Button)findViewById(R.id.auto_button);
        buttonEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(check == false)
                {
                    manualButton.setEnabled(false);
                    buttonEvent.setBackgroundColor(Color.rgb(105, 135, 118));
                    check = true;
                    a=1;
                    b=0;c=0;d=0;e=0;
                }
                else {
                    manualButton.setEnabled(true);
                    buttonEvent.setBackgroundColor(Color.rgb(157, 214, 182));
                    check = false;
                    a=0;
                }
                createPost(a,b,c,d,e);
            }
        });

        //ViewPager2
        mPager = findViewById(R.id.viewpager);
        //Adapter
        pagerAdapter = new MyAdapter(this, num_page);
        mPager.setAdapter(pagerAdapter);
        //Indicator
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page,0);
        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mPager.setCurrentItem(1000);
        mPager.setOffscreenPageLimit(3);

        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position%num_page);
            }

        });


        final float pageMargin= getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        mPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffset = position * -(2 * pageOffset + pageMargin);
                if (mPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(mPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-myOffset);
                    } else {
                        page.setTranslationX(myOffset);
                    }
                } else {
                    page.setTranslationY(myOffset);
                }
            }
        });

    }
}
