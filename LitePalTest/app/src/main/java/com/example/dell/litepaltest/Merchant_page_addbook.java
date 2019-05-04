package com.example.dell.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

public class Merchant_page_addbook extends AppCompatActivity {

    private  String bname ;
    private  String aname ;
    private  String bcontent ;
    private  int bprice ;
    private  String bfclass ;
    private  String bsclass ;
    private  int flag = 0;//标志位

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_page_addbook);

        Button Button_merchant_addbook_sql = findViewById(R.id.Button_merchant_addbook_sql);
        Button_merchant_addbook_sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //判断各种输入是否规范
                //获取添加的书籍信息
                EditText editText_bname = findViewById(R.id.EditText_addbook_bname);
                if (editText_bname.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Merchant_page_addbook.this, "书名不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    bname = editText_bname.getText().toString();
                }

                EditText editText_aname = findViewById(R.id.EditText_addbook_aname);
                if (editText_aname.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Merchant_page_addbook.this, "作者名不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    aname = editText_aname.getText().toString();
                }

                EditText editText_bcontent = findViewById(R.id.EditText_addbook_bcontent);
                if (editText_bcontent.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Merchant_page_addbook.this, "简介不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    bcontent = editText_bcontent.getText().toString();
                }

                EditText editText_bprice = findViewById(R.id.EditText_addbook_bprice);
                if (editText_bprice.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Merchant_page_addbook.this, "价格不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    bprice = Integer.parseInt(editText_bprice.getText().toString());
                }

                EditText editText_bfclass = findViewById(R.id.EditText_addbook_fclass);
                if (editText_bfclass.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Merchant_page_addbook.this, "一类不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    bfclass = editText_bfclass.getText().toString();
                }

                EditText editText_bsclass = findViewById(R.id.EditText_addbook_sclass);
                if (editText_bsclass.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Merchant_page_addbook.this, "二类不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    bsclass = editText_bsclass.getText().toString();
                }

                //将书籍信息添加到数据库中
                if ( flag == 0) {
                    Book book = new Book();
                    book.setBname(bname);
                    book.setAname(aname);
                    book.setBcontent(bcontent);
                    book.setBprice(bprice);
                    book.setBfclass(bfclass);
                    book.setBsclass(bsclass);
                    book.save();
                    Toast.makeText(Merchant_page_addbook.this, "添加成功！",Toast.LENGTH_SHORT).show();
                }else {
                    flag = 0;
                }

            }
        });
    }
}
