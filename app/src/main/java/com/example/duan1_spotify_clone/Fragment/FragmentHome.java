package com.example.duan1_spotify_clone.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import  android.text.format.Time;
import android.widget.TextView;

import com.example.duan1_spotify_clone.AdapterHome.DanhMucAdapterHome;
import com.example.duan1_spotify_clone.AdapterHome.NgheSiAdapterHome;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DTO.HomeItem;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Kenh_Home;
import com.example.duan1_spotify_clone.R;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    private RecyclerView recyclerViewDM,recyclerViewNS,recycleViewPL,recyclerViewKenh;
    private DanhMucAdapterHome DMAdapter;
    private NgheSiAdapterHome NSAdapter;
    TextView tv_setDate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //
        recyclerViewKenh = v.findViewById(R.id.recycleViewKenh);
        GridLayoutManager dGridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerViewKenh.setLayoutManager(dGridLayoutManager);
        try {
            init();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //
        recyclerViewDM = v.findViewById(R.id.recycleMainDM);
        DMAdapter = new DanhMucAdapterHome(getListHome());
        LinearLayoutManager managerDM = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerViewDM.setLayoutManager(managerDM);
        recyclerViewDM.setAdapter(DMAdapter);
        //
        recyclerViewNS = v.findViewById(R.id.recycleMainNS);
        NSAdapter = new NgheSiAdapterHome(getListHome());
        LinearLayoutManager managerNS = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerViewNS.setLayoutManager(managerNS);
        recyclerViewNS.setAdapter(NSAdapter);
        //
        recycleViewPL = v.findViewById(R.id.recycleMainPL);
        LinearLayoutManager managerPL = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recycleViewPL.setLayoutManager(managerPL);
        recycleViewPL.setAdapter(DMAdapter);

        tv_setDate = v.findViewById(R.id.tv_setDate);
        setDate();
        return v;
    }
    private List<HomeItem> getListHome(){
        List<HomeItem> list1 = new ArrayList<>();

        list1.add(new HomeItem("Bad Bunny",R.drawable.v_pop));
        list1.add(new HomeItem("Sơn Tùng M-TP",R.drawable.hiphop));
        list1.add(new HomeItem("J97",R.drawable.k_pop));
        list1.add(new HomeItem("Amee",R.drawable.pop));
        return list1;

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
        JsonParser_Kenh_Home jsonParser = new JsonParser_Kenh_Home(getActivity(),recyclerViewKenh);
        jsonParser.execute("http://172.28.192.1:3000/kenhs");
    }
}