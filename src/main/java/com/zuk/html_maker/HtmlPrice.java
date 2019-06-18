package com.zuk.html_maker;

import com.zuk.models.Prices;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class HtmlPrice {

public String getHtmlAllPrices(HttpSession session, ArrayList<Prices> list){
    String html="";//session.getAttribute("prices").toString()+"<br><br>";

    for (Prices i:list) {
        html+="<h4> "+ session.getAttribute(i.getWork_kind()).toString() +"  </h4> - "+ i.getPrice()+" <br><br>";
    }
    return html;
}
}
