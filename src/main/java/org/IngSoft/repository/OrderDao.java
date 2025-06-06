package org.IngSoft.repository;

import org.IngSoft.database.Conexion;
import org.IngSoft.models.Order;

import java.sql.*;

public class OrderDao {

    private final Conexion conexion = new Conexion();

    public long createOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (Client_id, status, created_at) VALUES (?, ?, ?)";

        try (Connection con = conexion.establecerConexion()) {

            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, order.getClientId());
                ps.setString(2, order.getStatusOrder().name());
                ps.setDate(3, java.sql.Date.valueOf(order.getCreatedAt()));

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        long id = rs.getLong(1);

                        return id;
                    } else {
                        throw new SQLException("No se pudo obtener el ID del pedido generado.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar orden: " + e.getMessage());
            throw e;
        }
    }
}
