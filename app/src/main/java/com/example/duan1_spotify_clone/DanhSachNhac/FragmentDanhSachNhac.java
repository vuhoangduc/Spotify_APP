package com.example.duan1_spotify_clone.DanhSachNhac;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duan1_spotify_clone.DBHelper.Dont_Open;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_DanhMuc;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
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
        ImageView img = view.findViewById(R.id.back_button);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity2) getContext()).setCurrentPage(1);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        try {
            init();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return view;
    }
    public void init() throws UnknownHostException {
        Dont_Open dont_open = new Dont_Open(getActivity());
        JsonParser_DanhMuc jsonParser = new JsonParser_DanhMuc(getActivity(),recyclerView,dont_open.getData());
//        Log.d("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+getMachineIP(), "init: ");
        jsonParser.execute("http://192.168.0.103:3000/danhmucs");
    }
}