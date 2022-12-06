package com.example.duan1_spotify_clone.AdapterHome;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_spotify_clone.DBHelper.Dont_Open;
import com.example.duan1_spotify_clone.DTO.Kenh;
import com.example.duan1_spotify_clone.DTO.TheLoai;
import com.example.duan1_spotify_clone.DanhSachNhac.FragmentDanhSachNhac;
import com.example.duan1_spotify_clone.Fragment.FragmentHome;
import com.example.duan1_spotify_clone.Fragment.FragmentKenh;
import com.example.duan1_spotify_clone.Fragment.FragmentLibrary;
import com.example.duan1_spotify_clone.Fragment.FragmentList;
import com.example.duan1_spotify_clone.Fragment.FragmentMusic;
import com.example.duan1_spotify_clone.Fragment.FragmentPramium;
import com.example.duan1_spotify_clone.Fragment.FragmentSearch;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.example.duan1_spotify_clone.intefaces.KenhSend;
import com.example.duan1_spotify_clone.intefaces.SongItemAction;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.Random;

public class KenhAdapterHome extends RecyclerView.Adapter<KenhAdapterHome.KenhViewHolder> {
    private Context context;
    private List<Kenh> list;
    KenhSend kenhSend;
    public KenhAdapterHome(Context context, List<Kenh> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public KenhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kenh_home,parent,false);
        return new KenhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KenhViewHolder holder, int position) {
        Kenh kenh = list.get(position);
        if(kenh==null){
            return;
        }
        Glide.with(context).load(list.get(position).getImg_kenh()).placeholder(R.drawable.hiphop).into(holder.img);
        holder.textView.setText(kenh.getTen_kenh());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kenhSend = (KenhSend) context;
                kenhSend.setOnItemClickListener(kenh);

            }
        });
    }
    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }
    public class KenhViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView textView;
        public KenhViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgKenhHome);
            textView = itemView.findViewById(R.id.tvKenhHome);
        }
    }




}
