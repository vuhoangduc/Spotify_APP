package com.example.duan1_spotify_clone.Fragment.GET_API_JSON;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.TheLoaiAdapter;
import com.example.duan1_spotify_clone.AdapterHome.KenhAdapterHome;
import com.example.duan1_spotify_clone.DTO.TheLoai;

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

public class JsonParser extends AsyncTask<String,Integer, List<TheLoai>> {
    Context context;
    RecyclerView ds_theloai;

    public JsonParser(Context context, RecyclerView ds_theloai) {
        this.context = context;
        this.ds_theloai = ds_theloai;
    }
    public JsonParser(Context context) {
        this.context = context;

    }
    @Override
    protected List<TheLoai> doInBackground(String... strings) {
        String line = "";
        String datav1 = "";
        List<TheLoai> data = new ArrayList<>();
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
                    JSONArray jsonarray_v1 = new JSONArray(value.getJSONArray("theloais").toString());
                    for (int j = 0; j < jsonarray_v1.length(); j++) {
                        JSONObject value_DanhMuc = jsonarray_v1.getJSONObject(j);
                        TheLoai theLoai = new TheLoai(value_DanhMuc.getString("id_TL"),value_DanhMuc.getString("ten_TL"),value_DanhMuc.getString("img_TL"));
                        data.add(theLoai);
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

    @Override
    protected void onPostExecute(List<TheLoai> theLoais) {
        super.onPostExecute(theLoais);

        TheLoaiAdapter adapter = new TheLoaiAdapter(context,theLoais);
        ds_theloai.setAdapter(adapter);
    }
}

