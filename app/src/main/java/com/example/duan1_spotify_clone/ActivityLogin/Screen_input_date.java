package com.example.duan1_spotify_clone.ActivityLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.duan1_spotify_clone.R;

public class Screen_input_date extends AppCompatActivity {
    String email,pass;
    int date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_input_date);
        Intent intent = getIntent();
        pass=intent.getStringExtra("Pass");
        email = intent.getStringExtra("Email");
        Button btn = findViewById(R.id.btnDate);
        DatePicker datePicker = findViewById(R.id.datePicker);
        TextView tv = findViewById(R.id.tvAlertDate);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if(year>2009){
                            btn.setBackgroundResource(R.drawable.btn_next);
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            });
                            tv.setText("Rất tiếc, bạn không đủ tuổi quy định của Spotify.");
                        }else{
                            btn.setBackgroundResource(R.drawable.btn_next2);
                            tv.setText("");
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Screen_input_date.this, Screen_input_gender.class);
                                    int year = datePicker.getYear();
                                    date = 2022-year;
                                    intent.putExtra("Email",email);
                                    intent.putExtra("Pass",pass);
                                    intent.putExtra("Age",date);
                                    startActivity(intent);
                                }
                            });
                        }
                }
            });
        }

    }
    public void back_date(View view){
        startActivity(new Intent(Screen_input_date.this, Screen_input_password.class));
    }
}