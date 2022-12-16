package com.example.duan1_spotify_clone.AdapterHome.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_spotify_clone.Add_List;
import com.example.duan1_spotify_clone.DBHelper.SaveUser;
import com.example.duan1_spotify_clone.DBHelper.Save_music_fv;
import com.example.duan1_spotify_clone.DTO.DanhMuc;
import com.example.duan1_spotify_clone.DTO.Music1;
import com.example.duan1_spotify_clone.DTO.TheLoai;
import com.example.duan1_spotify_clone.DTO.User;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_user_v1;
import com.example.duan1_spotify_clone.Fragment.POST_API_JSON.Post_music_fv;
import com.example.duan1_spotify_clone.Fragment.POST_API_JSON.RetrofitInterFace;
import com.example.duan1_spotify_clone.LoadingImg;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.example.duan1_spotify_clone.intefaces.SongItemAction;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MusicAdapterRecycle extends RecyclerView.Adapter<MusicAdapterRecycle.MusicAdapterRecycleViewHolder> {
    private Context context;
    private List<Music1> list;
    Retrofit retrofit;
    String ten;
    String BASE_URL = "http://192.168.0.102:3000/";
    RetrofitInterFace retrofitInterFace;
    Save_music_fv save_music_fv;
    private SongItemAction songItemAction;
    public SaveUser saveUser;
    public MusicAdapterRecycle(@NonNull Context context, List<Music1> list) {
        this.context=context;
        this.list=list;
        JsonParser_user_v1 jsonParser_user_v1 = new JsonParser_user_v1(context);
        jsonParser_user_v1.execute("http://192.168.0.102:3000/users");
    }
    public MusicAdapterRecycle(@NonNull Context context, List<Music1> list,String ten) {
        this.context=context;
        this.list=list;
        this.ten=ten;
    }

    @NonNull
    @Override
    public MusicAdapterRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        save_music_fv = new Save_music_fv(context);
        return new MusicAdapterRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapterRecycleViewHolder holder, int position) {
        Music1 music1 = list.get(position);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterFace = retrofit.create(RetrofitInterFace.class);
        saveUser = new SaveUser(context);
        int index = position;
        if (music1 == null) {
            return;
        }
        if (save_music_fv.getData().size()>0){
            for (int i = 0; i <save_music_fv.getData().size(); i++) {
                if (music1.getId_music().equals(save_music_fv.getData().get(i))){
                    holder.btn_love.setImageResource(R.drawable.ic_love_music_v2);
                }else{
                    continue;
                }
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songItemAction = (SongItemAction) context;
                songItemAction.setOnItemClickListener(index,list);
                ((MainActivity2)context).showFragment(false);
            }
        });
        holder.tvNS.setText(music1.getTen_music());
        Glide.with(context).load(list.get(position).getImg_music()).placeholder(R.drawable.hiphop).into(holder.img);
        holder.tvMS.setText(ten);
        holder.btn_love.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SaveUser saveUser = new SaveUser(context);
                saveUser.open();
                holder.btn_love.setImageResource(R.drawable.ic_love_music_v2);
                Post_music_fv post_music_fv = new Post_music_fv(saveUser.getData().get(0).getEmail_user(),music1.getId_music());
                HashMap<String ,String> map = new HashMap<>();
                map.put("email",post_music_fv.getEmail());
                map.put("music_fv",post_music_fv.getId_music());
                Call<Post_music_fv> post_music_fvCall = retrofitInterFace.POST_MUSIC_FV_CALL(map);
                post_music_fvCall.enqueue(new Callback<Post_music_fv>() {
                    @Override
                    public void onResponse(Call<Post_music_fv> call, Response<Post_music_fv> response) {

                    }

                    @Override
                    public void onFailure(Call<Post_music_fv> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class MusicAdapterRecycleViewHolder extends RecyclerView.ViewHolder {
        TextView tvNS, tvMS;
        ImageView btn_love;
        ImageView img;

        public MusicAdapterRecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNS = itemView.findViewById(R.id.tv_tenbaihat);
            tvMS = itemView.findViewById(R.id.tv_nameNS);
            img = itemView.findViewById(R.id.img_MS);
            btn_love = itemView.findViewById(R.id.btn_love_music);
        }
    }
}


