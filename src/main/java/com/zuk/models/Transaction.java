package com.zuk.models;

public class Transaction {
    private int transaction_id;
    private int order_id;
    private int user_id;
    private int price;

    public Transaction(int transaction_id, int order_id, int user_id, int price) {
        this.transaction_id = transaction_id;
        this.order_id = order_id;
        this.user_id = user_id;
        this.price = price;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
