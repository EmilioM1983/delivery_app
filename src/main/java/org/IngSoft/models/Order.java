package org.IngSoft.models;

import org.IngSoft.models.Enum.StatusOrder;

import java.time.LocalDate;

public class Order {
    //Atributos
    private long id;
    private LocalDate CreatedAt;
    private StatusOrder statusOrder;
    private long clientId;

    //Constructores
    public Order() {
    }

    public Order(LocalDate createdAt, StatusOrder statusOrder, long clientId) {
        CreatedAt = createdAt;
        this.statusOrder = statusOrder;
        this.clientId = clientId;
    }

    //Gets & Sets

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        CreatedAt = createdAt;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
}
