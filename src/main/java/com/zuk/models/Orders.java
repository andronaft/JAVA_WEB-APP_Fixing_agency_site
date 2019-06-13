package com.zuk.models;

import java.util.Date;

public class Orders {
    private int order_id;
    private int order_by;
    private String work_kind;
    private int price;
    private String descriprion_work;
    private String payment_state;
    private String status;
    private int taken_by;
    private Date order_date;
    private String description_text;
    private Date response_date;

    public Orders(int order_id, int order_by, String work_kind, int price, String descriprion_work, String payment_state, String status, int taken_by, Date order_date, String description_text, Date response_date) {
        this.order_id = order_id;
        this.order_by = order_by;
        this.work_kind = work_kind;
        this.price = price;
        this.descriprion_work = descriprion_work;
        this.payment_state = payment_state;
        this.status = status;
        this.taken_by = taken_by;
        this.order_date = order_date;
        this.description_text = description_text;
        this.response_date = response_date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_by() {
        return order_by;
    }

    public void setOrder_by(int order_by) {
        this.order_by = order_by;
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

    public String getDescriprion_work() {
        return descriprion_work;
    }

    public void setDescriprion_work(String descriprion_work) {
        this.descriprion_work = descriprion_work;
    }

    public String getPayment_state() {
        return payment_state;
    }

    public void setPayment_state(String payment_state) {
        this.payment_state = payment_state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTaken_by() {
        return taken_by;
    }

    public void setTaken_by(int taken_by) {
        this.taken_by = taken_by;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getDescription_text() {
        return description_text;
    }

    public void setDescription_text(String description_text) {
        this.description_text = description_text;
    }

    public Date getResponse_date() {
        return response_date;
    }

    public void setResponse_date(Date response_date) {
        this.response_date = response_date;
    }


}
