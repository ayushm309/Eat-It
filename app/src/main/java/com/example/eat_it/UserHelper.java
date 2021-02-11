package com.example.eat_it;

public class UserHelper {
    String fname, rUsername, rEmail, rPassword, rPhoneno;
    public UserHelper() {

    }

    public UserHelper(String fname, String rUsername, String rEmail, String rPassword, String rPhoneno) {
        this.fname = fname;
        this.rUsername = rUsername;
        this.rEmail = rEmail;
        this.rPassword = rPassword;
        this.rPhoneno = rPhoneno;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getrUsername() {
        return rUsername;
    }

    public void setrUsername(String rUsername) {
        this.rUsername = rUsername;
    }

    public String getrEmail() {
        return rEmail;
    }

    public void setrEmail(String rEmail) {
        this.rEmail = rEmail;
    }

    public String getrPassword() {
        return rPassword;
    }

    public void setrPassword(String rPassword) {
        this.rPassword = rPassword;
    }

    public String getrPhoneno() {
        return rPhoneno;
    }

    public void setrPhoneno(String rPhoneno) {
        this.rPhoneno = rPhoneno;
    }
}
