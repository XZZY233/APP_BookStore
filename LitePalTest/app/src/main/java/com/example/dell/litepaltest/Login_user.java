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

public class Login_user extends AppCompatActivity {

    private  String uname ;
    private  String upwd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        //跳转到注册界面
        Button button_user_register = findViewById(R.id.Button_user_register);
        button_user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_user_register = new Intent(Login_user.this,Register.class);
                startActivityForResult(intent_user_register,1);
            }
        });

        //跳转到用户登录成功后的界面
        Button button_user_login_index = findViewById(R.id.Button_user_login_index);
        button_user_login_index.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                //获取输入的用户的用户名和密码
                EditText editText_uname = findViewById(R.id.EditText_uname);
                uname = editText_uname.getText().toString();
                EditText editText_upwd = findViewById(R.id.EditText_upwd);
                upwd = editText_upwd.getText().toString();

                //查询数据库寻找是否有匹配的用户的用户名和密码
                List<User> users = LitePal.findAll(User.class);
                for (User user : users){
                    String sql_uname = user.getUname();
                    String sql_upwd = user.getUpwd();
                        if (uname.equals(sql_uname)) {
                            if (upwd.equals(sql_upwd)) {
                                Toast.makeText(Login_user.this, "登录成功！", Toast.LENGTH_SHORT).show();
                                Intent intent_user_test = new Intent(Login_user.this, User_page.class);
                                intent_user_test.putExtra("uname",uname);
                                startActivityForResult(intent_user_test, 1);
                                break;
                            } else {
                                Toast.makeText(Login_user.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Login_user.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
         });


    }
}
