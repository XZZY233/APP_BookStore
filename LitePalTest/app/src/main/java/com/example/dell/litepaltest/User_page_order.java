package com.example.dell.litepaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class User_page_order extends AppCompatActivity {

    private String uname;
    private String time;
    List<ShoppingCar> shoppingCars_order = new ArrayList<ShoppingCar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page_order);

        Intent intent = getIntent();
        uname = intent.getStringExtra("uname");

        //使用Recycler显示
        RecyclerView recyclerView = findViewById(R.id.recyclerview_order);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        shoppingCars_order = LitePal.where("uname = ? and time != ?",uname,"null").order("id desc").find(ShoppingCar.class);

        OrderAdapter orderAdapter = new OrderAdapter(shoppingCars_order);
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(User_page_order.this,User_page_order_details.class);
                intent.putExtra("uname",uname);
                intent.putExtra("time",shoppingCars_order.get(position).getTiem());
                startActivity(intent);
            }
        });
    }
}
