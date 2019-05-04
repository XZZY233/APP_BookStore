package com.example.dell.litepaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

public class User_page_bookdetails extends AppCompatActivity {

    private  int bid;
    private  String bname ;
    private  String aname ;
    private  String bcontent ;
    private  int bprice ;
    private  String bfclass ;
    private  String bsclass ;
    private  int flag = 0;//标志位
    private String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page_bookdetails);

        //获取传过来的书名
        Intent intent = getIntent();
        String bookName = intent.getStringExtra("bname");
        //获取用户名
        uname = intent.getStringExtra("uname");
        Log.d("加入购物车页面：","用户名： "+uname);


        //查询数据库中对应书名的书并显示
        final List<Book> books = LitePal.where("bname = ?",bookName).find(Book.class);
        for (Book book : books){
            bid = book.getId();
            bname = book.getBname();
            aname = book.getAname();
            bcontent = book.getBcontent();
            bprice = book.getBprice();
            bfclass = book.getBfclass();
            bsclass = book.getBsclass();
        }
        EditText editText_bname = findViewById(R.id.EditText_bookdetails_bname_user);
        editText_bname.setText(bname);

        EditText editText_aname = findViewById(R.id.EditText_bookdetails_aname_user);
        editText_aname.setText(aname);

        //setText()方法只能传入String，所以price要类型转换
        EditText editText_bprice = findViewById(R.id.EditText_bookdetails_bprice_user);
        String bookPrice_details = String.valueOf(bprice);
        editText_bprice.setText(bookPrice_details);

        EditText editText_bcontent = findViewById(R.id.EditText_bookdetails_bcontent_user);
        editText_bcontent.setText(bcontent);

        EditText editText_bfclass = findViewById(R.id.EditText_bookdetails_bfclass_user);
        editText_bfclass.setText(bfclass);

        EditText editText_bsclass = findViewById(R.id.EditText_bookdetails_bsclass_user);
        editText_bsclass.setText(bsclass);


        //加入购物车
        Button addbutton = findViewById(R.id.Button_merchant_addcar_user);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCar shoppingCar = new ShoppingCar();
                shoppingCar.setUname(uname);
                shoppingCar.setBid(bid);
                shoppingCar.seTime("null");
                shoppingCar.save();
                Toast.makeText(User_page_bookdetails.this,"成功加入购物车！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
