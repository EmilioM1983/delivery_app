package org.IngSoft.repository;

import org.IngSoft.database.Conexion;
import org.IngSoft.models.Enum.StatusDelivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RastreoDao {
    Conexion conexion = new Conexion();
    Connection conn = conexion.establecerConexion();

    public String obtenerEstadoDePedido(long orderId) {
        String sql = "SELECT status FROM delivery WHERE orders_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String statusDb = rs.getString("status");

                for (StatusDelivery status : StatusDelivery.values()) {
                    if (status.name().equalsIgnoreCase(statusDb)) {
                        return status.name();
                    }
                }

                return statusDb;
            } else {
                return "Pedido no encontrado.";
            }

        } catch (Exception e) {
            return "Error al consultar estado del pedido: " + e.getMessage();
        }
    }
}
