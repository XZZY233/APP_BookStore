package com.example.dell.litepaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Merchant_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_page);

        //添加书籍
        Button button_merchant_addbook = findViewById(R.id.Button_merchant_addbook);
        button_merchant_addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_merchant_page_addbook = new Intent(Merchant_page.this,Merchant_page_addbook.class);
                startActivityForResult(intent_merchant_page_addbook,1);
            }
        });

        //查看书籍
        Button button_merchant_bookdisplay = findViewById(R.id.Button_merchant_bookdisplay);
        button_merchant_bookdisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_merchant_page_bookdisplay = new Intent(Merchant_page.this,Merchant_page_bookdisplay.class);
                intent_merchant_page_bookdisplay.putExtra("sbname","null_list");
                startActivityForResult(intent_merchant_page_bookdisplay,1);
            }
        });

        //搜索书籍
        Button button_merchant_searchbook = findViewById(R.id.Button_merchant_searchbook);
        button_merchant_searchbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_merchant_page_searchbook = new Intent(Merchant_page.this,Merchant_page_searchbook.class);
                startActivityForResult(intent_merchant_page_searchbook,1);
            }
        });



    }
}
