package com.example.duan1_spotify_clone.AdapterHome.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.duan1_spotify_clone.DTO.Music1;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.R;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<Playlist> {
        List<Music1> list;
        Context context;
        TextView tvNS,tvMS;
        ImageView img;

    public MusicAdapter(@NonNull Context context, int resource, List<Music1> list) {
        super(context, resource);
        this.list = list;
        this.context=context;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_music, null);
        }
         final Music1 music1 = list.get(position);
        if(music1!=null){
        tvNS = v.findViewById(R.id.tv_nameMS);
        tvMS = v.findViewById(R.id.tv_nameNS);
        img = v.findViewById(R.id.img_MS);
        tvMS.setText(music1.getTen_music());
        tvMS.setText("hello");
        Glide.with(context).load(list.get(position).getImg_music()).placeholder(R.drawable.hiphop).into(img);
        }
        return v;
        }
}
