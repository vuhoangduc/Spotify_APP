package com.example.duan1_spotify_clone.AdapterHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_spotify_clone.DTO.DanhMuc;
import com.example.duan1_spotify_clone.DTO.HomeItem;
import com.example.duan1_spotify_clone.R;

import java.util.List;

public class DanhMucAdapterHome   extends RecyclerView.Adapter<DanhMucAdapterHome.DanhMucViewHolder> {
    private List<HomeItem> list;

    public DanhMucAdapterHome(List<HomeItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DanhMucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_danhmuc, parent, false);
        return new DanhMucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucViewHolder holder, int position) {
        HomeItem danhMuc = list.get(position);
        if (danhMuc == null) {
            return;
        }
        holder.imgDM.setImageResource(danhMuc.getImgHI());
        holder.textView.setText(danhMuc.getNameHI());
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class DanhMucViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgDM;
        private TextView textView;

        public DanhMucViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDM = itemView.findViewById(R.id.imgDMHome);
            textView = itemView.findViewById(R.id.tenDMHome);
        }
    }
}
