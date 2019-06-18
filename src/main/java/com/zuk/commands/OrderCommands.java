package com.zuk.commands;

import com.zuk.connection.ConnectionManager;
import com.zuk.models.Orders;
import com.zuk.models.Prices;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

public class OrderCommands extends Orders {
    private static final Logger LOG = Logger.getLogger(PriceCommands.class);
    private String errors;
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();
    PreparedStatement pr;

    public ArrayList<Orders> getAllOrders(HttpSession session) {
        ArrayList<Orders> orders = new ArrayList<>();
        if (con != null) {
            try {
                if(session.getAttribute("role").equals("manager")) {
                    pr = con.prepareStatement("SELECT * from orders");
                }
                else {
                    pr = con.prepareStatement("SELECT * from orders where status='done'");
                }
                ResultSet resultSet = pr.executeQuery();
                while (resultSet.next()) {
                    orders.add(new Orders(resultSet.getInt(1), resultSet.getInt(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getDate(9),resultSet.getString(10),resultSet.getDate(11)));
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
                    e.printStackTrace();
                }
            }
        } else {
            errors += "Something is wrong";
        }
        if (errors != null) {
            session.setAttribute("errors", errors);
        }
        return orders;
    }

    public ArrayList<Orders> getOrderAtAcc(HttpSession session) {
        ArrayList<Orders> orders = new ArrayList<>();
        if (con != null) {
            try {
                if(session.getAttribute("role").equals("manager")) {
                    pr = con.prepareStatement("SELECT * from orders Where status='not done'");
                }
                if(session.getAttribute("role").equals("master")) {
                    pr = con.prepareStatement("SELECT * from orders where taken_by=?");
                    pr.setString(1, session.getAttribute("id").toString());
                }
                if(session.getAttribute("role").equals("user")) {
                    pr = con.prepareStatement("SELECT * from orders where order_by=?");
                    pr.setString(1,session.getAttribute("id").toString());
                }
                ResultSet resultSet = pr.executeQuery();
                while (resultSet.next()) {
                    orders.add(new Orders(resultSet.getInt(1), resultSet.getInt(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getDate(9),resultSet.getString(10),resultSet.getDate(11)));
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
                    e.printStackTrace();
                }
            }
        } else {
            errors += "Something is wrong";
        }
        if (errors != null) {
            session.setAttribute("errors", errors);
        }
        return orders;
    }

    public String updateStatusOrder(HttpServletRequest request) {
        if(con!=null) {
            try {
                Date curr_date = new Date(System.currentTimeMillis());
                PreparedStatement pr = con.prepareStatement("UPDATE orders  SET status=? , response_date=? where order_id=?");
                pr.setString(1,"done");
                pr.setDate(2,curr_date);
                pr.setString(3,request.getParameter("Id_order"));
                pr.executeUpdate();

            } catch (SQLException e) {
                errors+="Something is wrong\n";
                e.printStackTrace();
                LOG.error("Error with Connect to bd" + e.toString());
                return errors;
            } catch (Exception e) {
                errors+="Something is wrong\n";
                e.printStackTrace();
                LOG.error("Error with Connect to bd" + e.toString());
                return  errors;
            }finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        else {
            errors+="Something bab3\n";
            return errors;
        }
        return "done";
    }
    public String newOrder(HttpServletRequest request,HttpSession session) {
        if(con!=null) {
            try {
                Date curr_date = new Date(System.currentTimeMillis());
                PreparedStatement pr1 = con.prepareStatement("SELECT price from prices where work_kind=? ");
                pr1.setString(1,request.getParameter("work_kind"));
                ResultSet resultSet = pr1.executeQuery();
                PreparedStatement pr = con.prepareStatement("insert into orders  ( work_kind , order_date, description_work, order_by,price,status) values (? , ?   ,?  , ?  , ? , 'not done')");
                pr.setString(1,request.getParameter("work_kind"));
                pr.setDate(2,curr_date);
                pr.setString(3,request.getParameter("description_work"));
                pr.setInt(4,Integer.parseInt(session.getAttribute("id").toString()));
                pr.setInt(5,12);
                pr.executeUpdate();

            } catch (SQLException e) {
                errors+="Something is wrong\n";
                e.printStackTrace();
                LOG.error("Error with Connect to bd" + e.toString());
                return errors;
            } catch (Exception e) {
                errors+="Something is wrong\n";
                e.printStackTrace();
                LOG.error("Error with Connect to bd" + e.toString());
                return  errors;
            }finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        else {
            errors+="Something bab3\n";
            return errors;
        }
        return "done";
    }
}
