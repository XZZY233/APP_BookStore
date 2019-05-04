package com.example.dell.litepaltest;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class User_page_order_details extends AppCompatActivity {

    private String uname;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page_order_details);

        Intent intent = getIntent();
        uname = intent.getStringExtra("uname");
        time = intent.getStringExtra("time");
//        Log.d("用户名：",uname);
//        Log.d("订单时间：",time);

        List<Integer> bookid = new ArrayList<Integer>();//id
        List<Book> books_order = new ArrayList<Book>();//book
        //获取该订单详细
        List<ShoppingCar> shoppingCars = LitePal.where("uname = ? and time = ?",uname,time).find(ShoppingCar.class);
        //获取里面的bookid
        for (ShoppingCar shoppingCar : shoppingCars){
            bookid.add(shoppingCar.getBid());
        }
        //找到对应的book
        for (Integer i : bookid){
            String str_i = String.valueOf(i);
            List<Book> books = LitePal.where("id = ?",str_i).find(Book.class);
            for (Book book : books){
                books_order.add(book);
            }
        }
        //显示bookname和bookprice
        LinearLayout ll = (LinearLayout) findViewById(R.id.lay);
        for (Book book : books_order){
            String bname = book.getBname();
            int bprice = book.getBprice();
            String str_bprice = String.valueOf(bprice);
            Log.d("书名：",bname);
            Log.d("价格：",str_bprice);
            TextView tv = new TextView(this);
            tv.setText("书名："+bname+"   价格："+str_bprice);
            tv.setTextSize(20);
            ll.addView(tv);
        }


    }
}
