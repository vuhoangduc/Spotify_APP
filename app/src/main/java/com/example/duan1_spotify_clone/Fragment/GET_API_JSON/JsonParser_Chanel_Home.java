package com.example.duan1_spotify_clone.Fragment.GET_API_JSON;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_spotify_clone.AdapterHome.KenhAdapterHome;
import com.example.duan1_spotify_clone.AdapterHome.MusicAdapterHome;
import com.example.duan1_spotify_clone.AdapterHome.NgheSiAdapterHome;
import com.example.duan1_spotify_clone.DTO.GoiY;
import com.example.duan1_spotify_clone.DTO.Kenh;

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

public class JsonParser_Chanel_Home extends AsyncTask<String,Integer, List<Kenh>> {
    Context context;
    RecyclerView ds_kenh;

    public JsonParser_Chanel_Home(Context context, RecyclerView ds_kenh) {
        this.context = context;
        this.ds_kenh = ds_kenh;
    }
    public JsonParser_Chanel_Home(Context context) {
        this.context = context;

    }
    @Override
    protected List<Kenh> doInBackground(String... strings) {
        String line = "";
        String datav1 = "";
        List<Kenh> data = new ArrayList<>();
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
                    Kenh kenh  = new Kenh(value.getString("id_kenh"),value.getString("ten_kenh"), value.getString("img_kenh"), value.getString("img_gioiThieu"),value.getString("thongtin_gioiThieu") );
                    data.add(kenh);
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
    protected void onPostExecute(List<Kenh> kenhs) {
        super.onPostExecute(kenhs);
        NgheSiAdapterHome adapter = new NgheSiAdapterHome(kenhs,context);
        ds_kenh.setAdapter(adapter);
    }
}
