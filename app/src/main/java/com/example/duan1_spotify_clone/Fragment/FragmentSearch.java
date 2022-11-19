package com.example.duan1_spotify_clone.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.DanhMucAdapter;
import com.example.duan1_spotify_clone.AdapterHome.Adapter.ViewPagerAdapter;
import com.example.duan1_spotify_clone.DTO.DanhMuc;
import com.example.duan1_spotify_clone.DanhSachNhac.FragmentDanhSachNhac;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    List<DanhMuc> list = new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        SearchView searchView = v.findViewById(R.id.search);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FragmentSearch2.class));
            }
        });
        // Inflate the layout for this fragment
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                startActivity(new Intent(getActivity(), FragmentSearch2.class));
            }
        });
        DanhMuc danhMuc = new DanhMuc(1,"Podcast",R.drawable.popmix);
        list.add(danhMuc);
        list.add(danhMuc);
        list.add(danhMuc);
        list.add(danhMuc);
        list.add(danhMuc);
        list.add(danhMuc);
        list.add(danhMuc);
        list.add(danhMuc);
        list.add(danhMuc);
        list.add(danhMuc);
        recyclerView = v.findViewById(R.id.recycle);
        GridLayoutManager dGridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(dGridLayoutManager);
        DanhMucAdapter adapter = new DanhMucAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
        //
//        View view = getLayoutInflater().inflate(R.layout.activity_main2,null);
//        ViewPager2 viewPager2 = view.findViewById(R.id.frame);
//        ViewPagerAdapter adapter1 = new ViewPagerAdapter(getActivity());
//        viewPager2.setAdapter(adapter1);
//        viewPager2.setCurrentItem(0);
//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                replaceFrg(new FragmentDanhSachNhac());
//                Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
//            }
//        });

        return v;
    }
//    private void replaceFrg(Fragment frg){
//        FragmentManager fm = getParentFragmentManager();
//        fm.beginTransaction().replace(R.id.frame,frg).commit();
//    }
}