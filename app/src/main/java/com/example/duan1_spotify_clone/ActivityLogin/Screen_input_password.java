package com.example.duan1_spotify_clone.ActivityLogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;

import com.example.duan1_spotify_clone.R;
import com.google.android.material.textfield.TextInputEditText;

public class Screen_input_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_input_password);
        final TextInputEditText editText = findViewById(R.id.edtInputPass);
        AppCompatButton btn = findViewById(R.id.btnNextPass);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=8){
                    btn.setBackgroundResource(R.drawable.btn_next2);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            go();
                        }
                    });
                }else{
                    btn.setBackgroundResource(R.drawable.btn_next);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void back_pass(View view){
        startActivity(new Intent(Screen_input_password.this, Screen_input_email.class));
    }
    public void go(){
        startActivity(new Intent(Screen_input_password.this, Screen_input_date.class));
    }

}