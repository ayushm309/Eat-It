package com.example.eat_it;

public class menumodel {
    String mname, price;

    public menumodel() {
    }

    public menumodel(String mname, String price) {
        this.mname = mname;
        this.price = price;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
