package com.example.duan1_spotify_clone.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.duan1_spotify_clone.R;

import org.w3c.dom.Text;


public class NowPlayingFragmentBottom extends Fragment {

    ImageView control_btn;
    TextView tenBaiHat;
    SeekBar thoiGianChay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing_bottom, container, false);

        return view;
    }
}