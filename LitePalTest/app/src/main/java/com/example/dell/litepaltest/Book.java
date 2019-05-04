package com.example.dell.litepaltest;

import org.litepal.crud.LitePalSupport;

/**
 * Created by DELL on 2019/4/9.
 */

public class Book extends LitePalSupport {
    //Book类
    int id;
    private String bname;
    private String aname;
    private String bcontent;
    private int bprice;
    private String bfclass;
    private String bsclass;

    public int getId() {
        return id;
    }

    public String getBname() {
        return bname;
    }

    public String getAname() {
        return aname;
    }

    public String getBcontent() {
        return bcontent;
    }

    public int getBprice() {
        return bprice;
    }

    public String getBfclass() {
        return bfclass;
    }

    public String getBsclass() {
        return bsclass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public void setBprice(int bprice) {
        this.bprice = bprice;
    }

    public void setBfclass(String bfclass) {
        this.bfclass = bfclass;
    }

    public void setBsclass(String bsclass) {
        this.bsclass = bsclass;
    }
}
