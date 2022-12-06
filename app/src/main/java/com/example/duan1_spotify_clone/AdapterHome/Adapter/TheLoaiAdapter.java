package com.example.duan1_spotify_clone.AdapterHome.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.Random;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.DanhMucViewHolder>{
    private Context context;
    private List<TheLoai> list;
    public TheLoaiAdapter(Context context, List<TheLoai> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DanhMucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhmuc,parent,false);
        return new DanhMucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucViewHolder holder, int position) {
        TheLoai theLoai = list.get(position);
        if(theLoai==null){
            return;
        }
        Glide.with(context).load(list.get(position).getImg()).placeholder(R.drawable.hiphop).into(holder.imgDM);
        holder.textView.setText(theLoai.getTitle());
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.linearLayout.setBackgroundColor(color);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dont_Open dont_open = new Dont_Open(context);
                dont_open.open();
                if (!dont_open.getData().equals("")){
                    dont_open.DELETE_ALL();
                    dont_open.ADD_NEW(theLoai.getId());
                }else {
                    dont_open.ADD_NEW(theLoai.getId());
                }
                ((MainActivity2) context).setCurrentPage(4);
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
    public class DanhMucViewHolder extends RecyclerView.ViewHolder{
            private RoundedImageView imgDM;
    private LinearLayout linearLayout;
    private TextView textView;
        public DanhMucViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDM = itemView.findViewById(R.id.ImgDanhMuc);
            linearLayout = itemView.findViewById(R.id.BackDanhMuc);
            textView = itemView.findViewById(R.id.tvDanhMuc);
        }
    }




}
