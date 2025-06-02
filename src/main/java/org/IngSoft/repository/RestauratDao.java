package org.IngSoft.repository;

import org.IngSoft.database.Conexion;
import org.IngSoft.models.Client;
import org.IngSoft.models.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestauratDao {
    private Conexion conexion = new Conexion();

    public List<Restaurant> getAllRestaurant() {
        List<Restaurant> listRestaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurants ORDER BY id";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getLong("id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setAddres(rs.getString("adress"));
                restaurant.setUserId(rs.getLong("users_id"));
                listRestaurants.add(restaurant);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ocurrió un error al consultar datos: " + e.getMessage(), e);
        }

        return listRestaurants;
    }

    public boolean findById(Restaurant restaurant) {
        String sql = "SELECT * FROM restaurants WHERE id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, restaurant.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    restaurant.setName(rs.getString("name"));
                    restaurant.setAddres(rs.getString("adress"));
                    restaurant.setUserId(rs.getLong("users_id"));
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al buscar el restaurant: " + e.getMessage());
        }

        return false;
    }


    public boolean save(Restaurant restaurant) {
        String sql = "INSERT INTO restaurants (name, adress, users_id) VALUES (?, ?, ?)";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Validación previa: el usuario debe estar asignado
            if (restaurant.getUserId() <= 0) {
                System.out.println("❌ Error: el ID del usuario no está asignado al restaurante.");
                return false;
            }

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddres());
            ps.setLong(3, restaurant.getUserId());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("❌ No se pudo insertar el restaurante.");
                return false;
            }

            // Capturar el ID generado si lo necesitás después
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    restaurant.setId(generatedKeys.getLong(1));
                }
            }

            return true;

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al registrar el restaurante: " + e.getMessage());
        }

        return false;
    }


    public boolean update(Restaurant restaurant) {
        String sql = "UPDATE restaurants SET name = ?, adress = ?, users_id = ? WHERE id = ?";  // Corregido

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddres());
            ps.setLong(3, restaurant.getUserId());
            ps.setLong(4, restaurant.getId());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al modificar el restaurant: " + e.getMessage());
        }

        return false;
    }

    public boolean delete(Restaurant restaurant) {  // Corregido parámetro
        String sql = "DELETE FROM restaurants WHERE id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, restaurant.getId());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al eliminar el restaurant: " + e.getMessage());
        }

        return false;
    }

    //Buscar por users_id
    public Restaurant findRestaurantIdByUserId(Long userId) {
        String sql = "SELECT * FROM restaurants WHERE users_id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Restaurant restaurant = new Restaurant();
                    restaurant.setId(rs.getLong("id"));
                    restaurant.setName(rs.getString("name"));
                    restaurant.setAddres(rs.getString("adress"));
                    restaurant.setUserId(rs.getLong("users_id"));
                    return restaurant;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener Restaurant por users_id: " + e.getMessage());
        }

        return null;
    }

}
