package com.zuk.html_maker;

import com.zuk.commands.OrderCommands;
import com.zuk.commands.PriceCommands;
import com.zuk.commands.UserCommands;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HtmlMaker {
    public HtmlMaker(HttpSession session, HttpServletRequest request) {
        this.session = session;
        this.request = request;
    }
    private HttpSession session ;
    private HttpServletRequest request;

    public String createHtml(){
        session.setAttribute("errors","");
        String html;
        String whatToDo =request.getParameter("WhatToDo");
        String[] parts = whatToDo.split("-");
        if(parts[0].equals("1")) {
            html = session.getAttribute("Main_text").toString();
            return html;
        }
        if(parts[0].equals("2")) {
            HtmlPrice htmlPrice = new HtmlPrice();
            PriceCommands priceCommands = new PriceCommands();
            html = htmlPrice.getHtmlAllPrices(session,priceCommands.getAllPrices(session));
            return html;
        }
        if(parts[0].equals("4")) {

            HtmlUser htmlUser = new HtmlUser();
            UserCommands userCommands = new UserCommands();
            html = htmlUser.getPersonalInformation(session,userCommands.getPersonalInformation(session));
            HtmlOrder htmlOrder = new HtmlOrder();
            if(!parts[1].equals("0")){
                UserCommands userCommands1 = new UserCommands();
                html+= htmlOrder.getFormForSetWorker(session,userCommands1.getWorkerForWork(session,parts[2]),parts[1]);
            }
            else {
                OrderCommands orderCommands = new OrderCommands();
                html += htmlOrder.getOrderAtAcc(session, orderCommands.getOrderAtAcc(session));
            }
            html+="<form method=\"Post\" action=\"simple\">\n" +
                    "    <input type=\"hidden\" name=\"WhatToDo\" value=\"LogOut\" />\n" +
                    "    <button name=\"submit\" type=\"submit\">LogOut</button>\n" +
                    "</form>";
            return html;
        }

        if(parts[0].equals("3")){
            HtmlOrder htmlOrder = new HtmlOrder();
            OrderCommands orderCommands = new OrderCommands();
            html = htmlOrder.getAllOrders(session,orderCommands.getAllOrders(session),Integer.parseInt(parts[1]));
            return html;
        }


        html="Something is wrong, try to reload page";
        return html;

    }
}
