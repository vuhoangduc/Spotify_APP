package com.example.duan1_spotify_clone.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan1_spotify_clone.AdapterHome.Adapter.PlayListAdapter2;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DBHelper.Dont_Open;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.DTO.TheLoai;
import com.example.duan1_spotify_clone.DanhSachNhac.ItemNhac;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;

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

public class FragmentList extends Fragment {
    ListView listView;
    ArrayList<Playlist> list = new ArrayList<>();
    DBPlayList db;
    PlayListAdapter2 adapter;
    ImageView imageView;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        listView = v.findViewById(R.id.lvListBH);
        textView = v.findViewById(R.id.tieu_de_DanhSachNhac);
        imageView = v.findViewById(R.id.img_DanhSachNhac);
        db = new DBPlayList(getContext());
        capNhat();
        GetData getData = new GetData();
        getData.execute("http://192.168.137.45:3000/new");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity2) getContext()).setCurrentPage(6);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    void capNhat() {
        list = db.getAll();
        adapter = new PlayListAdapter2(getActivity(), list);
        listView.setAdapter(adapter);
    }


    public class GetData extends AsyncTask<String, Integer, List<Playlist>> {
        @Override
        protected List<Playlist> doInBackground(String... strings) {
            String line = "";
            String datav1 = "";
            Dont_Open dont_open = new Dont_Open(getActivity());
            String data_value = dont_open.getData();
            List<Playlist> data = new ArrayList<>();
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
                    JSONArray jsonarray_v1 = new JSONArray(value.getJSONArray("DanhMuc").toString());
                    for (int j = 0; j < jsonarray_v1.length(); j++) {
                        JSONObject value_DanhMuc = jsonarray_v1.getJSONObject(j);
                        JSONArray jsonarray_v2 = new JSONArray(value_DanhMuc.getJSONArray("DanhSach_Nhac").toString());
                        for (int k = 0; k < jsonarray_v2.length(); k++) {
                            JSONObject value_DanhSachNhac = jsonarray_v2.getJSONObject(k);
                            if (value_DanhSachNhac.getString("ten_DanhSach").equals(data_value)) {
//                                textView.setText(""+value_DanhSachNhac.getString("gioi_thieu_DanhSach"));
//                                Glide.with(getActivity()).load(value_DanhSachNhac.getString("img_DanhSach")).placeholder(R.drawable.hiphop).into(imageView);
                                Log.d("zzzzzzzz"+value_DanhSachNhac.get("img_DanhSach"), "doInBackground:");
                                break;
                            } else {
                                continue;
                            }
                        }
//                            data.add(tong);
                    }
                }
                return null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return data;
        }
    }
}