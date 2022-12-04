package com.example.duan1_spotify_clone.ActivityLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.duan1_spotify_clone.R;

public class Screen_input_gender extends AppCompatActivity {
    String email,pass,gender;
    int date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_input_gender);
        CheckBox cb1 = findViewById(R.id.cb1);
        CheckBox cb2 = findViewById(R.id.cb2);
        CheckBox cb3 = findViewById(R.id.cb3);
        CheckBox cb4 = findViewById(R.id.cb4);
        CheckBox cb5 = findViewById(R.id.cb5);
        Intent intent = getIntent();
        email = intent.getStringExtra("Email");
        pass = intent.getStringExtra("Pass");
        date = intent.getIntExtra("Age",0);

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Nữ";
                input_dieuKhoan();
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Nam";
                input_dieuKhoan();
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Phi nhị giới";
                input_dieuKhoan();
            }
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Khác";
                input_dieuKhoan();
            }
        });
        cb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Không muốn nêu củ thể";
                input_dieuKhoan();
            }
        });
    }
    public void back_gender(View view){
        startActivity(new Intent(Screen_input_gender.this, Screen_input_date.class));
    }
    public void input_dieuKhoan(){
        Intent intent = new Intent(Screen_input_gender.this, Screen_input_name.class);
        intent.putExtra("Email",email);
        intent.putExtra("Pass",pass);
        intent.putExtra("Gender",gender);
        intent.putExtra("Age",date);
        startActivity(intent);
    }
}