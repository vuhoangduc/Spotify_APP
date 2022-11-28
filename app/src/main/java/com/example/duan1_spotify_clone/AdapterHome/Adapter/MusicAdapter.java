package com.example.duan1_spotify_clone.AdapterHome.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_spotify_clone.AdapterHome.KenhAdapterHome;
import com.example.duan1_spotify_clone.DBHelper.Dont_Open;
import com.example.duan1_spotify_clone.DTO.Kenh;
import com.example.duan1_spotify_clone.DTO.Music1;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Kenh_Home;
import com.example.duan1_spotify_clone.R;

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

public class MusicAdapter extends ArrayAdapter<Music1> {
        List<Music1> list;
        Context context;
        public TextView tvNS,tvMS;
        ImageView img;
        public MusicAdapter(@NonNull Context context, List<Music1> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_music, null);
        }
         final Music1 playlist = list.get(position);
        if(playlist!=null){
        tvNS = v.findViewById(R.id.tv_tenbaihat);
        tvMS = v.findViewById(R.id.tv_nameNS);
        img = v.findViewById(R.id.img_MS);

            tvNS.setText(playlist.getTen_music());
            JsonParser_Kenh_Home_v1 jsonParser_kenh_home = new JsonParser_Kenh_Home_v1(context);
            Dont_Open dont_open = new Dont_Open(context);
            if (dont_open.getData()!=null){
                dont_open.DELETE_ALL();
                dont_open.ADD_NEW(playlist.getId_kenh());
            }
            jsonParser_kenh_home.execute("http://192.168.0.104:3000/kenhs");

            Glide.with(context).load(list.get(position).getImg_music()).placeholder(R.drawable.hiphop).into(img);
        }
        return v;
        }

    public class JsonParser_Kenh_Home_v1 extends AsyncTask<String,Integer,String> {
        Context context;
        RecyclerView ds_kenh;

        public JsonParser_Kenh_Home_v1(Context context, RecyclerView ds_kenh) {
            this.context = context;
            this.ds_kenh = ds_kenh;
        }
        public JsonParser_Kenh_Home_v1(Context context) {
            this.context = context;

        }
        @Override
        protected String doInBackground(String... strings) {
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
                    Dont_Open dont_open = new Dont_Open(context);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject value = jsonarray.getJSONObject(i);
                        if (value.getString("id_kenh").equals(dont_open.getData())){
                            return value.getString("ten_kenh");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("zzzzzzzzzzzzzzzzzzzzz "+s, "onPostExecute: ");
            tvMS.setText(s);
        }
    }

}
