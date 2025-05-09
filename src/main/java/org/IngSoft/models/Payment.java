package org.IngSoft.models;

import org.IngSoft.models.Enum.PaymentMethod;
import org.IngSoft.models.Enum.StatusPayment;

public class Payment {
    //Atributos
    private long id;
    private double totalPrice;
    private PaymentMethod paymentMethod;
    private StatusPayment statusPayment;
    private long orderItemId;

    //Constructores
    public Payment() {
    }

    public Payment(double totalPrice, PaymentMethod paymentMethod, StatusPayment statusPayment, long orderId) {
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.statusPayment = statusPayment;
        this.orderItemId = orderId;
    }

    //Gets & Sets
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public StatusPayment getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;
    }

    public long getOrderId() {
        return orderItemId;
    }

    public void setOrderId(long orderId) {
        this.orderItemId = orderId;
    }
}
