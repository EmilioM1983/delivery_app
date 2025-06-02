package org.IngSoft.service;

import org.IngSoft.models.Order;
import org.IngSoft.models.OrderItem;
import org.IngSoft.repository.OrderDao;
import org.IngSoft.repository.OrderItemDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderService {
    private OrderDao orderDao;
    private OrderItemDao itemDao;

    public OrderService(Connection connection) {
        this.orderDao = new OrderDao(connection);
        this.itemDao = new OrderItemDao(connection);
    }

    public void createOrder(int customerId, List<OrderItem> items) throws SQLException {
        Order order = new Order();
        order.setClientId(customerId);
        order.setCreatedAt(java.time.LocalDate.now());
        order.setStatusOrder(Order.StatusOrder.Confirmed);

        long orderId = orderDao.createOrder(order);

        for (OrderItem item : items) {
            item.setOrderId(orderId);
            itemDao.createOrderItem(item);
        }

        System.out.println("Pedido creado con éxito. ID: " + orderId);
    }

}
