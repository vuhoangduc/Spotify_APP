package com.example.duan1_spotify_clone.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
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
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class FragmentList extends Fragment {
    public ListView listView;
    ArrayList<Playlist> list = new ArrayList<>();
    DBPlayList db;
    PlayListAdapter2 adapter;
    public ImageView imageView,back_list,playmusic;
    public TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        listView = v.findViewById(R.id.lvListBH);
        textView = v.findViewById(R.id.tieu_de_DanhSachNhac);
        imageView = v.findViewById(R.id.img_DanhSachNhac);
        back_list = v.findViewById(R.id.back_list);
        playmusic = v.findViewById(R.id.playMusic);
        back_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity2)getContext()).setCurrentPage(4);
            }
        });
        db = new DBPlayList(getContext());
        GetData getData = new GetData();
        getData.execute("http://192.168.0.104:3000/danhsachnhacs");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity2) getContext()).setCurrentPage(6);
            }
        });
        JsonParser_Music jsonParser_music = new JsonParser_Music(getActivity(),listView);
        jsonParser_music.execute("http://192.168.0.104:3000/musics");
        // Inflate the layout for this fragment
        playmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonParser_Music_v1 jsonParser_music_v1 = new JsonParser_Music_v1(getActivity());
                jsonParser_music_v1.execute("http://192.168.0.104:3000/musics");
            }
        });
        return v;
    }
    public class JsonParser_Music_v1 extends AsyncTask<String, Integer, List<Music1>> {

        Context context;
        MediaPlayer m;

        public JsonParser_Music_v1(Context context) {
            this.context = context;

        }
        @Override
        protected List<Music1> doInBackground(String... strings) {
            Log.d("zzzzzzzz play music", "onClick: ");
            String line = "";
            String datav1 = "";
            List<Music1> data = new ArrayList<>();
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
                    Dont_Open dont_open = new Dont_Open(context);
                    JSONArray jsonarray = new JSONArray(datav1);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject value = jsonarray.getJSONObject(i);
                        if (value.getString("id_DanhSach").equals(dont_open.getData())) {
                            Music1 music1 = new Music1(value.getString("id_music"), value.getString("ten_music"), value.getString("img_music"), value.getString("file_music"), value.getString("id_kenh"), value.getString("id_DanhSach"));
                            data.add(music1);
                        }else{
                            continue;
                        }
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(List<Music1> music1s) {
            super.onPostExecute(music1s);
            for (int i = 0; i < music1s.size(); i++) {
                Log.d("zzzzzzzzzzzzzzz file music"+music1s.get(i).getFile_music(), "onPostExecute: ");
                startAudioStream(music1s.get(i).getFile_music());
            }
        }
        public void startAudioStream(String url) {
            if (m == null)
                m = new MediaPlayer();
            try {
                Log.d("mylog", "Playing: " + url);
                m.setAudioStreamType(AudioManager.STREAM_MUSIC);
                m.setDataSource(url);
                //descriptor.close();
                m.prepare();
                m.setVolume(1f, 1f);
                m.setLooping(false);
                m.start();
            } catch (Exception e) {
                Log.d("mylog", "Error playing in SoundHandler: " + e.toString());
            }
            if(m.isPlaying()){
                m.pause();
                playmusic.setImageResource(R.drawable.pause);
            }else {
                m.start();
                playmusic.setImageResource(R.drawable.play);
            }
        }
    }

    public class GetData extends AsyncTask<String, Integer, List<DanhSachNhac>> {
        @Override
        protected List<DanhSachNhac> doInBackground(String... strings) {
            String line = "";
            String datav1 = "";
            Dont_Open dont_open = new Dont_Open(getActivity());
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