package org.IngSoft.repository;

import org.IngSoft.database.Conexion;
import org.IngSoft.models.Delivery;
import org.IngSoft.models.Enum.StatusDelivery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DeliveryDao {
    private Conexion conexion;

    public DeliveryDao() {
        this.conexion = new Conexion();
    }

    private Connection conectar() throws SQLException {
        return conexion.establecerConexion();
    }

    public void guardar(Delivery delivery) {
        String sql = "INSERT INTO delivery (status, dealer_id, orders_id) VALUES (?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, delivery.getStatusDelivery().name());
            stmt.setLong(2, delivery.getDealerId());
            stmt.setLong(3, delivery.getOrderId()); // Usamos getOrderId() de Delivery
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Delivery buscarPorId(long id) {
        String sql = "SELECT * FROM delivery WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Delivery d = new Delivery();
                d.setId(rs.getLong("id"));
                d.setStatusDelivery(StatusDelivery.fromString(rs.getString("status")));
                d.setDealerId(rs.getLong("dealer_id"));
                d.setOrderId(rs.getLong("orders_id")); // Leemos orders_id
                return d;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Delivery> obtenerTodos() {
        List<Delivery> lista = new ArrayList<>();
        String sql = "SELECT * FROM delivery";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Delivery d = new Delivery();
                d.setId(rs.getLong("id"));
                d.setStatusDelivery(StatusDelivery.fromString(rs.getString("status")));
                d.setDealerId(rs.getLong("dealer_id"));
                d.setOrderId(rs.getLong("orders_id")); // Leemos orders_id
                lista.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Delivery delivery) {
        String sql = "UPDATE delivery SET status = ?, dealer_id = ?, orders_id = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, delivery.getStatusDelivery().name());
            stmt.setLong(2, delivery.getDealerId());
            stmt.setLong(3, delivery.getOrderId()); // Usamos getOrderId() de Delivery
            stmt.setLong(4, delivery.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(long id) {
        String sql = "DELETE FROM delivery WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}