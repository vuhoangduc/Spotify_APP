package com.example.duan1_spotify_clone.AdapterHome.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.duan1_spotify_clone.DTO.DanhMuc;
import com.example.duan1_spotify_clone.DTO.Music1;
import com.example.duan1_spotify_clone.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class MusicAdapterRecycle extends RecyclerView.Adapter<MusicAdapterRecycle.MusicAdapterRecycleViewHolder> {
    private Context context;
    private List<Music1> list;
    String ten;

    public MusicAdapterRecycle(@NonNull Context context, List<Music1> list) {
        this.context=context;
        this.list=list;
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
        return new MusicAdapterRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapterRecycleViewHolder holder, int position) {
        Music1 music1 = list.get(position);
        if (music1 == null) {
            return;
        }
        holder.tvNS.setText(music1.getTen_music());
        Glide.with(context).load(list.get(position).getImg_music()).placeholder(R.drawable.hiphop).into(holder.img);
        holder.tvMS.setText(ten);
        holder.btn_love.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", "onClick: "+position);
                holder.btn_love.setImageResource(R.drawable.ic_love_music_v2);
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


