package com.dev.db.data.h2.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by N5608296 on 18/08/17.
 */
@SuppressWarnings("ALL")
@Entity
@Table(name="UserLogin", schema="SENTINEL")
@NamedQueries({
        @NamedQuery(query = "SELECT u FROM UserLogin u WHERE u.userName = :userName ", name = "get user detail"),
        @NamedQuery(query = "SELECT u FROM UserLogin u", name = "get users")
})
public class UserLogin implements Serializable {
    @Id
    @Column(name = "UserName")
    private String userName;
    @Column(name = "Password")
    private String password;
    @Column(name = "UserRole")
    private Integer userRole;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "InsertTime")
    private Date insertTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdateTime")
    private Date updateTime;

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

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", insertTime=" + insertTime +
                '}';
    }
}
