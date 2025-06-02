package org.IngSoft.repository;

import org.IngSoft.models.Order;
import org.IngSoft.models.Order.StatusOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    // Crear un nuevo pedido
    public long createOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (Client_id, status, created_at) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getClientId());
            statement.setString(2, order.getStatusOrder().name().toLowerCase());
            statement.setDate(3, java.sql.Date.valueOf(order.getCreatedAt()));
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("No se pudo obtener el ID del pedido generado.");
            }
        }
    }

    // Obtener un pedido por su ID
    public Order getOrderById(long id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setClientId(resultSet.getLong("Client_id"));
                order.setStatusOrder(StatusOrder.valueOf(resultSet.getString("status").toUpperCase()));
                order.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
                return order;
            }
            return null;
        }
    }

    // Actualizar el estado de un pedido
    public void updateOrderStatus(long id, StatusOrder status) throws SQLException {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status.name().toLowerCase());
            statement.setLong(2, id);
            statement.executeUpdate();
        }
    }

    // Obtener todos los pedidos de un cliente
    public List<Order> getOrdersByClientId(long clientId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE Client_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, clientId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setClientId(resultSet.getLong("Client_id"));
                order.setStatusOrder(StatusOrder.valueOf(resultSet.getString("status")));
                order.setCreatedAt(resultSet.getDate("created_at").toLocalDate());
                orders.add(order);
            }
            return orders;
        }
    }

    // Eliminar un pedido
    public void deleteOrder(long id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
