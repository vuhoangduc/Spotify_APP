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

import com.example.duan1_spotify_clone.DTO.DanhMuc;
import com.example.duan1_spotify_clone.DTO.HomeItem;
import com.example.duan1_spotify_clone.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.Random;

public class NgheSiAdapterHome  extends RecyclerView.Adapter<NgheSiAdapterHome.NgheSiViewHolder> {
    private List<HomeItem> list;


    public NgheSiAdapterHome(List<HomeItem> list) {
        this.list = list;
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
        HomeItem danhMuc = list.get(position);
        if (danhMuc == null) {
            return;
        }
        holder.imgDM.setImageResource(danhMuc.getImgHI());
        holder.textView.setText(danhMuc.getNameHI());
        holder.cardView.setBackgroundResource(0);
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
