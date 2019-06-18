package com.zuk.html_maker;

import com.zuk.models.Prices;
import com.zuk.models.Users;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class HtmlUser {
    public String getPersonalInformation(HttpSession session, Users users){

        String html="<h3>Изменить информацию о себе</h3><br>";
        if(session.getAttribute("role").equals("user")){
            html+="<form  method=\"Post\" action=\"simple\">\n" +
                    "<table border=\"1\"><tr><td> E-mail : </td><td><input type=\"email\" name=\"email\" value=\""+users.getEmail()+"\"/></td></tr><td>" +session.getAttribute("Phone")+
                    " :  </td><td><input type=\"text\" name=\"phone\" value=\""+users.getPhone()+"\"/> </td></tr></table>" +
                    "<input type=\"hidden\" name=\"WhatToDo\" value=\"UpdateUserInfo\" />"+
                    "<button name=\"submit\" type=\"submit\">change</button></form><br>";
            return html;
        }
        if(session.getAttribute("role").equals("master")){
            String skillList = users.getSkill_list();
            String checked1="",checked2="",checked3="",checked4="",checked5="";
            if(skillList.contains("Work_with_motor")){
                checked1="checked";
            }
            if(skillList.contains("Work_with_electrical")){
                checked2="checked";
            }
            if(skillList.contains("Work_with_body")){
                checked3="checked";
            }
            if(skillList.contains("Work_with_tires")){
                checked4="checked";
            }
            if(skillList.contains("Work_with_transmission")){
                checked5="checked";
            }

            html+="<form  method=\"Post\" action=\"simple\">\n" +
                    "<table border=\"1\"><tr><td>E-mail : </td><td><input type=\"email\" name=\"email\" value=\""+users.getEmail()+"\"/>  </td></tr><td>" +session.getAttribute("Phone")+
                    " :  </td><td><input type=\"text\" name=\"phone\" value=\""+users.getPhone()+"\"/></td></tr><tr><td>" +
                    "Могу починить : <p><input type=\"checkbox\" name=\"Work_with_motor\" value=\"Work_with_motor\""+checked1+"> Мотор</p>\n" +
                    "<p><input type=\"checkbox\" name=\"Work_with_electrical\" value=\"Work_with_electrical\""+checked2+" > Електрику</p>\n" +
                    "<p><input type=\"checkbox\" name=\"Work_with_body\" value=\"Work_with_body\" "+checked3+"> Корпус</p>\n" +
                    "<p><input type=\"checkbox\" name=\"Work_with_tires\" value=\"Work_with_tires\""+checked4+"> Шины</p>\n" +
                    "<p><input type=\"checkbox\" name=\"Work_with_transmission\" value=\"Work_with_transmission\""+checked5+"> Трансмисию</p> Мотор</p>\n" +
                    "</td></tr></table>" +
                    "<input type=\"hidden\" name=\"WhatToDo\" value=\"UpdateMasterInfo\" />"+
                    "<button name=\"submit\" type=\"submit\">change</button></form><br>";
            return html;
        }
        return "";
    }
}
