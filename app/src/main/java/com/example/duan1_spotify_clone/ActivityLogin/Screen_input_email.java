package com.example.duan1_spotify_clone.ActivityLogin;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.duan1_spotify_clone.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class Screen_input_email extends AppCompatActivity {
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_input_email);
        final TextInputEditText editText = findViewById(R.id.edtInputEmail);
        AppCompatButton btn = findViewById(R.id.btnNextEmail);
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+com+";
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().matches(emailPattern)){
                        email = s.toString();
                        btn.setBackgroundResource(R.drawable.btn_next2);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Screen_input_email.this, Screen_input_password.class);
                                intent.putExtra("Email",email);
                                startActivity(intent);
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
                    if(s.toString().equals("thong@gmail.com")){
                        openDialog(Gravity.CENTER);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }
    public void back_email(View view){
        startActivity(new Intent(Screen_input_email.this, Screen_Login_SignUp.class));
    }


    private void openDialog(int gravity){
       Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_errol);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.background_errol));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();
        TextView tv = dialog.findViewById(R.id.tvCloseDialog);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if(Gravity.CENTER==gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }
    }
}