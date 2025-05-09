package org.IngSoft.models;

public class OrderItem {
    //Atributos
    private long id;
    private int quantity;
    private long productId;
    private long orderId;

    //Constructores
    public OrderItem() {
    }

    public OrderItem(int quantity, long productId, long orderId) {
        this.quantity = quantity;
        this.productId = productId;
        this.orderId = orderId;
    }

    //Gets & Sets
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
