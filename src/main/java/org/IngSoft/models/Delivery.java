package org.IngSoft.models;

import org.IngSoft.models.Enum.StatusDelivery;

public class Delivery {
    //Atributos
    private long id;
    private StatusDelivery statusDelivery;
    private long dealerId;
    private long orderId;

    //Constructores

    public Delivery() {
    }

    public Delivery(StatusDelivery statusDelivery, long dealerId, long orderId) {
        this.statusDelivery = statusDelivery;
        this.dealerId = dealerId;
        this.orderId = orderId;
    }

    //Gets & Sets
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StatusDelivery getStatusDelivery() {
        return statusDelivery;
    }

    public void setStatusDelivery(StatusDelivery statusDelivery) {
        this.statusDelivery = statusDelivery;
    }

    public long getDealerId() {
        return dealerId;
    }

    public void setDealerId(long dealerId) {
        this.dealerId = dealerId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
