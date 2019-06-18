package com.zuk.commands;

import com.zuk.connection.ConnectionManager;
import com.zuk.models.Prices;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PriceCommands extends Prices {
    private static final Logger LOG = Logger.getLogger(PriceCommands.class);
    private String errors;
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();

    public ArrayList<Prices> getAllPrices(HttpSession session) {
        ArrayList<Prices> prices = new ArrayList<>();
        if (con != null) {
            try {
                PreparedStatement pr = con.prepareStatement("SELECT * from prices");
                ResultSet resultSet = pr.executeQuery();
                while (resultSet.next()) {
                    prices.add(new Prices(resultSet.getString(1), resultSet.getInt(2)));
                }


            } catch (SQLException e) {
                errors += "Something is wrong\n";
                e.printStackTrace();
                LOG.error("Error with Connect to bd" + e.toString());
            } catch (Exception e) {
                errors += "Something is wrong\n";
                e.printStackTrace();
                LOG.error("Error with Connect to bd" + e.toString());
            }finally {

                try {
                    con.close();
                } catch (SQLException e) {
                    LOG.error("Error close con" + e.toString());
                    e.printStackTrace();
                }
            }
        }
        else {
            errors+="Something is wrong";
        }
        if (errors!=null) {
            session.setAttribute("errors", errors);
        }
        return prices;
    }
}
