package com.example.dell.litepaltest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User_page_shoppingcar extends AppCompatActivity {

    private String uname;
    private List<Book> booksCar = new ArrayList<Book>();
    private List<ShoppingCar> ShoppingCars;
    int totalprice = 0;//总价格

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page_shoppingcar);

        //获取用户名
        Intent intent =getIntent();
        uname = intent.getStringExtra("uname");


        //同一个用户的bookid列表
        List<Integer> bookIDList = new ArrayList<Integer>();
        ShoppingCars = LitePal.where("uname = ? and time = ?",uname,"null").find(ShoppingCar.class);
        for (ShoppingCar shoppingCar : ShoppingCars) {
            bookIDList.add(shoppingCar.getBid());
        }


        //使用Recycler显示
        RecyclerView recyclerView = findViewById(R.id.recyclerview_car_user);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        for (Integer i:bookIDList){
            String id = String.valueOf(i);
            List<Book> books = LitePal.where("id = ?",id).find(Book.class);
            for (Book book : books){
                booksCar.add(book);
            }
        }


        //删除点击事件
        CarAdapter carAdapter = new CarAdapter(booksCar);
        recyclerView.setAdapter(carAdapter);
        carAdapter.setOnItemClickListener(new CarAdapter.OnItemClickListener() {
            @Override
            //删除购物车书
            public void onItemClick(View view, int position) {
                Toast.makeText(view.getContext(), "删除成功！", Toast.LENGTH_SHORT).show();
                int bookid = booksCar.get(position).getId();
                //类型转换(int -> string）
                String bookid_string = String.valueOf(bookid);
                //删除购物车内容
                LitePal.deleteAll(ShoppingCar.class,"bid = ?",bookid_string);
                Intent intent1 = new Intent(User_page_shoppingcar.this,User_page.class);
                intent1.putExtra("uname",uname);
                startActivityForResult(intent1,1);
            }
        });


        //计算总价格
        List<Integer> bookidlist_pay = new ArrayList<Integer>();//int列表用户寻找bookid
        List<Book> book_price = new ArrayList<Book>();//Book列表用于寻找价格

        //获取所有确定支付的id
        for (ShoppingCar shoppingCar : ShoppingCars){
            bookidlist_pay.add(shoppingCar.getBid());
        }

        //遍历bookid并寻找对应price
        for (Integer i : bookidlist_pay){
            String str_i = String.valueOf(i);
            List<Book> books = LitePal.where("id = ?",str_i).find(Book.class);
            for (Book book : books){
                book_price.add(book);
            }
        }

        //遍历bookprice得到totalprice
        for (Book book:book_price){
            totalprice = totalprice + book.getBprice();
        }


        //支付按钮功能
        Button button = findViewById(R.id.button_user_car_pay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNormalDialog_delete();
            }
        });

    }

    //警告框showNormalDialog_delete()方法：购买支付
    private void showNormalDialog_delete() {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(User_page_shoppingcar.this);

        normalDialog.setTitle("确定要购买这些吗？");
        normalDialog.setMessage("一共需要支付"+totalprice+"元");
        normalDialog.setPositiveButton("支付", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //当前时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String str = formatter.format(curDate);
                //设置下单时间
                ShoppingCar shoppingCars_user_sql = new ShoppingCar();
                shoppingCars_user_sql.seTime(str);
                shoppingCars_user_sql.updateAll("uname = ? and time = ?", uname, "null");
                //提示购买成功
                Toast.makeText(User_page_shoppingcar.this, "购买成功！", Toast.LENGTH_SHORT).show();
                //跳转
                Intent intent = new Intent(User_page_shoppingcar.this,User_page.class);
                intent.putExtra("uname",uname);
                startActivityForResult(intent,1);
            }
        });
        normalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //无操作
            }
        });
        //显示
        normalDialog.show();
    }
}
