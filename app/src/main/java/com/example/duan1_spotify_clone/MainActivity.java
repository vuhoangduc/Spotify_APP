package com.example.duan1_spotify_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.duan1_spotify_clone.ActivityLogin.Screen_Login_SignUp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img = findViewById(R.id.imgLogo);
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this,R.animator.hieu_ung);
        animatorSet.setTarget(img);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animatorSet.start();
            }
        },500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, Screen_Login_SignUp.class));
                finish();
            }
        },3000);
    }
}
//Login:
//-Màn hình input_date:Chưa làm đẹp DatePicker,check sk
//-Màn hình input_gender:Chưa cho các radio vào 1 ground,check sk
//-Màn hình Login: Chưa check rỗng
//-Màn hình Name:Chưa có điều khoản