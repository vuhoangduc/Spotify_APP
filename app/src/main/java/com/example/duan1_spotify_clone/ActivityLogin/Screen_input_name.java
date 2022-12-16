package com.example.duan1_spotify_clone.ActivityLogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.duan1_spotify_clone.DBHelper.SaveUser;
import com.example.duan1_spotify_clone.DTO.User;
import com.example.duan1_spotify_clone.Fragment.POST_API_JSON.LoginResult;
import com.example.duan1_spotify_clone.Fragment.POST_API_JSON.RetrofitInterFace;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Screen_input_name extends AppCompatActivity {
    String email,gender,pass,name;
    int date;
    Retrofit retrofit;
    LoginResult loginResult;
    RetrofitInterFace retrofitInterFace;
    String BASE_URL = "http://192.168.0.102:3000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_input_name);
        Intent intent = getIntent();
        email = intent.getStringExtra("Email");
        gender = intent.getStringExtra("Gender");
        pass = intent.getStringExtra("Pass");
        date = intent.getIntExtra("Age",0);
        SaveUser saveUser = new SaveUser(this);
        saveUser.open();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterFace = retrofit.create(RetrofitInterFace.class);
        final TextInputEditText editText = findViewById(R.id.edtInputName);
        AppCompatButton btn = findViewById(R.id.btnNextHome);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    btn.setBackgroundResource(R.drawable.btn_next);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                }else{
                    name = s.toString();
                    btn.setBackgroundResource(R.drawable.btn_next2);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            User user = new User(name,gender,date,email,pass);

                            HashMap<String,String> map = new HashMap<>();
                            map.put("email",email);
                            map.put("password",pass);
                            map.put("date",""+date);
                            map.put("name",name);
                            map.put("gender",gender);
                            Call<LoginResult> call = retrofitInterFace.wxecuteSignup(map);
                            call.enqueue(new Callback<LoginResult>() {
                                @Override
                                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                                        if (response.code() == 200){
                                        }else if (response.code() == 400){
                                            if (saveUser.getData().size()==0){
                                                saveUser.Save_NEW_USER(user);
                                            }else {
                                                saveUser.DELETE();
                                                saveUser.Save_NEW_USER(user);
                                            }
                                            go();
                                        }
                                }

                                @Override
                                public void onFailure(Call<LoginResult> call, Throwable t) {
                                    Toast.makeText(Screen_input_name.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    public void go(){
        startActivity(new Intent(Screen_input_name.this, MainActivity2.class));
    }
    public  void back_gender(View view){
            startActivity(new Intent(Screen_input_name.this, Screen_input_gender.class));

    }
    private class SendJSONObject extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}