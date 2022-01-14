package com.example.springbatchexample.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "req_data")
public class ReqData implements Serializable {

    @Id
    private String userId;
    private String name;

    public ReqData(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public ReqData() {
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
}
