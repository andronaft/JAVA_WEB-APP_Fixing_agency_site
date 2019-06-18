package com.zuk.commands;

import com.mysql.cj.Session;
import com.zuk.connection.ConnectionManager;
import com.zuk.models.Users;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserCommands extends Users {
    private static final Logger LOG = Logger.getLogger(UserCommands.class);
    private String errors;
    ConnectionManager cm = new ConnectionManager();
    Connection con = cm.getConnection();


    public String check_login_password(){
        if(!getLogin().matches("^[a-zA-Z0-9]+$")||!getPassword().matches("^[a-zA-Z0-9]+$")){
            errors+="Login and password can consist only of letters of the English alphabet and numbers\n";
        }
        if(getLogin().length()<3||getLogin().length()>30||getPassword().length()<3||getPassword().length()>30){
            errors+="Login and password must be at least 3 characters and no more than 30\n";
        }
        return errors;
    }

    public String login( HttpSession session ) {

            if(check_login_password()!=null){
                return check_login_password();
            }

            if(con!=null) {
                try {
                PreparedStatement pr = con.prepareStatement("SELECT * from users where login=?");
                pr.setString(1, getLogin());
                ResultSet resultSet = pr.executeQuery();
                    if(resultSet.next()){
                        if(resultSet.getString("password").equals(DigestUtils.md5Hex(getPassword()))){
                            session.setAttribute("id",resultSet.getInt("id"));
                            session.setAttribute("role",resultSet.getString(4));
                        } else{
                            errors+="You entered an incorrect username / password\n"+"";
                            return errors;
                        }
                    }else{
                     errors+="Something is wrong";
                     return errors;
                    }
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
                errors+="Something wrong\n"+"";
                return errors;
            }
    return "done";
    }
    public String updateUserInfo(HttpServletRequest request,HttpSession session) {
        if(con!=null) {
            try {
                PreparedStatement pr = con.prepareStatement("UPDATE users  SET email=? ,phone=? where id=?");
                pr.setString(1,request.getParameter("email"));
                pr.setString(2,request.getParameter("phone"));
                pr.setString(3,session.getAttribute("id").toString());
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
    public String updateMasterInfo(HttpServletRequest request,HttpSession session) {
        if(con!=null) {
            try {
                PreparedStatement pr = con.prepareStatement("UPDATE users  SET email=? ,phone=?,skill_list=? where id=?");
                pr.setString(1,request.getParameter("email"));
                pr.setString(2,request.getParameter("phone"));
                pr.setString(3,request.getParameter("Work_with_motor")+" , "+request.getParameter("Work_with_electrical")+" , "+request.getParameter("Work_with_body")+" , "+ request.getParameter("Work_with_tires")+" , "+request.getParameter("Work_with_transmission"));
                pr.setString(4,session.getAttribute("id").toString());
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
    public String setWorkerForOrde(HttpServletRequest request) {
        if(con!=null) {
            try {
                String id = request.getParameter("Order_id");
                PreparedStatement pr = con.prepareStatement("UPDATE orders  SET taken_by=? ,status='done'  where order_id=?");
                pr.setString(1,request.getParameter("Worker_id"));
                pr.setString(2,request.getParameter("Order_id"));
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
    public String register(HttpSession session ){

        if(check_login_password()!=null){
            return check_login_password();
        }

        if(con!=null) {
            try {
                PreparedStatement pr_0 = con.prepareStatement("SELECT id from users where login=?");
                pr_0.setString(1, getLogin());
                ResultSet resultSet = pr_0.executeQuery();
                resultSet.last();
                if(resultSet.getRow()>0){
                    errors+="this login was taken\n";
                    return errors;
                }

                PreparedStatement pr = con.prepareStatement("INSERT INTO users (login, password,role) values (?,?,?)");
                pr.setString(1,getLogin());
                pr.setString(2,(DigestUtils.md5Hex(getPassword())));
                pr.setString(3, session.getAttribute("role").toString());
                if(pr.executeUpdate()!=1) {
                    errors += "Something is wrong\n";
                    return errors;
                }
                resultSet = pr_0.executeQuery();
                if(resultSet.next()) {
                    session.setAttribute("id", resultSet.getInt("id"));
                }
                else{
                    errors+="Something is wrong\n";
                    return errors;
                }
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

    public Users getPersonalInformation(HttpSession session){
        Users users =new Users();
        if(con!=null) {
            try {
                PreparedStatement pr = con.prepareStatement("SELECT * from users where id=?");
                pr.setString(1, session.getAttribute("id").toString());
                ResultSet resultSet = pr.executeQuery();
                if(resultSet.next()){
                    users = new Users(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
                }else{
                    errors+="Something is wrong";

                }
            } catch (SQLException e) {
                errors+="Something is wrong\n";
                e.printStackTrace();
                LOG.error("Error with Connect to bd" + e.toString());

            } catch (Exception e) {
                errors+="Something is wrong\n";
                e.printStackTrace();
                LOG.error("Error with Connect to bd" + e.toString());

            }finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (errors!=null) {
            session.setAttribute("errors", errors);
        }
        return users;
    }
    public ArrayList<Users> getWorkerForWork(HttpSession session,String kind){
        ArrayList<Users> list = new ArrayList<>();
        if(con!=null) {
            try {
                PreparedStatement pr = con.prepareStatement("SELECT * from users where role='master'");
                ResultSet resultSet = pr.executeQuery();
                while(resultSet.next()){

                    //if(resultSet.getString("skill_list").contains(kind)) {
                        list.add(new Users(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
                    //}
                }
            } catch (SQLException e) {
                errors+="Something is wrong\n";
                e.printStackTrace();
                LOG.error("Error with Connect to bd" + e.toString());

            } catch (Exception e) {
                errors+="Something is wrong\n";
                e.printStackTrace();
                LOG.error("Error with Connect to bd" + e.toString());

            }finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (errors!=null) {
            session.setAttribute("errors", errors);
        }

        return list;
    }

}
