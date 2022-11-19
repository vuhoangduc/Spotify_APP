package com.example.duan1_spotify_clone.ActivityLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.duan1_spotify_clone.R;

public class Screen_input_date extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_input_date);
    }
    public void back_date(View view){
        startActivity(new Intent(Screen_input_date.this, Screen_input_password.class));
    }
    public void input_Gender(View view){
        startActivity(new Intent(Screen_input_date.this, Screen_input_gender.class));
    }
}