package com.example.duan1_spotify_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {
    ViewPager2 viewPager2;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav_bar);
        navigationView.setItemTextColor(navigationViewColorStateList);
        navigationView.setItemIconTintList(navigationViewColorStateList);
        viewPager2 = findViewById(R.id.frame);
        adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.iconHome:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.iconSearch:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.iconLibrary:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.iconPremium:
                        viewPager2.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }
    int[][] states = new int[][]{
            new int[]{-android.R.attr.state_checked},  // unchecked
            new int[]{android.R.attr.state_checked},   // checked
            new int[]{}                                // default
    };

    // Fill in color corresponding to state defined in state
    int[] colors = new int[]{
            Color.parseColor("#B3B3B3"),
            Color.parseColor("#FFFFFF"),
            Color.parseColor("#B3B3B3"),
    };
    ColorStateList navigationViewColorStateList = new ColorStateList(states, colors);
//    public void loadFragment(Fragment fragment) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
}