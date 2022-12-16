package com.example.duan1_spotify_clone.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1_spotify_clone.DBHelper.Save_music_fv;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Music_FV;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;


public class Frament_Music_FV extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public Frament_Music_FV() {
        // Required empty public constructor
    }

    ImageView btn_back,control_btn;
    TextView tv_soluong_music_fv;
    RecyclerView ds_music_fv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frament__music__f_v, container, false);
        btn_back = view.findViewById(R.id.back_btn);
        ds_music_fv =view.findViewById(R.id.lvListBH_fv);
        control_btn =view.findViewById(R.id.playMusic);
        tv_soluong_music_fv = view.findViewById(R.id.tv_soluong_bai_hat_fv);
        Save_music_fv save_music_fv = new Save_music_fv(getActivity());
        tv_soluong_music_fv.setText( save_music_fv.getData().size()+" bài hát");
        LinearLayoutManager managerPL = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        ds_music_fv.setLayoutManager(managerPL);
        JsonParser_Music_FV jsonParser_music_fv = new JsonParser_Music_FV(getActivity(),ds_music_fv,control_btn);
        jsonParser_music_fv.execute("http://192.168.0.102:3000/musics");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity2) getContext()).setCurrentPage(2);
            }
        });

        return view;
    }
}