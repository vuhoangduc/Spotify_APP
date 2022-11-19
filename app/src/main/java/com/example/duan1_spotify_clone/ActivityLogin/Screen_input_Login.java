package com.example.duan1_spotify_clone.ActivityLogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.duan1_spotify_clone.R;
import com.google.android.material.textfield.TextInputEditText;

public class Screen_input_Login extends AppCompatActivity {
    AppCompatButton button;
    TextInputEditText edtEmail,edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_input_login);
        button = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPass = findViewById(R.id.edtLoginPass);
    }
    public void back_singup(View view){
        startActivity(new Intent(Screen_input_Login.this,Screen_Login_SignUp.class));
    }
}