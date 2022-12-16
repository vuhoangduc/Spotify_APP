package com.example.duan1_spotify_clone.AdapterHome.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan1_spotify_clone.DanhSachNhac.FragmentDanhSachNhac;
import com.example.duan1_spotify_clone.Fragment.FragmentHome;
import com.example.duan1_spotify_clone.Fragment.FragmentKenh;
import com.example.duan1_spotify_clone.Fragment.FragmentLibrary;
import com.example.duan1_spotify_clone.Fragment.FragmentList;
import com.example.duan1_spotify_clone.Fragment.FragmentMusic;
import com.example.duan1_spotify_clone.Fragment.FragmentPramium;
import com.example.duan1_spotify_clone.Fragment.FragmentSearch;
import com.example.duan1_spotify_clone.Fragment.FragmentSetting;
import com.example.duan1_spotify_clone.Fragment.Frament_Music_FV;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentSearch();
            case 2:
                return new FragmentLibrary();
            case 3:
                return new FragmentPramium();
            case 4:
                return new FragmentDanhSachNhac();
            case 5:
                return new FragmentKenh();
            case 6:
                return new FragmentMusic();
            case 7:
                return new FragmentList();
            case 8:
                return new FragmentSetting();
            case 9:
                return new Frament_Music_FV();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
