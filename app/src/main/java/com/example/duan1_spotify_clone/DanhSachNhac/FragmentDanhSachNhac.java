package com.example.duan1_spotify_clone.DanhSachNhac;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_spotify_clone.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentDanhSachNhac extends Fragment {
    RecyclerView recyclerView;
    TongAdapter tongAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_danh_sach_nhac, container, false);;
        recyclerView = view.findViewById(R.id.recycleMain);
        tongAdapter = new TongAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        tongAdapter.setData(getData());
        recyclerView.setAdapter(tongAdapter);
        return view;
    }
    private List<Tong> getData(){
        List<Tong> list = new ArrayList<>();
        List<ItemNhac> listItem = new ArrayList<>();
        listItem.add(new ItemNhac(R.drawable.popmix,"Son Tung","Nghe si so 1 VN"));
        listItem.add(new ItemNhac(R.drawable.k_pop,"Son Tung","Nghe si so 1 VN"));
        listItem.add(new ItemNhac(R.drawable.v_pop,"Son Tung","Nghe si so 1 VN"));
        listItem.add(new ItemNhac(R.drawable.hiphop,"Son Tung","Nghe si so 1 VN"));
        listItem.add(new ItemNhac(R.drawable.pop,"Son Tung","Nghe si so 1 VN"));
        listItem.add(new ItemNhac(R.drawable.rockjpg,"Son Tung","Nghe si so 1 VN"));

        list.add(new Tong("Nhac Viet thinh hanh",listItem));
        list.add(new Tong("Nhac Viet thinh hanh",listItem));
        list.add(new Tong("Nhac Viet thinh hanh",listItem));
        list.add(new Tong("Nhac Viet thinh hanh",listItem));
        list.add(new Tong("Nhac Viet thinh hanh",listItem));
        list.add(new Tong("Nhac Viet thinh hanh",listItem));
        return list;
    }
}