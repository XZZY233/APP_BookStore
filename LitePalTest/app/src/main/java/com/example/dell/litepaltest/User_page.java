package com.example.dell.litepaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User_page extends AppCompatActivity {

    private String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        Intent intent =getIntent();
        uname = intent.getStringExtra("uname");

        //查看书籍
        Button button_user_bookdisplay = findViewById(R.id.Button_user_bookdisplay);
        button_user_bookdisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_user_page_bookdisplay = new Intent(User_page.this,User_page_bookdisplay.class);
                intent_user_page_bookdisplay.putExtra("sbname","null_list");
                intent_user_page_bookdisplay.putExtra("fcname","null_list");
                intent_user_page_bookdisplay.putExtra("scname","null_list");
                intent_user_page_bookdisplay.putExtra("uname",uname);
                startActivityForResult(intent_user_page_bookdisplay,1);
            }
        });

        //分类查找书籍
        Button button_user_classifybook = findViewById(R.id.Button_user_classifybook);
        button_user_classifybook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_user_page_classifybook = new Intent(User_page.this,User_page_classify_display.class);
                intent_user_page_classifybook.putExtra("uname",uname);
                startActivityForResult(intent_user_page_classifybook,1);
            }
        });

        //搜索书籍
        Button button_user_searchbook = findViewById(R.id.Button_user_searchbook);
        button_user_searchbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_user_page_searchbook = new Intent(User_page.this,User_page_searchbook.class);
                intent_user_page_searchbook.putExtra("uname",uname);
                startActivityForResult(intent_user_page_searchbook,1);
            }
        });

        //购物车
        Button button_user_shoppingcar = findViewById(R.id.Button_user_shoppingcar);
        button_user_shoppingcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_user_page_shoppingcar = new Intent(User_page.this,User_page_shoppingcar.class);
                intent_user_page_shoppingcar.putExtra("uname",uname);
                startActivityForResult(intent_user_page_shoppingcar,1);
            }
        });

        //购订单
        Button button_user_order = findViewById(R.id.Button_user_order);
        button_user_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_user_page_shoppingcar = new Intent(User_page.this,User_page_order.class);
                intent_user_page_shoppingcar.putExtra("uname",uname);
                startActivityForResult(intent_user_page_shoppingcar,1);
            }
        });


    }
}
