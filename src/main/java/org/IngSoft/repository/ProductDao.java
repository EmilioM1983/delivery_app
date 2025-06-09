package org.IngSoft.repository;

import org.IngSoft.database.Conexion;
import org.IngSoft.models.Product;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private final Conexion conexion;

    public ProductDao() {
        this.conexion = new Conexion();
    }


    public long create(Product product) {
        long idGenerado = -1;
        try {
            Connection conn = conexion.establecerConexion();
            String sql = "INSERT INTO products (name, price, restaurants_id) VALUES (?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setLong(3, product.getRestaurantId());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    idGenerado = rs.getLong(1);
                    product.setId(idGenerado);
                }
                rs.close();
            }

            pstmt.close();
            conexion.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el producto: " + e.toString());
        }

        return idGenerado;
    }


    public Product findById(long id) {
        Product product = null;
        try {
            Connection conn = conexion.establecerConexion();
            String sql = "SELECT * FROM products WHERE id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setRestaurantId(rs.getLong("restaurants_id"));
            }

            rs.close();
            pstmt.close();
            conexion.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el producto: " + e.toString());
        }

        return product;
    }


    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection conn = conexion.establecerConexion();
            String sql = "SELECT * FROM products";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setRestaurantId(rs.getLong("restaurants_id"));
                products.add(product);
            }

            rs.close();
            stmt.close();
            conexion.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los productos: " + e.toString());
        }

        return products;
    }


    public List<Product> findByRestaurant(long restaurantId) {
        List<Product> products = new ArrayList<>();
        try {
            Connection conn = conexion.establecerConexion();
            String sql = "SELECT * FROM products WHERE restaurants_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, restaurantId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setRestaurantId(rs.getLong("restaurants_id"));
                products.add(product);
            }

            rs.close();
            pstmt.close();
            conexion.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los productos del restaurante: " + e.toString());
        }

        return products;
    }


    public boolean update(Product product) {
        boolean actualizado = false;
        try {
            Connection conn = conexion.establecerConexion();
            String sql = "UPDATE products SET name = ?, price = ?, restaurants_id = ? WHERE id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setLong(3, product.getRestaurantId());
            pstmt.setLong(4, product.getId());

            int filasAfectadas = pstmt.executeUpdate();
            actualizado = filasAfectadas > 0;

            pstmt.close();
            conexion.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto: " + e.toString());
        }

        return actualizado;
    }


    public boolean delete(long id) {
        boolean eliminado = false;
        try {
            Connection conn = conexion.establecerConexion();
            String sql = "DELETE FROM products WHERE id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            int filasAfectadas = pstmt.executeUpdate();
            eliminado = filasAfectadas > 0;

            pstmt.close();
            conexion.cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.toString());
        }

        return eliminado;
    }
}
