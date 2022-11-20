package com.example.duan1_spotify_clone.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.PlayListAdapter2;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;

import java.util.ArrayList;

public class FragmentList extends Fragment {
    ListView listView;
    ArrayList<Playlist> list = new ArrayList<>();
    DBPlayList db;
    PlayListAdapter2 adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        listView = v.findViewById(R.id.lvListBH);
        db = new DBPlayList(getContext());
        capNhat();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity2)getContext()).setCurrentPage(6);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }
    void capNhat(){
        list = db.getAll();
        adapter = new PlayListAdapter2(getActivity(),list);
        listView.setAdapter(adapter);
    }
}