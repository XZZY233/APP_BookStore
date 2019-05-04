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
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class Login_merchant extends AppCompatActivity {

    //定义输入的商家用户名和密码
    private   String mname ;
    private  String mpwd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_merchant);

        Button button_merchant_login = findViewById(R.id.Button_merchant_login);
        button_merchant_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //获取输入的商家用户名和密码
                EditText editText_mname = findViewById(R.id.EditText_mname);
                mname = editText_mname.getText().toString();
                EditText editText_mpwd = findViewById(R.id.EditText_mpwd);
                mpwd = editText_mpwd.getText().toString();

                //查询数据库寻找是否有匹配的商家用户名和密码
                List<Merchant> merchants = LitePal.findAll(Merchant.class);
                for (Merchant merchant : merchants){
                    String sql_mname = merchant.getMname();
                    String sql_mpwd = merchant.getMpwd();
                    if (sql_mname.equals(mname)){
                        if (sql_mpwd.equals(mpwd)){
                            Intent intent_merchant_page = new Intent(Login_merchant.this,Merchant_page.class);
                            startActivityForResult(intent_merchant_page,1);
                        }else{
                            Toast.makeText(Login_merchant.this, "用户名或密码错误！",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Login_merchant.this, "用户名或密码错误！",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });




    }
}
