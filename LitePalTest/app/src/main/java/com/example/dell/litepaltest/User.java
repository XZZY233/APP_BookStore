package com.example.dell.litepaltest;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

/**
 * Created by DELL on 2019/4/13.
 */

public class User extends LitePalSupport {
    //User类
    private int id;
    private String uname;
    private String upwd;
    private int uphone;
    private String uaddress;

    public int getId() {
        return id;
    }

    public String getUname() {
        return uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public int getUphone() {
        return uphone;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public void setUphone(int uphone) {
        this.uphone = uphone;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }
}
