package org.IngSoft.repository;

import org.IngSoft.database.Conexion;
import org.IngSoft.models.Enum.Role;
import org.IngSoft.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Conexion conexion = new Conexion();

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUserName(rs.getString("username")); // Asegúrate de que el campo se llama "username"
                user.setRole(Role.valueOf(rs.getString("rol")));
                user.setPassword(rs.getString("password"));
                listUser.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ocurrió un error al consultar datos: " + e.getMessage(), e);
        }

        return listUser;
    }

    public boolean findById(User user) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, user.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user.setUserName(rs.getString("username"));
                    user.setRole(Role.valueOf(rs.getString("rol")));
                    user.setPassword(rs.getString("password"));
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al buscar el usuario: " + e.getMessage());
        }

        return false;
    }
/*  esta es la clase qeu estaba antes
    public boolean save(User user) {
        String sql = "INSERT INTO users (username, password, rol) VALUES (?, ?, ?)";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, String.valueOf(user.getRole()));
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al registrar el usuario: " + e.getMessage());
        }

        return false;
    }
*/
public boolean save(User user) {
    String sql = "INSERT INTO users (username, password, rol) VALUES (?, ?, ?)";

    try (Connection con = conexion.establecerConexion();
         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ps.setString(3, String.valueOf(user.getRole()));

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("No se pudo insertar el usuario.");
        }

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                user.setId(id);  // Actualiza el objeto user con el ID generado
            } else {
                throw new SQLException("No se obtuvo el ID generado.");
            }
        }

        return true;

    } catch (SQLException e) {
        System.out.println("Ocurrió un error al registrar el usuario: " + e.getMessage());
    }

    return false;
}



    public boolean update(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, rol = ? WHERE id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, String.valueOf(user.getRole()));
            ps.setLong(4, user.getId());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al modificar el usuario: " + e.getMessage());
        }

        return false;
    }

    public boolean delete(User user) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, user.getId());
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Ocurrió un error al eliminar el usuario: " + e.getMessage());
        }

        return false;
    }
/*
    public static void main(String[] args) {
        UserDao usu = new UserDao();
        List<User> users = usu.getAllUsers();
        users.forEach(System.out::println);
    }*/

}
