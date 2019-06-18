package com.zuk.requestDispatcher;

import com.zuk.commands.OrderCommands;
import com.zuk.commands.UserCommands;
import com.zuk.html_maker.HtmlMaker;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RequestDispatcher {
    private static final Logger LOG = Logger.getLogger(RequestDispatcher.class);

    private HttpSession session ;
    private HttpServletRequest request;
    private String html;
    UserCommands user;

    public RequestDispatcher(HttpSession session, HttpServletRequest request) {
        this.session = session;
        this.request = request;
    }
    private void setUserRole(){
        if(request.getParameter("role")!=null){
            session.setAttribute("role","master");
        }else{
            session.setAttribute("role","user");
        }
    }
    private void setUser(){
        user = new UserCommands();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));

    }
    public HttpServletRequest work() throws ServletException, IOException {
        request.setAttribute("errors","");
        request.setAttribute("html","");



        if((request.getParameter("WhatToDo").equals("Login"))){
            setUser();
            String response =user.login(session);
            if(response.equals("done")){
                 //setUserRole();
                 request.setAttribute("GoTo","WEB-INF/jsps/index.jsp");
            }
            else{
                request.setAttribute("errors",response);
                request.setAttribute("GoTo","WEB-INF/jsps/login.jsp");
            }
            return request;
        }

        if((request.getParameter("WhatToDo").equals("Registration"))){
            setUser();
            String response =user.register(session);
            if(response.equals("done")){
                setUserRole();
                request.setAttribute("GoTo","WEB-INF/jsps/index.jsp");
            }
            else{
                request.setAttribute("errors",response);
                request.setAttribute("GoTo","WEB-INF/jsps/register.jsp");
            }
            return request;
        }
        if((request.getParameter("WhatToDo").equals("SetWorkerForOrder"))){
            user = new UserCommands();
            String response = user.setWorkerForOrde(request);

            if(response.equals("done")){
                //setUserRole();
                request.setAttribute("GoTo","WEB-INF/jsps/index.jsp");
            }
            else{
                request.setAttribute("errors",response);
                request.setAttribute("GoTo","WEB-INF/jsps/login.jsp");
            }
            return request;
        }
        if((request.getParameter("WhatToDo").equals("UpdateUserInfo"))){
            user = new UserCommands();
            String response = user.updateUserInfo(request,session);
            if(response.equals("done")){
                //setUserRole();
                request.setAttribute("GoTo","WEB-INF/jsps/index.jsp");
            }
            return request;
        }
        if((request.getParameter("WhatToDo").equals("NewOrder"))){
            OrderCommands orderCommands = new OrderCommands();

            String response = orderCommands.newOrder(request,session);
            if(response.equals("done")){
                //setUserRole();
                request.setAttribute("GoTo","WEB-INF/jsps/index.jsp");
            }
            return request;
        }
        if((request.getParameter("WhatToDo").equals("UpdateStatusOrder"))){
            OrderCommands orderCommands = new OrderCommands();

            String response = orderCommands.updateStatusOrder(request);
            if(response.equals("done")){
                //setUserRole();
                request.setAttribute("GoTo","WEB-INF/jsps/index.jsp");
            }
            return request;
        }
        if((request.getParameter("WhatToDo").equals("UpdateMasterInfo"))){
            user = new UserCommands();
            String response = user.updateMasterInfo(request,session);
            if(response.equals("done")){
                //setUserRole();
                request.setAttribute("GoTo","WEB-INF/jsps/index.jsp");
            }
            return request;
        }
            HtmlMaker htmlMaker = new HtmlMaker(session,request);
            html = htmlMaker.createHtml();
            request.setAttribute("errors",session.getAttribute("errors").toString());
            request.setAttribute("html", html);

        return request;
    }


}
