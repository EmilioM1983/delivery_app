package org.IngSoft.models;

import java.time.LocalDate;

public class Order {
    // Atributos
    private long id;
    private LocalDate createdAt;
    private StatusOrder statusOrder;
    private long clientId;

    // Constructor vacio
    public Order() {
    }

    // Constructor completo
    public Order( long id, LocalDate createdAt, StatusOrder statusOrder, long clientId) {
        this.id = id;
        this.createdAt = createdAt;
        this.statusOrder = statusOrder;
        this.clientId = clientId;
    }

     // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
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

    // Enum para representar los estados del pedido
    public enum StatusOrder {

        Confirmed("Confirmado"),

        Cancel("Cancelado");

        private final String descripcion;

        StatusOrder(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        @Override
        public String toString() {
            return descripcion;
        }
    }
}


