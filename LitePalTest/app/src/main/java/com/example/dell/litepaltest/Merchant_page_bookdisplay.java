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

public class Merchant_page_bookdisplay extends AppCompatActivity {

    private String sbname;//搜索的书名
    private int flag;//标志位

    private List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_page_bookdisplay);

        //显示修改后的书籍
        Intent intent = getIntent();
        sbname = intent.getStringExtra("sbname");
        if (sbname.equals("null_list")){
            flag = 0;
        }
        //显示搜索对应的书籍
        if (!sbname.equals("null_list")){
            flag = 1;
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
        }


        //使用Recycler显示
        RecyclerView recyclerView = findViewById(R.id.recyclerview_book);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(bookList);
        recyclerView.setAdapter(adapter);
        //调用自定义接口实现Recycler
        adapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //将书名传给下一个活动，并跳转
                Intent intent = new Intent(Merchant_page_bookdisplay.this,Merchant_page_bookdetails.class);
                intent.putExtra("bname",bookList.get(position).getBname().toString());
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
}
