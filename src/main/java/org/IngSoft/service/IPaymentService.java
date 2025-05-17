package org.IngSoft.service;

import org.IngSoft.models.OrderItem;
import org.IngSoft.models.Payment;
import org.IngSoft.models.Product;

import java.util.List;

public interface IPaymentService {
    double calcularPrecioTotal(long idProduct);
    List<OrderItem> getAllOrdersItems();
    boolean paymentSave(Payment payment);
}
