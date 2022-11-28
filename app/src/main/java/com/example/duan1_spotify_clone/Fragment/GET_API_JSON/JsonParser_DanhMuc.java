package com.example.duan1_spotify_clone.Fragment.GET_API_JSON;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.DanhSachNhac.ItemNhac;
import com.example.duan1_spotify_clone.DanhSachNhac.Tong;
import com.example.duan1_spotify_clone.DanhSachNhac.TongAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonParser_DanhMuc extends AsyncTask<String,Integer, List<Tong>> {
    Context context;
    RecyclerView ds_danhMuc;
    TongAdapter tongAdapter;
    String data_value;

    public JsonParser_DanhMuc(Context context, RecyclerView ds_danhMuc, String data_value) {
        this.context = context;
        this.ds_danhMuc = ds_danhMuc;
        this.data_value = data_value;
    }

    public JsonParser_DanhMuc(Context context) {
        this.context = context;

    }

    @Override
    protected List<Tong> doInBackground(String... strings) {
        String line = "";
        String datav1 = "";
        List<Tong> data = new ArrayList<>();
        List<String> tenDanhMuclist = new ArrayList<>();
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
                Tong tong = new Tong();
                List<ItemNhac> list_nhac = new ArrayList<>();
                JSONArray jsonarray = new JSONArray(datav1);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject value = jsonarray.getJSONObject(i);
                    if (value.getString("id_TL").equals(data_value)) {
                        tenDanhMuclist.add(value.getString("ten_DM"));
                        GetData getData = new GetData();
                        tong = new Tong(tenDanhMuclist, getData.doInBackground("http://172.28.192.1:3000/danhsachnhacs"));
                        data.add(tong);
                    } else {
                        continue;
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
    protected void onPostExecute(List<Tong> tongs) {
        super.onPostExecute(tongs);
        TongAdapter tongAdapter = new TongAdapter(context);
        tongAdapter.setData(tongs);
        ds_danhMuc.setAdapter(tongAdapter);

    }

    public class GetData extends AsyncTask<String, Integer, List<ItemNhac>> {

        @Override
        protected List<ItemNhac> doInBackground(String... strings) {
            String line = "";
            String datav1 = "";
            List<ItemNhac> list = new ArrayList<>();
            try {
                URL url = new URL(strings[0]);

                InputStream inputStream = url.openStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    datav1 += line;
                }
                JSONArray jsonarray = new JSONArray(datav1);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject value = jsonarray.getJSONObject(i);
                    ItemNhac itemNhac = new ItemNhac(value.getString("id_DanhSach"),value.getString("img_DanhSach"),value.getString("ten_DanhSach"),value.getString("gioi_thieu_DanhSach"));
                    list.add(itemNhac);
                }
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return list;
        }
    }
}

