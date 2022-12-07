package com.example.duan1_spotify_clone.AdapterHome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_spotify_clone.DTO.GoiY;
import com.example.duan1_spotify_clone.DTO.Kenh;
import com.example.duan1_spotify_clone.DTO.Music1;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.example.duan1_spotify_clone.intefaces.SongItemAction;
import com.example.duan1_spotify_clone.intefaces.SongItemActionHome;

import java.util.List;
    public class MusicAdapterHome extends RecyclerView.Adapter<MusicAdapterHome.MusicViewHolder>{
        private Context context;
        private List<Music1> list;
//        private SongItemActionHome songItemAction;
        private SongItemAction songItemAction;
        public MusicAdapterHome(Context context, List<Music1> list) {
            this.context = context;
            this.list = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MusicAdapterHome.MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music_danhmuc,parent,false);
            return new MusicAdapterHome.MusicViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MusicAdapterHome.MusicViewHolder holder, int position) {
            Music1 goiY = list.get(position);
            if(goiY==null){
                return;
            }
            Glide.with(context).load(list.get(position).getImg_music()).placeholder(R.drawable.hiphop).into(holder.img);
            holder.textView.setText(goiY.getTen_music());
            holder.textView2.setText("Sơn Tùng M-TP");
            holder.cardView.setBackgroundResource(0);
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    songItemAction = (SongItemAction) context;
                    songItemAction.setOnItemClickListener(position,list);
                    ((MainActivity2)context).showFragment(false);
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
        public class MusicViewHolder extends RecyclerView.ViewHolder{
            private ImageView img;
            private TextView textView,textView2;
            CardView cardView;
            public MusicViewHolder(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.imgMSHome);
                textView = itemView.findViewById(R.id.tenMSHome);
                textView2 = itemView.findViewById(R.id.tenKMSHome);
                cardView = itemView.findViewById(R.id.background3);
            }
        }




    }
