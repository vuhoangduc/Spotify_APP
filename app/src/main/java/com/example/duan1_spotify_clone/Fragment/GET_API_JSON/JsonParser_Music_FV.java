package com.example.duan1_spotify_clone.Fragment.GET_API_JSON;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.MusicAdapterRecycle;
import com.example.duan1_spotify_clone.DBHelper.Dont_Open;
import com.example.duan1_spotify_clone.DBHelper.Save_music_fv;
import com.example.duan1_spotify_clone.DTO.Music1;
import com.example.duan1_spotify_clone.Fragment.NowPlayingFragmentBottom;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.example.duan1_spotify_clone.intefaces.SongItemAction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonParser_Music_FV extends AsyncTask<String, Integer, List<Music1>> {

    Context context;
    RecyclerView view;
    List<Music1> data;

    ImageView control_music;
    SongItemAction songItemAction;
    public JsonParser_Music_FV(Context context, RecyclerView view) {
        this.context = context;
        this.view = view;
    }

    public JsonParser_Music_FV(Context context, RecyclerView view, ImageView control_music) {
        this.context = context;
        this.view = view;
        this.control_music = control_music;
    }

    public JsonParser_Music_FV(Context context) {
        this.context = context;
    }
    public void setSongItemAction(SongItemAction songItemAction) {
        this.songItemAction = songItemAction;
    }
    MusicAdapterRecycle adapter;
    @Override
    protected List<Music1> doInBackground(String... strings) {
        String line = "";
        String datav1 = "";
        data = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            InputStream inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                datav1 += line;
            }
            if (!datav1.isEmpty()){
                Save_music_fv save_music_fv = new Save_music_fv(context);
                JSONArray jsonarray = new JSONArray(datav1);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject value = jsonarray.getJSONObject(i);
                    if (save_music_fv.getData().size()==0){
                        return null;
                    }else {
                        for (int j = 0; j < save_music_fv.getData().size(); j++) {
                            if (value.getString("id_music").equals(save_music_fv.getData().get(j))){
                                Music1 music1 = new Music1(value.getString("id_music"), value.getString("ten_music"), value.getString("img_music"), value.getString("file_music"), value.getString("id_kenh"), value.getString("id_DanhSach"));
                                data.add(music1);
                            }else {
                                continue;
                            }
                        }
                    }

                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
    boolean check = true;
    int count = 0;
    @Override
    protected void onPostExecute(List<Music1> music1s) {
        super.onPostExecute(music1s);
        adapter = new MusicAdapterRecycle(context,music1s);
        view.setAdapter(adapter);
        control_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count==0){
                    control_music.setImageResource(R.drawable.pause);
                    ((MainActivity2)context).showFragment(false);
                    Intent intent = new Intent(context,NowPlayingFragmentBottom.class);
                    songItemAction = (SongItemAction) context;
                    NowPlayingFragmentBottom nowPlayingFragmentBottom = NowPlayingFragmentBottom.getInstance();
                    songItemAction.showMoreAction(0,music1s,control_music);
                    check=false;
                    count++;
                }else{
                    if(check==true&&count==1){
                        control_music.setImageResource(R.drawable.pause);
                        ((MainActivity2)context).controller(true);
                        check=false;

                    }else{
                        control_music.setImageResource(R.drawable.play1);
                        ((MainActivity2)context).controller(false);
                        check=true;
                    }
                }


            }
        });
    }

}
