package com.example.duan1_spotify_clone.ActivityLogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.duan1_spotify_clone.DBHelper.SaveUser;
import com.example.duan1_spotify_clone.DTO.User;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_user_v1;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Screen_input_Login extends AppCompatActivity {
    AppCompatButton button;
    TextInputEditText edtEmail,edtPass;
    String email="",pass="";
    TextInputLayout layout_email,layout_pass;
    List<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_input_login);
        button = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPass = findViewById(R.id.edtLoginPass);
        layout_email = findViewById(R.id.layout_email);
        layout_pass = findViewById(R.id.layout_password);
        JsonParser_User_v1 jsonParser_user_v1 = new JsonParser_User_v1(this);
            jsonParser_user_v1.execute("http://192.168.0.102:3000/users");
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
                               Working_Login();
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
                            Working_Login();
                        }
                    });

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    String User_email;
    Boolean check = false;
    private boolean CheckEmail(){
        User_email = edtEmail.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        for (int i = 0; i < users.size(); i++) {
            if(User_email.equals(users.get(i).getEmail_user())){
                check =true;
            }
        }
        if(check == false){
            layout_email.setError("Email chua ton tai!");
        }else if (!User_email.matches(emailPattern)){
            layout_email.setError("Sai dinh dang Email");
            return false;
        }else{
            layout_email.setError(null);
            return true;
        }
        return check;
    }

    private boolean CheckPassWord(){
        String User_password = edtPass.getText().toString().trim();
        SaveUser saveUser = new SaveUser(this);
        saveUser.open();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail_user().equals(User_email)){
                if (users.get(i).getPass_user().equals(User_password)){
                    User user = new User(users.get(i).getName_user(),users.get(i).getGender_user(),users.get(i).getAge_user(),users.get(i).getEmail_user(),users.get(i).getPass_user());
                    if (saveUser.getData().size()==0){
                        saveUser.Save_NEW_USER(user);
                    }else {
                        saveUser.DELETE();
                        saveUser.Save_NEW_USER(user);
                    }
                    return true;
                }
            }
        }
            layout_pass.setError("Sai mat khau!");
            return false;


    }
    public void Working_Login(){
        if(CheckEmail() == true){
            CheckPassWord();
        }
        if(!CheckEmail()| !CheckPassWord()){
            Log.d("Tai khoan chua ton tai", "Login_Working: ");
            return;
        }
        JsonParser_user_v1 jsonParser_user_v1 = new JsonParser_user_v1(this);
        jsonParser_user_v1.execute("http://192.168.0.102:3000/users");
        go();
    }
    public void back_singup(View view){
        startActivity(new Intent(Screen_input_Login.this,Screen_Login_SignUp.class));
    }
    public void go(){
        startActivity(new Intent(Screen_input_Login.this, MainActivity2.class));
    }


    public class JsonParser_User_v1 extends AsyncTask<String,Integer, List<User>> {
        Context context;

        public JsonParser_User_v1(Context context) {
            this.context = context;
        }

        @Override
        protected List<User> doInBackground(String... strings) {
            String line = "";
            String datav1 = "";
            List<User> data = new ArrayList<>();
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while ((line = bufferedReader.readLine()) != null) {
                    datav1 += line;
                }
                //
                if (!datav1.isEmpty()) {
                    JSONArray jsonarray = new JSONArray(datav1);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject value = jsonarray.getJSONObject(i);
                        User user = new User(value.getString("name"), value.getString("gender"), value.getInt("date"), value.getString("email"), value.getString("password"));
                        data.add(user);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(List<User> users_v1) {
            super.onPostExecute(users_v1);
            users.addAll(users_v1);
        }
    }
}