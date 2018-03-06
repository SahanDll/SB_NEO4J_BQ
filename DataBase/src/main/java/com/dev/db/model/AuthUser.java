package com.dev.db.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by N5608296 on 18/08/17.
 */
public class AuthUser implements Serializable {
    private String userName;
    private String password;
    private Integer userRole;
    private String updatedUser;
    private Integer status;
    private byte[] encPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public byte[] getEncPassword() {
        return encPassword;
    }

    public void setEncPassword(byte[] encPassword) {
        this.encPassword = encPassword;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", updatedUser='" + updatedUser + '\'' +
                ", status=" + status +
                ", encPassword=" + Arrays.toString(encPassword) +
                '}';
    }
}
