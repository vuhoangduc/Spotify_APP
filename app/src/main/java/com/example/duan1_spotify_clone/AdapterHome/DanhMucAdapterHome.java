package com.example.duan1_spotify_clone.AdapterHome;

import android.content.Context;
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
import com.example.duan1_spotify_clone.DTO.WordCup;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.example.duan1_spotify_clone.intefaces.SongItemAction;
import com.example.duan1_spotify_clone.intefaces.SongItemActionHome;

import java.util.List;

public class DanhMucAdapterHome extends RecyclerView.Adapter<DanhMucAdapterHome.DanhMucViewHolder> {
    private List<WordCup> list;
    Context context;
    SongItemActionHome songItemAction;
    public DanhMucAdapterHome(Context context,List<WordCup> list) {
        this.list = list;
        this.context= context;
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
        WordCup wordCup = list.get(position);
        if (wordCup == null) {
            return;
        }
        Glide.with(context).load(list.get(position).getImg_wc()).placeholder(R.drawable.hiphop).into(holder.imgDM);
        holder.textView.setText(wordCup.getTen_wc());
        holder.textView2.setText(wordCup.getNam_wc());
        holder.layout.setBackgroundResource(0);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songItemAction = (SongItemActionHome) context;
                songItemAction.setOnItemClickListenerv1(wordCup);
                ((MainActivity2)context).showFragment(false);
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
    public class DanhMucViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgDM;
        private TextView textView,textView2;
        CardView layout;

        public DanhMucViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDM = itemView.findViewById(R.id.imgDMHome);
            textView = itemView.findViewById(R.id.tenDMHome);
            textView2 = itemView.findViewById(R.id.tenTTHome);
            layout = itemView.findViewById(R.id.background2);
        }
    }
}
