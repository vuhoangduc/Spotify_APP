package com.example.duan1_spotify_clone.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import  android.text.format.Time;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_spotify_clone.AdapterHome.DanhMucAdapterHome;
import com.example.duan1_spotify_clone.AdapterHome.NgheSiAdapterHome;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DTO.HomeItem;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Chanel_Home;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Kenh_Home;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Music_Home;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_WordCup;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.example.duan1_spotify_clone.intefaces.KenhSend;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    private RecyclerView recyclerViewDM,recyclerViewNS,recycleViewPL,recyclerViewKenh;
    private DanhMucAdapterHome DMAdapter;
    KenhSend kenhSend;

    public void setKenhSend(KenhSend kenhSend) {
        this.kenhSend = kenhSend;
    }

    TextView tv_setDate;
    ImageView setting;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        openDialog(Gravity.CENTER);
        //
        recyclerViewKenh = v.findViewById(R.id.recycleViewKenh);
        GridLayoutManager dGridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerViewKenh.setLayoutManager(dGridLayoutManager);
        setting = v.findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity2)getContext()).setCurrentPage(8);
            }
        });
        try {
            init();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //
        recyclerViewDM = v.findViewById(R.id.recycleMainDM);
        LinearLayoutManager managerDM = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerViewDM.setLayoutManager(managerDM);
        try {
            init2();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //
        recyclerViewNS = v.findViewById(R.id.recycleMainNS);
        LinearLayoutManager managerNS = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerViewNS.setLayoutManager(managerNS);
        try {
            init3();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //
        recycleViewPL = v.findViewById(R.id.recycleMainPL);
        LinearLayoutManager managerPL = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recycleViewPL.setLayoutManager(managerPL);
        try {
            init4();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        tv_setDate = v.findViewById(R.id.tv_setDate);
        setDate();
        return v;
    }
    public static FragmentHome getInstance(){
        return new FragmentHome();
    }
    public void setDate(){
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int getHour = today.hour;
        if(getHour>0&&getHour<=12){
            tv_setDate.setText("Chào buổi sáng");
        }else if(getHour<18){
            tv_setDate.setText("Chào buổi chiều");
        }else{
            tv_setDate.setText("Chào buổi tối");
        }
    }
    public void init() throws UnknownHostException {
        JsonParser_Kenh_Home jsonParser = new JsonParser_Kenh_Home(getActivity(),recyclerViewKenh,kenhSend);
        jsonParser.execute("http://192.168.0.103:3000/kenhs");
    }
    public void init2() throws UnknownHostException {
        JsonParser_Music_Home jsonParser = new JsonParser_Music_Home(getActivity(),recyclerViewDM);
        jsonParser.execute("http://192.168.0.103:3000/goiY");
    }
    public void init3() throws UnknownHostException {
        JsonParser_Chanel_Home jsonParser = new JsonParser_Chanel_Home(getActivity(),recyclerViewNS);
        jsonParser.execute("http://192.168.0.103:3000/kenhs");
    }
    public void init4() throws UnknownHostException {
        JsonParser_WordCup jsonParser = new JsonParser_WordCup(getActivity(),recycleViewPL);
        jsonParser.execute("http://192.168.0.103:3000/wordcups");
    }
    private void openDialog(int gravity){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.banner);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();
        ImageView img = dialog.findViewById(R.id.bannerback);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
