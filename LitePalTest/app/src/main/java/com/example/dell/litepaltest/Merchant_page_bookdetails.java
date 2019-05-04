package com.example.dell.litepaltest;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class Merchant_page_bookdetails extends AppCompatActivity {

    private  long bid;
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
        setContentView(R.layout.activity_merchant_page_bookdetails);

        //获取传过来的书名
        Intent intent = getIntent();
        String bookName = intent.getStringExtra("bname");

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
        EditText editText_bname = findViewById(R.id.EditText_bookdetails_bname);
        editText_bname.setText(bname);

        EditText editText_aname = findViewById(R.id.EditText_bookdetails_aname);
        editText_aname.setText(aname);

        //setText()方法只能传入String，所以price要类型转换
        EditText editText_bprice = findViewById(R.id.EditText_bookdetails_bprice);
        String bookPrice_details = String.valueOf(bprice);
        editText_bprice.setText(bookPrice_details);

        EditText editText_bcontent = findViewById(R.id.EditText_bookdetails_bcontent);
        editText_bcontent.setText(bcontent);

        EditText editText_bfclass = findViewById(R.id.EditText_bookdetails_bfclass);
        editText_bfclass.setText(bfclass);

        EditText editText_bsclass = findViewById(R.id.EditText_bookdetails_bsclass);
        editText_bsclass.setText(bsclass);


        //修改书籍内容
        Button Button_merchant_bookdetails_modify = findViewById(R.id.Button_merchant_modifybook_sql);
        Button_merchant_bookdetails_modify.setOnClickListener(new View.OnClickListener() {
            public  String ubname ;
            public  String uaname ;
            public  String ubcontent ;
            public  int ubprice ;
            public  String ubfclass ;
            public  String ubsclass ;
            @Override
            public void onClick(View view) {

                //获取更新后的消息
                EditText editText_bname_update = findViewById(R.id.EditText_bookdetails_bname);
                if (editText_bname_update.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Merchant_page_bookdetails.this, "书名不能为空！",Toast.LENGTH_SHORT).show();
                }else {
                    ubname = editText_bname_update.getText().toString();
                }

                EditText editText_aname_update = findViewById(R.id.EditText_bookdetails_aname);
                if (editText_aname_update.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Merchant_page_bookdetails.this, "作者名不能为空！",Toast.LENGTH_SHORT).show();
                }else {
                    uaname = editText_aname_update.getText().toString();
                }

                //ubprice是int，所以要类型转换
                EditText editText_bprice_update = findViewById(R.id.EditText_bookdetails_bprice);
                if (editText_bprice_update.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Merchant_page_bookdetails.this, "简介不能为空！",Toast.LENGTH_SHORT).show();
                }else {
                    ubprice = Integer.parseInt(editText_bprice_update.getText().toString());
                }

                EditText editText_bcontent_update = findViewById(R.id.EditText_bookdetails_bcontent);
                if (editText_bcontent_update.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Merchant_page_bookdetails.this, "价格不能为空！",Toast.LENGTH_SHORT).show();
                }else {
                    ubcontent = editText_bcontent_update.getText().toString();
                }

                EditText editText_bfclass_update = findViewById(R.id.EditText_bookdetails_bfclass);
                if (editText_bfclass_update.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Merchant_page_bookdetails.this, "一类不能为空！",Toast.LENGTH_SHORT).show();
                }else {
                    ubfclass = editText_bfclass_update.getText().toString();
                }

                EditText editText_bsclass_update = findViewById(R.id.EditText_bookdetails_bsclass);
                if (editText_bsclass_update.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Merchant_page_bookdetails.this, "二类不能为空！",Toast.LENGTH_SHORT).show();
                }else {
                    ubsclass = editText_bsclass_update.getText().toString();
                }

                //修改信息
                if (flag == 0) {
                    for (Book book : books) {
                        book.setBname(ubname);
                        book.setAname(uaname);
                        book.setBprice(ubprice);
                        book.setBcontent(ubcontent);
                        book.setBfclass(ubfclass);
                        book.setBsclass(ubsclass);
                        book.update(bid);
                        Toast.makeText(Merchant_page_bookdetails.this, "修改成功！", Toast.LENGTH_SHORT).show();
                        //跳转到书籍列表
                        Intent intent_merchant_page = new Intent(Merchant_page_bookdetails.this, Merchant_page_bookdisplay.class);
                        intent_merchant_page.putExtra("sbname","null_list");
                        startActivityForResult(intent_merchant_page, 1);
                    }
                }else {
                    flag = 0;
                }

            }
        });


        //删除该书籍
        Button Button_merchant_bookdetails_delete = findViewById(R.id.Button_merchant_deletebook_sql);
        Button_merchant_bookdetails_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNormalDialog_delete();
    }
});

    }

    //警告框showNormalDialog_delete()方法
    private void showNormalDialog_delete(){
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(Merchant_page_bookdetails.this);
        normalDialog.setTitle("确定要删除这本书吗？");
        normalDialog.setMessage("删除后不能恢复。");
        normalDialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LitePal.delete(Book.class,bid);
                        Toast.makeText(Merchant_page_bookdetails.this, "删除成功！", Toast.LENGTH_SHORT).show();
                        //跳转到书籍列表
                        Intent intent_merchant_page = new Intent(Merchant_page_bookdetails.this,Merchant_page_bookdisplay.class);
                        intent_merchant_page.putExtra("sbname","null_list");
                        startActivityForResult(intent_merchant_page,1);
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
