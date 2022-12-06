package com.example.duan1_spotify_clone.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.MusicAdapter;
import com.example.duan1_spotify_clone.AdapterHome.Adapter.PlayListAdapter2;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DBHelper.Dont_Open;
import com.example.duan1_spotify_clone.DTO.DanhSachNhac;
import com.example.duan1_spotify_clone.DTO.Music1;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Music;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class FragmentList extends Fragment {
    public ListView listView;
    ArrayList<Playlist> list = new ArrayList<>();
    DBPlayList db;
    PlayListAdapter2 adapter;
    Dont_Open dont_open;
    public ImageView imageView,back_list,playmusic;
    public TextView textView;
    GetData getData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        listView = v.findViewById(R.id.lvListBH);
        textView = v.findViewById(R.id.tieu_de_DanhSachNhac);
        imageView = v.findViewById(R.id.img_DanhSachNhac);
        back_list = v.findViewById(R.id.back_list);
        playmusic = v.findViewById(R.id.playMusic);
         dont_open = new Dont_Open(getActivity());
        back_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity2)getContext()).setCurrentPage(4);
                getData = new GetData();
            }
        });
        db = new DBPlayList(getContext());
        getData = new GetData();
        getData.execute("http://192.168.0.104:3000/danhsachnhacs");

        JsonParser_Music jsonParser_music = new JsonParser_Music(getActivity(),listView,playmusic);
        jsonParser_music.execute("http://192.168.0.104:3000/musics");
        // Inflate the layout for this fragment
        return v;
    }


    public class GetData extends AsyncTask<String, Integer, List<DanhSachNhac>> {
        @Override
        protected List<DanhSachNhac> doInBackground(String... strings) {
            String line = "";
            String datav1 = "";

            String data_value = dont_open.getData();
            List<DanhSachNhac> data = new ArrayList<>();
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
                    Log.d("sdsd",data_value);
                    JSONObject value = jsonarray.getJSONObject(i);
                    if (value.getString("id_DanhSach").equals(data_value)){
                        DanhSachNhac danhSachNhac = new DanhSachNhac(value.getString("id_DanhSach"),value.getString("ten_DanhSach"),value.getString("img_DanhSach"),value.getString("gioi_thieu_DanhSach"),value.getString("id_DM"));
                        data.add(danhSachNhac);
                        dont_open.DELETE_ALL();

                        dont_open.ADD_NEW(value.getString("id_DanhSach"));
                        return data;
                    }
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onPostExecute(List<DanhSachNhac> danhSachNhacs) {
            super.onPostExecute(danhSachNhacs);
            textView.setText(danhSachNhacs.get(0).getTieuDe_DS());
            DownloadImg downloadImg = new DownloadImg();
            downloadImg.execute(danhSachNhacs.get(0).getImg_DS());
        }
    }
    class DownloadImg extends AsyncTask<String, Void, Bitmap> {
        InputStream inputStream;
        Bitmap bitmap;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("zzzzzz", "onPreExecute: bat dau download ");
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Log.d("zzzzzzzzz", "doInBackground: dang download " + strings[0]);
            try {
                URL url = new URL(strings[0]);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

                httpsURLConnection.setReadTimeout(10000);
                httpsURLConnection.connect();

                int status = httpsURLConnection.getResponseCode();
                if(status == HttpsURLConnection.HTTP_OK){
                    inputStream = httpsURLConnection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);

                }
                httpsURLConnection.disconnect();

                inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }
}