package com.example.duan1_spotify_clone.ActivityLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.duan1_spotify_clone.R;

public class Screen_input_gender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_input_gender);
    }
    public void back_gender(View view){
        startActivity(new Intent(Screen_input_gender.this, Screen_input_date.class));
    }
    public void input_dieuKhoan(View view){
        startActivity(new Intent(Screen_input_gender.this, Screen_input_name.class));
    }
}