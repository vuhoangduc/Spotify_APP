package com.example.duan1_spotify_clone.AdapterHome.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_spotify_clone.DTO.DanhMuc;
import com.example.duan1_spotify_clone.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.Random;

public class DanhMucAdapter  extends RecyclerView.Adapter<DanhMucAdapter.DanhMucViewHolder>{
    private Context context;
    private List<DanhMuc> list;

    public DanhMucAdapter(Context context, List<DanhMuc> list) {
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
        DanhMuc danhMuc = list.get(position);
        if(danhMuc==null){
            return;
        }
        holder.imgDM.setImageResource(danhMuc.getImg());
        holder.textView.setText(danhMuc.getName_DM());
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.linearLayout.setBackgroundColor(color);
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
