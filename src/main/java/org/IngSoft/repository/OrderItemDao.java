package org.IngSoft.repository;

import org.IngSoft.database.Conexion;
import org.IngSoft.models.OrderItem;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {

    Conexion conexion = new Conexion();

    // Crear un nuevo ítem de pedido
    public boolean createOrderItem( OrderItem orderItem) {
        String sql = "INSERT INTO order_items (orders_id, products_id, quantity) VALUES (?, ?, ?)";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, orderItem.getOrderId());
            ps.setLong(2, orderItem.getProductId());
            ps.setInt(3, orderItem.getQuantity());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Error: " + e);
        }
    }
}
