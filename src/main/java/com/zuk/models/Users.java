package com.zuk.models;

public class Users {
    private int id;
    private String login;
    private String password;
    private String role;
    private String email;
    private String phone;
    private String skill_list;

    public Users(int id, String login, String password, String role, String email, String phone, String skill_list) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.skill_list = skill_list;
    }

    public Users() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkill_list() {
        return skill_list;
    }

    public void setSkill_list(String skill_list) {
        this.skill_list = skill_list;
    }
}
