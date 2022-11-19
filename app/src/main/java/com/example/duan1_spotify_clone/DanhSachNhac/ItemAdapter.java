package com.example.duan1_spotify_clone.DanhSachNhac;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.duan1_spotify_clone.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private List<ItemNhac> list;
    Context context ;
    int y;
    int count_old;
    int count_index = 0;
    public ItemAdapter(Context context, int y,int count_old) {
        this.context = context;
        this.y = y;
        this.count_old = count_old;
    }

    public void setData(List<ItemNhac> list){
        this.list = list;
        count_index = list.size()-y;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dsach_nhac,parent,false);

        return new ItemViewHolder(view);
    }
    int x =0 ;
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

                Log.d("zzzzzzzzzzzzzzzzzzzzzz"+position, "onBindViewHolder: ");
                if (y == 10 ){
                    x = 5;
                }else{
                    x++;
                }
                ItemNhac itemNhac = list.get(x);
                Glide.with(context)
                        .load(itemNhac.getImg())
                        .placeholder(R.drawable.hiphop) /// add this
                        .apply(new RequestOptions().override(800, 600))
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(holder.img);
                holder.tv1.setText(itemNhac.getTv1());
                holder.tv2.setText(itemNhac.getTv2());
                y--;
                //adfasf
    }
    @Override
    public int getItemCount() {
        Log.d("zzzzzzzzzzzzzzzzzzzzzzzzzzz"+count_index, "getItemCount: ");
        return 5;
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
