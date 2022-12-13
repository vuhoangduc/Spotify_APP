package com.example.duan1_spotify_clone.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.PlayListAdapter2;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.R;

import java.util.ArrayList;

public class FragmentLibrarySearch extends AppCompatActivity {
    ImageView img;
    ListView listView;
    PlayListAdapter2 adapter;
    ArrayList<Playlist> list = new ArrayList<>();
    ArrayList<Playlist> list2;
    DBPlayList db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragment_search);
        img = findViewById(R.id.back_search);
        SearchView searchView = findViewById(R.id.searchView);
         listView = findViewById(R.id.listViewSearch3);
        db = new DBPlayList(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                list = db.getAll();
                list2 = new ArrayList<>();
                for (int i=0;i<list.size();i++){
                    if(list.get(i).getTenList().length()>=newText.length()){
                        String a = list.get(i).getTenList().substring(0,newText.length());
                        if(a.equalsIgnoreCase(newText)){
                            list2.add(list.get(i));
                        }
                    }
                }
                capNhat();
                return false;
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    void capNhat(){
        adapter = new PlayListAdapter2(this,list2);
        listView.setAdapter(adapter);
    }
}