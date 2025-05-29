package org.IngSoft.repository;

import org.IngSoft.database.Conexion;
import org.IngSoft.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    private Conexion conexion = new Conexion();

    public List<Client> getAllclients() {
        List<Client> listClients = new ArrayList<>();
        String sql = "SELECT * FROM Client ORDER BY id";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                client.setLastName(rs.getString("last_name"));
                client.setPhone(rs.getString("phone"));
                client.setAdress(rs.getString("adress"));
                client.setUserId(rs.getLong("users_id"));
                listClients.add(client);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ocurrió un error al consultar datos: " + e.getMessage(), e);
        }

        return listClients;
    }

    public boolean findById(Client client) {
        String sql = "SELECT * FROM Client WHERE id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, client.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    client.setName(rs.getString("name"));
                    client.setLastName(rs.getString("last_name"));
                    client.setPhone(rs.getString("phone"));  // Corregido
                    client.setAdress(rs.getString("adress"));
                    client.setUserId(rs.getLong("users_id"));
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al buscar el cliente: " + e.getMessage());
        }

        return false;
    }

    public boolean save(Client client) {
        String sql = "INSERT INTO Client (name, last_name, phone, adress, users_id) VALUES (?, ?, ?, ?, ?)";  // Corregido

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, client.getName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getPhone());
            ps.setString(4, client.getAdress());
            ps.setLong(5, client.getUserId());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al registrar el cliente: " + e.getMessage());
        }

        return false;
    }

    public boolean update(Client client) {
        String sql = "UPDATE Client SET name = ?, last_name = ?, phone = ?, adress = ?, users_id = ? WHERE id = ?";  // Corregido

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, client.getName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getPhone());
            ps.setString(4, client.getAdress());
            ps.setLong(5, client.getUserId());
            ps.setLong(6, client.getId());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al modificar el cliente: " + e.getMessage());
        }

        return false;
    }

    public boolean delete(Client client) {  // Corregido parámetro
        String sql = "DELETE FROM Client WHERE id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, client.getId());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al eliminar el cliente: " + e.getMessage());
        }

        return false;
    }

    //Buscar por userId
    public Client findClientIdByUserId(Long userId) {
        String sql = "SELECT * FROM Client WHERE users_id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getLong("id"));
                    client.setName(rs.getString("name"));
                    client.setLastName(rs.getString("last_name"));
                    client.setPhone(rs.getString("phone"));
                    client.setAdress(rs.getString("adress"));
                    client.setUserId(rs.getLong("users_id"));
                    return client;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener Client por users_id: " + e.getMessage());
        }

        return null;
    }

}

