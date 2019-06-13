package com.zuk.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Control {
    private HttpSession session ;
    private HttpServletRequest request;



    public Control(HttpSession session, HttpServletRequest request) {
        this.session = session;
        this.request = request;
    }

    public HttpServletRequest work(){
        if((request.getParameter("WhatToDo").toString()).equals("Login")){
            request.setAttribute("done","done");
        }
        return request;
    }


}
