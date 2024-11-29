package com.example.demo.productRestController;

import java.util.ArrayList;

public class Users {
    private String username;
    private String pass;

    public Users(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public static boolean validateUser(ArrayList<Users> userList, String username, String password) {
        for (Users user : userList) {
            if (user.getUsername().equals(username) && user.getPass().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
