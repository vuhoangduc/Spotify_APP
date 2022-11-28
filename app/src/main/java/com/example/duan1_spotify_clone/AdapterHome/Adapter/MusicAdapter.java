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

import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.R;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<Playlist> {
        List<Playlist> list;
        Context context;
        TextView tvNS,tvMS;
        ImageView img;
        public MusicAdapter(@NonNull Context context, List<Playlist> list) {
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
         final Playlist playlist = list.get(position);
        if(playlist!=null){
        tvNS = v.findViewById(R.id.tv_nameMS);
        tvMS = v.findViewById(R.id.tv_nameNS);
        img = v.findViewById(R.id.img_MS);
        }
        return v;
        }
}
