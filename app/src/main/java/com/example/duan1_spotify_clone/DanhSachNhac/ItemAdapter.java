package com.example.duan1_spotify_clone.DanhSachNhac;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_spotify_clone.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private List<ItemNhac> list;
    public void setData(List<ItemNhac> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dsach_nhac,parent,false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
          ItemNhac itemNhac = list.get(position);
          holder.img.setImageResource(itemNhac.getImg());
          holder.tv1.setText(itemNhac.getTv1());
          holder.tv2.setText(itemNhac.getTv2());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv1,tv2;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgDanhSachNhac);
            tv1 = itemView.findViewById(R.id.tvDanhSachNhac1);
            tv2 = itemView.findViewById(R.id.tvDanhSachNhac2);

        }
    }
}
