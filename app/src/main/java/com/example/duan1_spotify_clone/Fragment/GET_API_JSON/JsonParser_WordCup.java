package com.example.duan1_spotify_clone.Fragment.GET_API_JSON;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_spotify_clone.AdapterHome.DanhMucAdapterHome;
import com.example.duan1_spotify_clone.AdapterHome.MusicAdapterHome;
import com.example.duan1_spotify_clone.DTO.GoiY;
import com.example.duan1_spotify_clone.DTO.WordCup;

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

public class JsonParser_WordCup extends AsyncTask<String,Integer, List<WordCup>> {
    Context context;
    RecyclerView ds_kenh;

    public JsonParser_WordCup(Context context, RecyclerView ds_kenh) {
        this.context = context;
        this.ds_kenh = ds_kenh;
    }

    public JsonParser_WordCup(Context context) {
        this.context = context;

    }

    @Override
    protected List<WordCup> doInBackground(String... strings) {
        String line = "";
        String datav1 = "";
        List<WordCup> data = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            InputStream inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                datav1 += line;
            }
            if (!datav1.isEmpty()) {
                JSONArray jsonarray = new JSONArray(datav1);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject value = jsonarray.getJSONObject(i);
                    WordCup wordCup = new WordCup(value.getString("id_wc"),value.getString("ten_wc"),value.getString("img_wc"),value.getString("nam_wc"),value.getString("flie_wc"));
                    data.add(wordCup);
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
    protected void onPostExecute(List<WordCup> wc) {
        super.onPostExecute(wc);
        DanhMucAdapterHome adapter = new DanhMucAdapterHome(context, wc);
        ds_kenh.setAdapter(adapter);
    }
}
