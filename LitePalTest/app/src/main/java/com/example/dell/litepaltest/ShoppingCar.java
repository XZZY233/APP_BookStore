package com.example.dell.litepaltest;

import org.litepal.crud.LitePalSupport;

/**
 * Created by DELL on 2019/4/22.
 */

public class ShoppingCar extends LitePalSupport {
    //shoppingcar类
    int id;
    String uname;
    int bid;
    String time;

    public int getId() {
        return id;
    }

    public String getUname() {
        return uname;
    }

    public int getBid() {
        return bid;
    }

    public String getTiem() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void seTime(String time) {
        this.time = time;
    }
}
