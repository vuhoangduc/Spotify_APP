package com.example.duan1_spotify_clone.DanhSachNhac;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_DanhMuc;
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
        JsonParser_DanhMuc jsonParser = new JsonParser_DanhMuc(getActivity(),recyclerView);
//        Log.d("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+getMachineIP(), "init: ");
        jsonParser.execute("http://192.168.0.104:3000/new");

    }
}