package com.example.duan1_spotify_clone.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.MusicAdapter;
import com.example.duan1_spotify_clone.AdapterHome.Adapter.PlayListAdapter2;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DTO.Music1;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Music;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Music_Home;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Music_Search;
import com.example.duan1_spotify_clone.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch2 extends AppCompatActivity {
    ImageView img;
    ListView listView;
    MusicAdapter adapter;
    List<Music1> list;
    List<Music1> list2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_search2);
        listView = findViewById(R.id.listViewSearch);
        img = findViewById(R.id.back_search);
        SearchView searchView = findViewById(R.id.searchView);
        JsonParser_Music_Search jsonParser_music = new JsonParser_Music_Search(FragmentSearch2.this,listView);
        jsonParser_music.execute("http://192.168.0.102:3000/musics");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                list = jsonParser_music.getData();
                    list2 = new ArrayList<>();
                for (int i=0;i<list.size();i++){
                    if(list.get(i).getTen_music().length()>=newText.length()){
                        String a = list.get(i).getTen_music().substring(0,newText.length());
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
        adapter = new MusicAdapter(this,list2);
        listView.setAdapter(adapter);
    }
}