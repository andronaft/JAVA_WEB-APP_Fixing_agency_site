package com.zuk.models;

public class Prices {
    private String work_kind;
    private int price;
    public Prices(){

    }
    public Prices(String work_kind, int price) {
        this.work_kind = work_kind;
        this.price = price;
    }

    public String getWork_kind() {
        return work_kind;
    }

    public void setWork_kind(String work_kind) {
        this.work_kind = work_kind;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
