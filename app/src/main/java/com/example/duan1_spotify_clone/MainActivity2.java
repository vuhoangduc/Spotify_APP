package com.example.duan1_spotify_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.ViewPagerAdapter;
import com.example.duan1_spotify_clone.DTO.Kenh;
import com.example.duan1_spotify_clone.DTO.Music1;
import com.example.duan1_spotify_clone.DanhSachNhac.FragmentDanhSachNhac;
import com.example.duan1_spotify_clone.Fragment.FragmentKenh;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Music;
import com.example.duan1_spotify_clone.Fragment.NowPlayingFragmentBottom;
import com.example.duan1_spotify_clone.intefaces.KenhSend;
import com.example.duan1_spotify_clone.intefaces.SongItemAction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.Serializable;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements SongItemAction,KenhSend {
    ViewPager2 viewPager2;
    ViewPagerAdapter adapter;
    FrameLayout frag_bottom_player;

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
        viewPager2.setUserInputEnabled(false);
        frag_bottom_player = findViewById(R.id.frag_bottom_player);
        showFragment(true);
        NowPlayingFragmentBottom nowPlayingFragmentBottom= NowPlayingFragmentBottom.getInstance();
        JsonParser_Music jsonParser_music = new JsonParser_Music(this);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.iconHome:
                        viewPager2.setCurrentItem(0,false);
                        break;
                    case R.id.iconSearch:
                        viewPager2.setCurrentItem(1,false);
                        break;
                    case R.id.iconLibrary:
                        viewPager2.setCurrentItem(2,false);
                        break;
                    case R.id.iconPremium:
                        viewPager2.setCurrentItem(3,false);
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

    public void setCurrentPage(int position){
        viewPager2.setCurrentItem(position,false);
    }


    public void showFragment(boolean check){
        if (check){
            frag_bottom_player.setVisibility(View.GONE);
        }else{
            frag_bottom_player.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMoreAction(int position,List<Music1> songs) {
        NowPlayingFragmentBottom fragBot = (NowPlayingFragmentBottom) getSupportFragmentManager().findFragmentById(R.id.framgent_bottom);
        fragBot.showMoreAction(0,songs);
    }

    @Override
    public void setOnItemClickListener(int position,List<Music1> songs) {
        NowPlayingFragmentBottom fragBot = (NowPlayingFragmentBottom) getSupportFragmentManager().findFragmentById(R.id.framgent_bottom);
        fragBot.showMoreAction_1(position,songs);
    }

    @Override
    public void setOnItemClickListener(Kenh kenh) {
        Log.d("hahaha",kenh.getTen_kenh());
        Bundle bundle = new Bundle();
        Fragment fragBot = new FragmentKenh();
//        ((FragmentKenh) fragBot).getDataKenh(kenh);
        bundle.putSerializable("kenh",kenh);
        fragBot.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragBot).commit();

        setCurrentPage(5);

    }
}