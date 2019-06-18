package com.zuk.html_maker;

import com.zuk.models.Orders;
import com.zuk.models.Users;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class HtmlOrder {
    String script="<script>\n" +
            "        $(function(){\n" +
            "            $('.link').click(function(e){\n" +
            "                e.preventDefault();\n" +
            "                var id = $(this).attr('href');\n" +
            "\n" +
            "                $.ajax({\n" +
            "                    url: 'simple',\n" +
            "                    type:'post',\n" +
            "                    data: 'WhatToDo='+id,\n" +
            "                    success:function(responce){\n" +
            "                        $('#aticle').html(responce);\n" +
            "                    }\n" +
            "                })\n" +
            "            })\n" +
            "\n" +
            "        });</script>";
    public String getAllOrders(HttpSession session, ArrayList<Orders> list,int count){
        String html="";
        String url1=null,url2=null;
        int max = count*1;
        if(count>1){
            url1=String.valueOf(count-1);
        }
        if(list.size()<count*1){
            max=list.size();
        }
        if(list.size()>count*1){
            url2=String.valueOf(count+1);
        }
        String role = session.getAttribute("role").toString();
        if(role.equals("manager")) {
            for (int i = (count-1)*1 ;i<max; i++) {
                html += list.get(i).toString() + "<br>";
            }

        }
        if(!role.equals("manager")){
            html+="<table border=\"1\"><tr><td>"+session.getAttribute("Order_nuber")+"</td><td>"+session.getAttribute("Order_by")+"</td><td>"+session.getAttribute("Work_kind")+"</td><td>"+session.getAttribute("Taken_by")+"</td><td>"+session.getAttribute("When_done")+"</td></tr>";

            for (int i = (count-1)*1 ;i<max; i++) {
                html += "<tr><td>"+list.get(i).getOrder_id() + "</td><td>"+ list.get(i).getOrder_by()+"</td><td>"+session.getAttribute(list.get(i).getWork_kind())+"</td><td>"+list.get(i).getTaken_by()+"</td><td>"+list.get(i).getResponse_date()+"</td></tr>";
            }
            html+="</table>";
        }
        html+=script;
        if(url1!=null) {
            html += "<br> <a class=\"link\" href=\"3-" + url1 + "\">back</a>  ";
        }
        if(url2!=null){
            html += "<br> <a class=\"link\" href=\"3-" + url2 + "\">next</a>  ";
        }

        return html;
    }

    public String getOrderAtAcc(HttpSession session, ArrayList<Orders> list){
        String html="";


        if (session.getAttribute("role").equals("manager")) {
            html+=script;
            html += "<table border=\"1\"><tr><td>" + session.getAttribute("Order_nuber") + "</td><td>" + session.getAttribute("Work_kind") + "</td><td></td></tr>";

            for (Orders i : list) {
                html += "<tr><td>" + i.getOrder_id() + "</td><td>" + i.getWork_kind()+"</td><td>"+"<a class=\"link\" href=\"4-"+i.getOrder_id()+"-"+i.getWork_kind()+"\"> Назначить работника</a></td></tr>";
            }
            html+="</table>";
        }
        if (session.getAttribute("role").equals("master")) {
            html += "<br><h3>Ваши заказы</h3><br><table border=\"1\"><tr><td>" + session.getAttribute("Order_nuber") + "</td><td>" + session.getAttribute("Order_by") + "</td><td>" + session.getAttribute("Work_kind") + "</td><td>" + session.getAttribute("Taken_by") + "</td><td>" + "Уточнения" + "</td><td>" + "Cтатус" + "</td><td>" + "Дата подачи" + "</td><td>" + session.getAttribute("When_done") + "</td><td></td></tr>";

            for (Orders i : list) {
                html += "<tr><td>" + i.getOrder_id() + "</td><td>" + i.getOrder_by() + "</td><td>" + session.getAttribute(i.getWork_kind()) + "</td><td>" + i.getTaken_by() + "</td><td>" + i.getDescriprion_work() + "</td><td>" + i.getStatus() + "</td><td>" + i.getOrder_date() + "</td><td>" + i.getResponse_date() + "</td><td>";
                if(!i.getStatus().equals("done")){
                    html+="<form  method=\"Post\" action=\"simple\">\n" +
                            "    <input type=\"hidden\" name=\"WhatToDo\" value=\"UpdateStatusOrder\" />\n" +
                            "    <input type=\"hidden\" name=\"Id_order\" value=\""+i.getOrder_id()+"\" />\n" +
                            "    <button name=\"submit\" type=\"submit\">Сделал</button>\n" +
                            "</form>";
                }
                else{
                    html+="Сделано";
                }
                html+="</td></tr>";
            }
            html+="</table>";
        }
        if(session.getAttribute("role").equals("user")) {
            html += "<br><h3>Ваши заказы</h3><br><table border=\"1\"><tr><td>" + session.getAttribute("Order_nuber") + "</td><td>" + session.getAttribute("Order_by") + "</td><td>" + session.getAttribute("Work_kind") + "</td><td>" + session.getAttribute("Taken_by") + "</td><td>" + "Уточнения" + "</td><td>" + "Cтатус" + "</td><td>" + "Дата подачи" + "</td><td>" + session.getAttribute("When_done") + "</td></tr>";

            for (Orders i : list) {
                html += "<tr><td>" + i.getOrder_id() + "</td><td>" + i.getOrder_by() + "</td><td>" + session.getAttribute(i.getWork_kind()) + "</td><td>" + i.getTaken_by() + "</td><td>" + i.getDescriprion_work() + "</td><td>" + i.getStatus() + "</td><td>" + i.getOrder_date() + "</td><td>" + i.getResponse_date() + "</td></tr>";

            }

            html += "</table><br><h3>New order for repair</h3><br><br>";
            html += "<form method=\"Post\" action=\"simple\">\n" +
                    "<table border=\"1\"><tr><td>    <p><input type=\"text\" name=\"description_work\" placeholder=\"Description work\" required></p>\n</td></tr><tr><td>" +
                    "    <p>Kind of work</p>\n" +
                    "    <p><input name=\"work_kind\" type=\"radio\" value=\"Work_with_motor\" checked> motor</p>\n" +
                    "    <p><input name=\"work_kind\" type=\"radio\" value=\"Work_with_tires\"> tires</p>\n" +
                    "    <p><input name=\"work_kind\" type=\"radio\" value=\"Work_with_electrical\"> electrical</p>\n" +
                    "    <p><input name=\"work_kind\" type=\"radio\" value=\"Work_with_transmission\" > transmission</p>\n" +
                    "    <p><input name=\"work_kind\" type=\"radio\" value=\"Work_with_body\"> body</p>\n" +
                    "    <input type=\"hidden\" name=\"WhatToDo\" value=\"NewOrder\" />\n" +
                    "    <button name=\"submit\" type=\"submit\">New order </button>\n</td></tr></table>" +
                    "</form>";
        }
        return html;
    }
    public String getFormForSetWorker(HttpSession session, ArrayList<Users> list,String order_id){
        String html="";
        html+="<form  method=\"post\" action=\"simple\">";
        html+="Количесвто не назначеных заказов : "+list.size()+"<br>";
        if(list.size()>0) {
            for (Users i : list) {
                html += "Id работников которые могут выполнить работу <input type=\"radio\" name=\"Worker_id\" value=\"" + i.getId() + "\" >" + i.getId();

            }
            html += " <input type=\"hidden\" name=\"Order_id\" value=\""+order_id+"\" />\n";
            html += " <input type=\"hidden\" name=\"WhatToDo\" value=\"SetWorkerForOrder\" />\n" +
                    "            <button name=\"submit\" type=\"submit\">Назначить работника</button></form>";
        }
        return html;
    }

}
