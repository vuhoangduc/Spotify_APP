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
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.example.duan1_spotify_clone.intefaces.SongItemAction;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<Music1> {
        List<Music1> list;
        Context context;
        TextView tvNS,tvMS;
        String ten;
    private SongItemAction songItemAction;
        ImageView img;
        public MusicAdapter(@NonNull Context context, List<Music1> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
        }
    public MusicAdapter(@NonNull Context context, List<Music1> list,String ten) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
        this.ten=ten;
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
            tvNS = v.findViewById(R.id.tv_tenbaihat);
            tvMS = v.findViewById(R.id.tv_nameNS);
            img = v.findViewById(R.id.img_MS);
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songItemAction = (SongItemAction) context;
                songItemAction.setOnItemClickListener(position,list);
                ((MainActivity2)context).showFragment(false);
            }
        });
        tvNS.setText(music1.getTen_music());
        Glide.with(context).load(list.get(position).getImg_music()).placeholder(R.drawable.hiphop).into(img);
        tvMS.setText(ten);
        return v;
        }
}
