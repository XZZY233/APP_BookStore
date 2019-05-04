package com.example.dell.litepaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //跳转到用户界面
        Button button_user = findViewById(R.id.Button_user_login);
        button_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        //跳转到用户界面
        Intent intent_user = new Intent(Login.this,Login_user.class);
        startActivityForResult(intent_user,1);
            }
        });

        //跳转到商家界面
        Button button_merchant = findViewById(R.id.Button_merchant_login);
        button_merchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_merchant = new Intent(Login.this,Login_merchant.class);
                startActivityForResult(intent_merchant,1);
            }
        });


    }
}
