package com.example.eat_it;

public class homemodel
{
    String name, purl, types;


    public homemodel() {
    }

    public homemodel(String name, String purl, String types) {
        this.name = name;
        this.purl = purl;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
