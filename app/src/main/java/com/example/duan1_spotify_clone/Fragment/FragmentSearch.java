package com.example.duan1_spotify_clone.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.DanhMucAdapter;
import com.example.duan1_spotify_clone.DTO.DanhMuc;
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
        DanhMuc danhMuc2 = new DanhMuc(1,"Podcast",R.drawable.popmix);
        DanhMuc danhMuc3 = new DanhMuc(1,"Podcast",R.drawable.k_pop);
        DanhMuc danhMuc4 = new DanhMuc(1,"Podcast",R.drawable.v_pop);
        DanhMuc danhMuc5= new DanhMuc(1,"Podcast",R.drawable.pop);
        DanhMuc danhMuc6 = new DanhMuc(1,"Podcast",R.drawable.popmix);
        DanhMuc danhMuc7 = new DanhMuc(1,"Podcast",R.drawable.popmix);
        DanhMuc danhMuc8 = new DanhMuc(1,"Podcast",R.drawable.popmix);
        DanhMuc danhMuc9 = new DanhMuc(1,"Podcast",R.drawable.popmix);
        DanhMuc danhMuc10 = new DanhMuc(1,"Podcast",R.drawable.popmix);
        DanhMuc danhMuc11 = new DanhMuc(1,"Podcast",R.drawable.popmix);
        DanhMuc danhMuc12 = new DanhMuc(1,"Podcast",R.drawable.popmix);
        list.add(danhMuc);
        list.add(danhMuc3);
        list.add(danhMuc4);
        list.add(danhMuc2);
        list.add(danhMuc5);
        list.add(danhMuc6);
        list.add(danhMuc7);
        list.add(danhMuc8);
        list.add(danhMuc9);
        list.add(danhMuc10);
        list.add(danhMuc11);
        list.add(danhMuc12);
        recyclerView = v.findViewById(R.id.recycle);
        GridLayoutManager dGridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(dGridLayoutManager);
        DanhMucAdapter adapter = new DanhMucAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);


        return v;
    }
}