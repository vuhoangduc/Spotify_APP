package com.example.duan1_spotify_clone.ActivityLogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.google.android.material.textfield.TextInputEditText;

public class Screen_input_Login extends AppCompatActivity {
    AppCompatButton button;
    TextInputEditText edtEmail,edtPass;
    String email="",pass="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        go();
        setContentView(R.layout.activity_screen_input_login);
        button = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPass = findViewById(R.id.edtLoginPass);
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                   email=s.toString();
                   if(s.length()<=0||pass.length()<=0){
                       button.setBackgroundResource(R.drawable.btn_next);
                       button.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                           }
                       });
                   }else{
                       button.setBackgroundResource(R.drawable.btn_next2);
                       button.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               go();
                           }
                       });
                   }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pass=s.toString();
                if(s.length()<=0||email.length()<=0){
                    button.setBackgroundResource(R.drawable.btn_next);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });

                }else{
                    button.setBackgroundResource(R.drawable.btn_next2);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            go();
                        }
                    });

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void back_singup(View view){
        startActivity(new Intent(Screen_input_Login.this,Screen_Login_SignUp.class));
    }
    public void go(){
        startActivity(new Intent(Screen_input_Login.this, MainActivity2.class));
    }
}