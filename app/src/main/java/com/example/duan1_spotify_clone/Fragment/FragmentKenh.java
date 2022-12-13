package com.example.duan1_spotify_clone.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan1_spotify_clone.AdapterHome.NgheSiAdapterHome;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DBHelper.Save_kenh;
import com.example.duan1_spotify_clone.DTO.HomeItem;
import com.example.duan1_spotify_clone.DTO.Kenh;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Chanel_Home;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Music_Kenh;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.example.duan1_spotify_clone.intefaces.KenhSend;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class FragmentKenh extends Fragment implements KenhSend {
    private NgheSiAdapterHome NSAdapter;
    private RecyclerView recyclerViewNS;
    TextView tvGioiThieu,tv_dsBai,nameKenh;
    RecyclerView lv;
    ImageView view,imgGthieu,backKenh;
    Kenh kenh;
    KenhSend kenhSend = this;
    String id_kenh,ten_kenh;


    public KenhSend getKenhSend() {
        return kenhSend;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kenh, container, false);
        recyclerViewNS = v.findViewById(R.id.recycleNS);
        LinearLayoutManager managerNS = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerViewNS.setLayoutManager(managerNS);
        view = v.findViewById(R.id.imgKenh);
        imgGthieu=v.findViewById(R.id.imgGthieu);
        nameKenh =v.findViewById(R.id.nameKenh);
        lv=v.findViewById(R.id.lv_dsbai);
        backKenh = v.findViewById(R.id.backKenh);
        backKenh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity2)getContext()).setCurrentPage(1);
            }
        });
        Save_kenh save_kenh = new Save_kenh(getActivity());
        tvGioiThieu = v.findViewById(R.id.tv_gioiThieu);
        try {
            init3();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if (save_kenh.getData()!=null){
            addData(save_kenh.getData());
        }
        try {
            init();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return v;
    }
    public void init3() throws UnknownHostException {
        JsonParser_Chanel_Home jsonParser = new JsonParser_Chanel_Home(getActivity(),recyclerViewNS);
        jsonParser.execute("http://192.168.0.101:3000/kenhs");
    }
    public void init() throws UnknownHostException {
        JsonParser_Music_Kenh jsonParser = new JsonParser_Music_Kenh(getActivity(),lv,id_kenh,ten_kenh);
        jsonParser.execute("http://192.168.0.101:3000/musics");
    }

    public static FragmentKenh getInstance(){
        Log.d("zzzzzzzzzzzzzzzzzzzzz fragment kenh hoat dong", "getInstance: '");
        return new FragmentKenh();
    }
    @Override
    public void setOnItemClickListener(Kenh kenh) {
        tvGioiThieu.setText(kenh.getThongtin_gioiThieu());
        Glide.with(getContext()).load(kenh.getImg_kenh()).placeholder(R.drawable.hiphop).into(view);
        Glide.with(getContext()).load(kenh.getImg_gioiThieu()).placeholder(R.drawable.hiphop).into(imgGthieu);
        nameKenh.setText(kenh.getTen_kenh());
    }

    public void addData(Kenh kenh) {
        tvGioiThieu.setText(kenh.getThongtin_gioiThieu());
        Glide.with(getContext()).load(kenh.getImg_kenh()).placeholder(R.drawable.hiphop).into(view);
        Glide.with(getContext()).load(kenh.getImg_gioiThieu()).placeholder(R.drawable.hiphop).into(imgGthieu);
        nameKenh.setText(kenh.getTen_kenh());
        id_kenh=kenh.getId_kenh();
        ten_kenh=kenh.getTen_kenh();
    }



}