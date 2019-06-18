package com.zuk.servlets;

import com.zuk.connection.ConnectionManager;
import com.zuk.requestDispatcher.RequestDispatcher;
import org.apache.log4j.Logger;

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
    private static final Logger LOG = Logger.getLogger(SimpleServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String url="";
        if(request.getParameter("WhatToDo").equals("LogOut")){
            session.removeAttribute("id");
            session.removeAttribute("role");
            request.getRequestDispatcher("WEB-INF/jsps/login.jsp").forward(request, response);
            return;
        }
        if(request.getParameter("WhatToDo")!=null) {
            RequestDispatcher requestDispatcher = new RequestDispatcher(session, request);
            HttpServletRequest resDisp = requestDispatcher.work();
            if(request.getAttribute("GoTo")!=null) {
                url = resDisp.getAttribute("GoTo").toString();
                request.setAttribute("errors", resDisp.getAttribute("errors"));
            }
            else{
                request.setAttribute("errors",resDisp.getAttribute("errors"));
                out.print(resDisp.getAttribute("html"));
                return;
            }
        }
        else {
            url = request.getParameter("GoTo");
        }
        if(url!=null){
           request.getRequestDispatcher(url).forward(request, response);
           return;
        }


        out.print("Something is wrong");

        //out.print(request.getAttribute("id"));
        out.print(request.getParameter("id"));
        //out.print(request.getParameterValues("id"));
        out.print("<h1>Hello Servlet</h1>");
        out.print("gdfgd\ndfgdfg\ndfgdfg\ndgdf\n");
        System.out.println(request.getParameterValues("htmlId"));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if(session.getAttribute("id")!=null){
             request.getRequestDispatcher("WEB-INF/jsps/index.jsp").forward(request, response);
             return;
        }
        request.getRequestDispatcher("WEB-INF/jsps/login.jsp").forward(request, response);

    }
}
