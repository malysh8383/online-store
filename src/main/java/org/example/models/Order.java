package org.example.models;

import java.sql.Timestamp;

public class Order {
    private Integer id;
    private Integer number;
    private String status;
    private Integer userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Order(Integer id, Integer number, String status, Integer userId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.number = number;
        this.status = status;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Order( Integer number, String status, Integer userId, Timestamp createdAt, Timestamp updatedAt) {
        this.number = number;
        this.status = status;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Order(Integer number, String status, Integer userId, Timestamp updatedAt) { // апдейт
        this.number = number;
        this.status = status;
        this.userId = userId;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdateAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Order [ id=" + id + ", number=" + number + ", status=" + status + ", user_id" + userId + ", created_at=" + createdAt + ", updated_at=" + updatedAt + "]";
    }
}
