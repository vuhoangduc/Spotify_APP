package com.example.duan1_spotify_clone.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duan1_spotify_clone.Add_List;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class FragmentLibrary extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    MainAdapter mainAdapter;
    ImageView img_addlist,search_library1,img_library1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_library, container, false);
        tabLayout =v.findViewById(R.id.tab_layoutLibrary);
        viewPager = v.findViewById(R.id.view_pagerLibrary);

        mainAdapter = new MainAdapter(getActivity().getSupportFragmentManager());

        mainAdapter.AddFragment(new FragmentLibraryAll(),"All");
        mainAdapter.AddFragment(new FragmentLibraryAlbum(),"Danh sách phát");
        mainAdapter.AddFragment(new FragmentLibrarySinger(),"Nghệ sĩ");
        viewPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(viewPager);
        img_addlist = v.findViewById(R.id.img_addlist);
        img_addlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   startActivity(new Intent(getActivity(), Add_List.class));
            }
        });
        search_library1=v.findViewById(R.id.search_library);
        search_library1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),FragmentLibrarySearch.class));
            }
        });
        img_library1=v.findViewById(R.id.img_library);
        img_library1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity2)getContext()).setCurrentPage(9);
            }
        });
        return v;
    }
    private class MainAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        public void AddFragment(Fragment fragment,String s){
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {

            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringArrayList.get(position);
        }
    }

}