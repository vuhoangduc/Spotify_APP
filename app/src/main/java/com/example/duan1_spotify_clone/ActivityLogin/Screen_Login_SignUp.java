package com.example.duan1_spotify_clone.ActivityLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.duan1_spotify_clone.R;

public class Screen_Login_SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login_sign_up);
    }
  public void dkFree(View view){
        startActivity(new Intent(Screen_Login_SignUp.this, Screen_input_email.class));
    }
    public void login(View view){
        startActivity(new Intent(Screen_Login_SignUp.this, Screen_input_Login.class));
    }
}