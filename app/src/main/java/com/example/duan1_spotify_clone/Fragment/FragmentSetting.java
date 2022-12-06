package com.example.duan1_spotify_clone.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.duan1_spotify_clone.ActivityLogin.Screen_Login_SignUp;
import com.example.duan1_spotify_clone.ActivityLogin.Screen_input_Login;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;

public class FragmentSetting extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        Button btn = v.findViewById(R.id.backtoPrenium);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity2)getContext()).setCurrentPage(3);
            }
        });
        LinearLayout layout = v.findViewById(R.id.backtoPrenium);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Screen_Login_SignUp.class));
            }
        });
        return v;
    }
}