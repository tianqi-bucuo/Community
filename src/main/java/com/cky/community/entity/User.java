package com.cky.community.entity;

import java.util.Date;

public class User {
    private int id;
    private String accountId;
    private String name;
    private String token;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account_id='" + accountId + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", create_time=" + createTime +
                '}';
    }
}
