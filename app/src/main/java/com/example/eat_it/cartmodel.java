package com.example.eat_it;

public class cartmodel {
    String Quantity, date, mname, price, time;

    public cartmodel() {
    }

    public cartmodel(String quantity, String date, String mname, String price, String time) {
        Quantity = quantity;
        this.date = date;
        this.mname = mname;
        this.price = price;
        this.time = time;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
