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

public class Register extends AppCompatActivity {

    private  String uname ;
    private  String upwd ;
    private  int uphone ;
    private  String uadress ;
    private static int flag=0;   //标记位

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button Button_user_register = findViewById(R.id.Button_user_register_sql);
        Button_user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //判断各种输入是否规范
                //获取注册的用户信息
                EditText editText_uname = findViewById(R.id.EditText_register_uname);
                if (editText_uname.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Register.this, "用户名不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    uname = editText_uname.getText().toString();
                }

                EditText editText_upwd = findViewById(R.id.EditText_register_upwd);
                if (editText_upwd.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Register.this, "密码不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    upwd = editText_upwd.getText().toString();
                }

                EditText editText_uphone = findViewById(R.id.EditText_register_uphone);
                if (editText_uphone.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Register.this, "手机号不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    //uphone是int，所以要类型转换
                    uphone = Integer.parseInt(editText_uphone.getText().toString());
                }

                EditText editText_uaddress = findViewById(R.id.EditText_register_uaddress);
                if (editText_uaddress.getText().toString().equals("")){
                    flag = 1;
                    Toast.makeText(Register.this, "地址不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    uadress = editText_uaddress.getText().toString();
                }


                //判断用户是否已经注册过
                List<User> users = LitePal.findAll(User.class);
                for (User user : users){
                    String sql_uname = user.getUname();
                    if (sql_uname.equals(uname)){
                        flag = 1;
                        Toast.makeText(Register.this, "该用户名已经存在！",Toast.LENGTH_SHORT).show();
                    }
                }

                //将用户信息添加到数据库中
                if ( flag == 0) {
                    User user = new User();
                    user.setUname(uname);
                    user.setUpwd(upwd);
                    user.setUphone(uphone);
                    user.setUaddress(uadress);
                    user.save();
                    Toast.makeText(Register.this, "注册成功！",Toast.LENGTH_SHORT).show();
                }else {
                    flag = 0;
                }




            }
        });

    }
}
