package org.IngSoft.repository;

import org.IngSoft.database.Conexion;
import org.IngSoft.models.Client;
import org.IngSoft.models.Dealer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealerDao {
    private Conexion conexion = new Conexion();

    public List<Dealer> getAllDealers() {
        List<Dealer> listDealer = new ArrayList<>();
        String sql = "SELECT * FROM dealer ORDER BY id";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Dealer dealer = new Dealer();
                dealer.setId(rs.getLong("id"));
                dealer.setName(rs.getString("name"));
                dealer.setLastName(rs.getString("last_name"));
                dealer.setPhone(rs.getString("phone"));
                dealer.setUserId(rs.getLong("users_id"));

                listDealer.add(dealer);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ocurrió un error al consultar datos: " + e.getMessage(), e);
        }

        return listDealer;
    }

    public boolean findById(Dealer dealer) {
        String sql = "SELECT * FROM dealer WHERE id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, dealer.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dealer.setName(rs.getString("name"));
                    dealer.setLastName(rs.getString("last_name"));
                    dealer.setPhone(rs.getString("phone"));
                    dealer.setUserId(rs.getLong("users_id"));
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al buscar el dealer: " + e.getMessage());
        }

        return false;
    }

    public boolean save(Dealer dealer) {
        String sql = "INSERT INTO dealer (name, last_name, phone, users_id) VALUES (?, ?, ?, ?)";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dealer.getName());
            ps.setString(2, dealer.getLastName());
            ps.setString(3, dealer.getPhone());
            ps.setLong(4, dealer.getUserId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Ocurrió un error al registrar el dealer: " + e.getMessage());
            return false;
        }
    }



    public boolean update(Dealer dealer) {
        String sql = "UPDATE dealer SET name = ?, last_name = ?, phone = ?, users_id = ? WHERE id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dealer.getName());
            ps.setString(2, dealer.getLastName());
            ps.setString(3, dealer.getPhone());
            ps.setLong(4, dealer.getUserId());
            ps.setLong(5, dealer.getId());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al modificar el dealer: " + e.getMessage());
        }

        return false;
    }


    public boolean delete(Dealer dealer) {  // Corregido parámetro
        String sql = "DELETE FROM dealer WHERE id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, dealer.getId());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al eliminar el dealer: " + e.getMessage());
        }

        return false;
    }

    //Buscar por users_id
    public Dealer findDealerIdByUserId(Long userId) {
        String sql = "SELECT * FROM dealer WHERE users_id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Dealer dealer = new Dealer();
                    dealer.setId(rs.getLong("id"));
                    dealer.setName(rs.getString("name"));
                    dealer.setLastName(rs.getString("last_name"));
                    dealer.setPhone(rs.getString("phone"));
                    dealer.setUserId(rs.getLong("users_id"));
                    return dealer;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener repartidor por users_id: " + e.getMessage());
        }

        return null;
    }

}
