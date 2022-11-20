package com.example.duan1_spotify_clone.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duan1_spotify_clone.AdapterHome.DanhMucAdapterHome;
import com.example.duan1_spotify_clone.AdapterHome.NgheSiAdapterHome;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DTO.HomeItem;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentMusic extends Fragment {
    private RecyclerView recyclerViewDM;
    private DanhMucAdapterHome DMAdapter;
    TextView tv_moveKenh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_music, container, false);
        recyclerViewDM = v.findViewById(R.id.recycleMainBH);
        DMAdapter = new DanhMucAdapterHome(getListHome());
        LinearLayoutManager managerDM = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerViewDM.setLayoutManager(managerDM);
        recyclerViewDM.setAdapter(DMAdapter);
        tv_moveKenh  = v.findViewById(R.id.tv_moveKenh);

        tv_moveKenh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity2)getActivity()).setCurrentPage(5);
            }
        });
        //
        TextView tv_dsBai = v.findViewById(R.id.tv_dsBai);
        DBPlayList db = new DBPlayList(getContext());
        ArrayList<Playlist> list = db.getAll();
        String name = "";
        for(int i=0;i<list.size();i++){
            name+=list.get(i).getTenList()+" • ";
        }
        name+="và hơn thế nữa";
        tv_dsBai.setText(name);
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
}