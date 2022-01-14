package com.example.springbatchexample.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_data")
public class UserData implements Serializable {

    @Id
    private String userId;
    private String name;
    private String status;


    public UserData() {
    }

    public UserData(String userId, String name, String status) {
        this.userId = userId;
        this.name = name;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
