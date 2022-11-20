package com.example.duan1_spotify_clone.Fragment.GET_API_JSON;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.TheLoaiAdapter;
import com.example.duan1_spotify_clone.DTO.TheLoai;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonParser_DanhMuc extends AsyncTask<String,Integer, List<Tong>> {
    Context context;
    RecyclerView ds_danhMuc;
    TongAdapter tongAdapter;

    public JsonParser_DanhMuc(Context context, RecyclerView ds_danhMuc) {
        this.context = context;
        this.ds_danhMuc = ds_danhMuc;
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
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                 datav1 += line;
            }
            if (!datav1.isEmpty()){
                Tong tong = new Tong();
                List<ItemNhac> list_nhac = new ArrayList<>();
                JSONArray jsonarray = new JSONArray(datav1);
                    JSONObject value = jsonarray.getJSONObject(0);
                    JSONArray jsonarray_v1 = new JSONArray(value.getJSONArray("DanhMuc").toString());
                    for (int j = 0; j < jsonarray_v1.length(); j++) {
                        JSONObject value_DanhMuc = jsonarray_v1.getJSONObject(j);
                        JSONArray jsonarray_v2 = new JSONArray(value_DanhMuc.getJSONArray("DanhSach_Nhac").toString());
                        for (int i = 0; i < jsonarray_v2.length(); i++) {
                            JSONObject value_DanhSachNhac = jsonarray_v2.getJSONObject(i);
                            Log.d("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+jsonarray_v2
                                    .length(), "doInBackground: ");
                            ItemNhac itemNhac =new ItemNhac(value_DanhSachNhac.getString("img_DanhSach"),value_DanhSachNhac.getString("ten_DanhSach"),value_DanhSachNhac.getString("gioi_thieu_DanhSach"));
                            list_nhac.add(itemNhac);
                            tong.setCount_img(jsonarray_v2.length());
                        }
                            tenDanhMuclist.add(value_DanhMuc.getString("ten_DM"));
                            tong.setNameTong(tenDanhMuclist);
                            tong.setListTong(list_nhac);
                        data.add(tong);
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

}

