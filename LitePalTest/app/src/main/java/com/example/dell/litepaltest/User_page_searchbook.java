package com.example.dell.litepaltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_page_searchbook extends AppCompatActivity {

    private String sbname;
    private int flag;//标志位
    private String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page_searchbook);


        Intent intent = getIntent();
        uname = intent.getStringExtra("uname");
        Button button_search_search =findViewById(R.id.Button_search_search_user);
        button_search_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flag = 0;

                //获取书名
                EditText editText_sbname = findViewById(R.id.EditText_search_sbname_user);
                if (editText_sbname.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(User_page_searchbook.this, "书名不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    sbname = editText_sbname.getText().toString();
                }

                //传书名
                if (flag == 0){
                    Intent intent = new Intent(User_page_searchbook.this,User_page_bookdisplay.class);
                    intent.putExtra("sbname",sbname);
                    intent.putExtra("fcname","null_list");
                    intent.putExtra("scname","null_list");
                    intent.putExtra("uname",uname);
                    startActivityForResult(intent,1);
                }else {
                    flag = 0;
                }
            }
        });
    }
}
