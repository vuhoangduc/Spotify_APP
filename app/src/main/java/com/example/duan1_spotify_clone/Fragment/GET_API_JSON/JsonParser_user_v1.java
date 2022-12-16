package com.example.duan1_spotify_clone.Fragment.GET_API_JSON;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_spotify_clone.DBHelper.SaveUser;
import com.example.duan1_spotify_clone.DBHelper.Save_music_fv;
import com.example.duan1_spotify_clone.DTO.TheLoai;
import com.example.duan1_spotify_clone.LoadingImg;

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

public class JsonParser_user_v1 extends AsyncTask<String,Integer, List<TheLoai>> {
    Context context;
    RecyclerView ds_theloai;
    LoadingImg loadingImg;
    Save_music_fv save_music_fv ;
    public JsonParser_user_v1(Context context, RecyclerView ds_theloai, LoadingImg loadingImg) {
        this.loadingImg=loadingImg;
        this.context = context;
        this.ds_theloai = ds_theloai;
    }
    public JsonParser_user_v1(Context context) {
        this.context = context;

    }
    @Override
    protected List<TheLoai> doInBackground(String... strings) {
        save_music_fv = new Save_music_fv(context);
        save_music_fv.open();
        String line = "";
        String datav1 = "";
        List<TheLoai> data = new ArrayList<>();
        SaveUser saveUser = new SaveUser(context);
        saveUser.open();
        if (save_music_fv.getData().size()>0){
            save_music_fv.DELETE_ALL();
        }
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
                JSONArray jsonarray = new JSONArray(datav1);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject value = jsonarray.getJSONObject(i);
                    if (value.getString("email").equals(saveUser.getData().get(0).getEmail_user())){
                        JSONArray jsonarray_v1 = new JSONArray(value.getJSONArray("music_fv").toString());
                        for (int j = 0; j < jsonarray_v1.length(); j++) {
                            String value_v1 = jsonarray_v1.getString(j);
                            if (save_music_fv.getData().size()<=jsonarray_v1.length()){
                                save_music_fv.ADD_NEW(value_v1);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

}

