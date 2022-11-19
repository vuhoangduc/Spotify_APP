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
import androidx.fragment.app.Fragment;

import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.R;

import java.util.ArrayList;
import java.util.List;

public class PlayListAdapter2 extends ArrayAdapter<Playlist> {
    List<Playlist> list;
    Context context;
     TextView tv;

    public PlayListAdapter2(@NonNull Context context, List<Playlist> list) {
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
            v = inflater.inflate(R.layout.item_list, null);
        }
        final Playlist playlist = list.get(position);
        if(playlist!=null){
              tv = v.findViewById(R.id.tvNameList);
              tv.setText(playlist.getTenList());
        }
        return v;
    }
}
