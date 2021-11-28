package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.miwok.adapter.CategoryAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Select the view pager
        ViewPager viewPager = (ViewPager) findViewById(R.id.vpv_main);

        // Create adapter
        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager(), this);

        // Set adapter at the viewPager
        viewPager.setAdapter(adapter);

        // Select tabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tlv_main_tab);
        tabLayout.setupWithViewPager(viewPager);
    }
}