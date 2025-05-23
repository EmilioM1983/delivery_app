package org.IngSoft.repository;

import org.IngSoft.database.Conexion;
import org.IngSoft.models.OrderItem;
import org.IngSoft.models.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PaymentDAO {

    Conexion conexion = new Conexion();

    public double calcularPrecio(long idProduct) {
        double precio = 0.0;
        PreparedStatement ps;
        ResultSet rs;
        Connection con = conexion.establecerConexion();

        // Consulta SQL que une las tablas order_items y products para calcular el precio total
        String sql = "SELECT oi.quantity, p.price FROM order_items oi " +
                "JOIN products p ON oi.products_id = p.id\n " +
                "WHERE oi.id = ?";


        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, idProduct);
            rs = ps.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                double productPrice = rs.getDouble("price");
                precio = quantity * productPrice;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al calcular el precio: " + e);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar la conexión: " + e.getMessage());
            }
        }

        return precio;
    }

    public List<OrderItem> getOrderItem() {
        List<OrderItem> listOrderItem = new ArrayList<>();
        PreparedStatement ps; //Prepara la sentencia sql
        ResultSet rs; //Guarda el resultado de la consulta sql
        Connection con = conexion.establecerConexion(); //Define una variable que establece la conexion a la BD
        String sql = "SELECT * FROM order_items ORDER BY id"; // Definimos la consulta sql
        try {
            ps = con.prepareStatement(sql); //Prepara la consulta sql
            rs = ps.executeQuery(); //Ejecuta la consulta sql y lo guarda en rs
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getLong("id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setOrderId(rs.getLong("orders_id"));
                orderItem.setProductId(rs.getLong("products_id"));

                listOrderItem.add(orderItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ocurrió un error al consultar datos: " + e);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar la conexión: " + e.getMessage());
            }
        }

        return listOrderItem;
    }

    public boolean createPayment(Payment payment) {
        PreparedStatement ps;
        Connection con = conexion.establecerConexion();
        String sql = "INSERT INTO payments (id, total_price, payment_method, status, order_items_id) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, payment.getId());
            ps.setDouble(2, payment.getTotalPrice());
            ps.setString(3, payment.getPaymentMethod().toString());
            ps.setString(4, payment.getStatusPayment().toString());
            ps.setLong(5, payment.getOrderId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el pago: " + e);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
