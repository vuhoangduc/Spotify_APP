package com.example.duan1_spotify_clone.Fragment.GET_API_JSON;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_spotify_clone.AdapterHome.KenhAdapterHome;
import com.example.duan1_spotify_clone.AdapterHome.MusicAdapterHome;
import com.example.duan1_spotify_clone.DBHelper.Dont_Open;
import com.example.duan1_spotify_clone.DTO.GoiY;
import com.example.duan1_spotify_clone.DTO.Kenh;
import com.example.duan1_spotify_clone.DTO.Music1;

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

public class JsonParser_Music_Home extends AsyncTask<String,Integer, List<GoiY>> {
    Context context;
    RecyclerView ds_kenh;

    public JsonParser_Music_Home(Context context, RecyclerView ds_kenh) {
        this.context = context;
        this.ds_kenh = ds_kenh;
    }
    public JsonParser_Music_Home(Context context) {
        this.context = context;

    }
    @Override
    protected List<GoiY> doInBackground(String... strings) {
        String line = "";
        String datav1 = "";
        List<GoiY> data = new ArrayList<>();
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
                    GoiY goiY = new GoiY(value.getString("id_music"), value.getString("ten_music"), value.getString("img_music"), value.getString("file_music"), value.getString("id_kenh"));
                        data.add(goiY);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(List<GoiY> goiYS) {
        super.onPostExecute(goiYS);
        MusicAdapterHome adapter = new MusicAdapterHome(context,goiYS);
        ds_kenh.setAdapter(adapter);
    }
}
