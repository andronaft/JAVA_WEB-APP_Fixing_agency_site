package com.zuk.servlets;

import com.zuk.connection.ConnectionManager;
import com.zuk.control.Control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//@WebServlet(value = "simple")
public class SimpleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Control control = new Control(session,request);
        HttpServletRequest requestControl = control.work();
        if(requestControl.getAttribute("done")=="done"){
            out.print("done");
        }
        out.print(request.getParameter("WhatToDo"));

       // out.print(request.getAttribute("id"));
        out.print(request.getParameter("id"));
        //out.print(request.getParameterValues("id"));
        out.print("<h1>Hello Servlet</h1>");
        System.out.println("gdfgd");
        System.out.println(request.getParameterValues("id"));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        if(session.getAttribute("id")!=null){
             request.getRequestDispatcher("WEB-INF/jsps/index.jsp").forward(request, response);
             return;
        }
        //session.setAttribute("id","2");
        request.getRequestDispatcher("WEB-INF/jsps/login.jsp").forward(request, response);
        PrintWriter out = response.getWriter();
        Connection cn=null;
       // request.getRequestDispatcher("WEB-INF/jsps/index.jsp").forward(request, response);



            ConnectionManager cm = new ConnectionManager();
            Connection con = cm.getConnection();
            if(con!=null) {
                try {
                    out.print("<h1>Hello Servlet2</h1>");
                    PreparedStatement pr = con.prepareStatement("update users set login='andronaft' where login='a'");
                    out.print("2 have done");
                    pr.executeUpdate();
                } catch (SQLException e) {
                    out.print("error in 2 sqlex");
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{

            }

        out.print("<h1>Hello Servlet</h1>");
    }
}
