package org.IngSoft.service;

import org.IngSoft.models.OrderItem;
import org.IngSoft.models.Payment;
import org.IngSoft.repository.PaymentDAO;

import java.util.List;

public class PaymentService implements IPaymentService{

    PaymentDAO paymentDAO = new PaymentDAO();

    @Override
    public double calcularPrecioTotal(long idProduct) {
        return paymentDAO.calcularPrecio(idProduct);
    }

    @Override
    public List<OrderItem> getAllOrdersItems() {
        return paymentDAO.getOrderItem();
    }

    @Override
    public boolean paymentSave(Payment payment) {


        return paymentDAO.createPayment(payment);
    }
}
