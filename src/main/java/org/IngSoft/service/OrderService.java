package org.IngSoft.service;

import org.IngSoft.models.Order;
import org.IngSoft.models.OrderItem;
import org.IngSoft.repository.OrderDao;
import org.IngSoft.repository.OrderItemDao;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    OrderDao orderDao = new OrderDao();
    OrderItemDao itemDao = new OrderItemDao();

    public void createOrder(int customerId, List<OrderItem> items) {
        try {

            Order order = new Order();
            order.setClientId(customerId);
            order.setCreatedAt(java.time.LocalDate.now());
            order.setStatusOrder(Order.StatusOrder.Confirmed);

            long orderId = orderDao.createOrder(order);

            for (OrderItem item : items) {
                item.setOrderId(orderId);
                System.out.println("→ Agregando item - Producto ID: " + item.getProductId() + ", Cantidad: " + item.getQuantity());
                itemDao.createOrderItem(item);
            }

            System.out.println("✔ Pedido registrado correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al registrar el pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }
}