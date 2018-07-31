package com.example.am99m.foodhippo;

/**
 * Created by am99m on 26-06-2018.
 */

public class artist1 {
    String price;
    String name;


    public artist1() {

    }

    public artist1(String price,String name) {

        this.price = price;
        this.name=name;
    }


    public String getPrice() {
        return price;
    }
    public String getName() { return  name;}



    public void setPrice(String price) {
        this.price = price;
    }
    public void setName(String name) { this.name=name;}

}
