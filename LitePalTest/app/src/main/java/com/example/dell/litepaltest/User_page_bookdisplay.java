package com.example.dell.litepaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class User_page_bookdisplay extends AppCompatActivity {

    private String sbname;//搜索的书名
    private String fcname;//一类名
    private String scname;//二类名
    private int flag ;//标志位
    private String uname;

    private List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page_bookdisplay);

        //显示所有书籍
        Intent intent = getIntent();
        sbname = intent.getStringExtra("sbname");
        fcname = intent.getStringExtra("fcname");
        scname = intent.getStringExtra("scname");
        uname = intent.getStringExtra("uname");
        //显示所有书籍
        if (sbname.equals("null_list")){
            flag = 0;
        }
        //显示搜索对应的书籍
        if (!sbname.equals("null_list")){
            flag = 1;
        }
        //显示分类书籍
        if (!fcname.equals("null_list")){
            if (!scname.equals("null_list")) {
                flag = 2;
            }
        }


        //分支显示
        switch (flag){
            case 0:
                //初始化所有书籍列表（遍历整个book表）
                initBooks();
                break;
            case 1:
                //初始化模糊搜索书籍列表
                initBooks_search();
                break;
            case 2:
                //分类显示
                initBooks_classify();
                break;
        }


        //使用Recycler显示
        RecyclerView recyclerView = findViewById(R.id.recyclerview_book_user);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter_user adapter = new BookAdapter_user(bookList);
        recyclerView.setAdapter(adapter);
        //调用自定义接口实现点击事件
        adapter.setOnItemClickListener(new BookAdapter_user.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //将书名传给下一个活动，并跳转
                Intent intent = new Intent(User_page_bookdisplay.this,User_page_bookdetails.class);
                intent.putExtra("bname",bookList.get(position).getBname().toString());
                intent.putExtra("uname",uname);
                startActivity(intent);
            }
        });

    }


    //初始所有书籍
    private void initBooks() {
        bookList = LitePal.findAll(Book.class);
    }

    //初始模糊搜索书籍
    private void initBooks_search() {
        bookList = LitePal.where("bname like ?","%"+sbname+"%").find(Book.class);
    }

    //初始分类书籍
    private void initBooks_classify() {
        bookList = LitePal.where("bfclass = ? and bsclass = ?",fcname,scname).find(Book.class);
    }
}


