package com.example.duan1_spotify_clone.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.DanhMucAdapter;
import com.example.duan1_spotify_clone.AdapterHome.Adapter.TheLoaiAdapter;
import com.example.duan1_spotify_clone.DTO.DanhMuc;
import com.example.duan1_spotify_clone.DTO.TheLoai;
import com.example.duan1_spotify_clone.DanhSachNhac.FragmentDanhSachNhac;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment{
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
        recyclerView = v.findViewById(R.id.recycle);
        GridLayoutManager dGridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(dGridLayoutManager);
        try {
            init();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return v;
    }
    public void init() throws UnknownHostException {
        JsonParser jsonParser = new JsonParser(getActivity(),recyclerView);
        jsonParser.execute("http://10.24.20.200:3000/new");

    }

}