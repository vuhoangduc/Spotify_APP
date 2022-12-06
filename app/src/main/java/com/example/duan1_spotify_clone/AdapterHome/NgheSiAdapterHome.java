package com.example.duan1_spotify_clone.AdapterHome;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_spotify_clone.DTO.DanhMuc;
import com.example.duan1_spotify_clone.DTO.HomeItem;
import com.example.duan1_spotify_clone.DTO.Kenh;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.Random;

public class NgheSiAdapterHome  extends RecyclerView.Adapter<NgheSiAdapterHome.NgheSiViewHolder> {
    private List<Kenh> list;
    Context context;


    public NgheSiAdapterHome(List<Kenh> list,Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NgheSiAdapterHome.NgheSiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_ns, parent, false);
        return new NgheSiAdapterHome.NgheSiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NgheSiAdapterHome.NgheSiViewHolder holder, int position) {
        Kenh kenh = list.get(position);
        if (kenh == null) {
            return;
        }


        Glide.with(context).load(list.get(position).getImg_kenh()).placeholder(R.drawable.hiphop).into(holder.imgDM);
        holder.textView.setText(kenh.getTen_kenh());
        holder.cardView.setBackgroundResource(0);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity2)context).setCurrentPage(5);
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

    public class NgheSiViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgDM;
        private TextView textView;
        CardView cardView;

        public NgheSiViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDM = itemView.findViewById(R.id.imgNSHome);
            textView = itemView.findViewById(R.id.tenNSHome);
            cardView = itemView.findViewById(R.id.background);
        }
    }
}
