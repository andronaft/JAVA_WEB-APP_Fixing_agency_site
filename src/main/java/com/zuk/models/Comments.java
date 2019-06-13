package com.zuk.models;

public class Comments {
    private int order_id;
    private int user_id;
    private int score;
    private String text;

    public Comments(int order_id,int user_id,int score,String text){
        this.order_id = order_id;
        this.user_id = user_id;
        this.score = score;
        this.text = text;
    }
    /*
    public Comments(ResultSet resultSet) throws SQLException{
        this.order_id = ResultSetTablesData
    } */
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_id(){
        return order_id;
    }

    public void setUser_id(int user_id){
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
