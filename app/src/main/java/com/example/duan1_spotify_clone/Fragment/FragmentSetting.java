package com.example.duan1_spotify_clone.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duan1_spotify_clone.ActivityLogin.Screen_Login_SignUp;
import com.example.duan1_spotify_clone.ActivityLogin.Screen_input_Login;
import com.example.duan1_spotify_clone.DBHelper.SaveUser;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;

public class FragmentSetting extends Fragment {
    TextView tv_name,tv_email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        TextView btn = v.findViewById(R.id.logout_btn);
        tv_name = v.findViewById(R.id.tv_nameUser_seting);
        tv_email =v.findViewById(R.id.email_user_seting);
        SaveUser saveUser = new SaveUser(getActivity());

        tv_email.setText(saveUser.getData().get(0).getEmail_user());
        tv_name.setText(saveUser.getData().get(0).getName_user());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveUser user = new SaveUser(getActivity());
                user.DELETE();
                startActivity(new Intent(getActivity(), Screen_Login_SignUp.class));
            }
        });
        LinearLayout layout = v.findViewById(R.id.backtoPrenium);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity2)getContext()).setCurrentPage(3);

            }
        });
        ImageView backSetting = v.findViewById(R.id.backSetting);
        backSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity2)getContext()).setCurrentPage(0);
            }
        });
        return v;
    }
}