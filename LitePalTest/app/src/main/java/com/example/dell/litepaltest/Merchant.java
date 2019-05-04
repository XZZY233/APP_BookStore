package com.example.dell.litepaltest;

import org.litepal.crud.LitePalSupport;

/**
 * Created by DELL on 2019/4/12.
 */

public class Merchant extends LitePalSupport {
    //Merchant类
    private int id;
    private String mname;
    private String mpwd;

    public int getId() {
        return id;
    }

    public String getMname() {
        return mname;
    }

    public String getMpwd() {
        return mpwd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setMpwd(String mpwd) {
        this.mpwd = mpwd;
    }
}
