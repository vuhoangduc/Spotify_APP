package com.example.duan1_spotify_clone.DanhSachNhac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_spotify_clone.R;

import java.util.List;

public class TongAdapter extends RecyclerView.Adapter<TongAdapter.TongViewHolder>{
    Context context;
    List<Tong> list;

    public TongAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<Tong> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dsach_nhac_tong,parent,false);

        return new TongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TongViewHolder holder, int position) {

        Tong tong = list.get(position);
        holder.tvTong.setText(tong.getNameTong());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        ItemAdapter adapter = new ItemAdapter();
        adapter.setData(tong.getListTong());
        holder.recyclerView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TongViewHolder extends RecyclerView.ViewHolder{
        TextView tvTong;
        RecyclerView recyclerView;


        public TongViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTong = itemView.findViewById(R.id.tvDanhSachNhac);
            recyclerView = itemView.findViewById(R.id.recycleDanhSachNhac);

        }
    }
}
