package org.IngSoft.repository;

import org.IngSoft.models.OrderItem;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {

    private Connection connection;

    public OrderItemDao(Connection connection) {
        this.connection = connection;
    }

    // Crear un nuevo ítem de pedido, ahora con el id del restaurante
    public long createOrderItem(@NotNull OrderItem orderItem) throws SQLException {

        String sql = "INSERT INTO order_items (orders_id, products_id, quantity, products_Restaurants_idRestaurants) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, orderItem.getOrderId());
            statement.setLong(2, orderItem.getProductId());
            statement.setInt(3, orderItem.getQuantity());
            statement.setLong(4, orderItem.getProductsRestaurantsIdRestaurants());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("No se pudo obtener el ID del ítem de pedido generado.");
            }
        }
    }

    // Obtener ítems de pedido por ID de pedido
    public List<OrderItem> getOrderItemsByOrderId(long orderId) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();

        String sql = "SELECT * FROM order_items WHERE orders_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(resultSet.getLong("id"));
                orderItem.setOrderId(resultSet.getLong("orders_id"));
                orderItem.setProductId(resultSet.getLong("products_id"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItem.setProductsRestaurantsIdRestaurants(resultSet.getLong("products_Restaurants_idRestaurants"));
                orderItems.add(orderItem);
            }
            return orderItems;
        }
    }

    // Actualizar la cantidad de un ítem de pedido
    public void updateOrderItemQuantity(long id, int quantity) throws SQLException {
        String sql = "UPDATE order_items SET quantity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
    }

    // Eliminar un ítem de pedido
    public void deleteOrderItem(long id) throws SQLException {
        String sql = "DELETE FROM order_items WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
