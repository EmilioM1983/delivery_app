package org.IngSoft;
import org.IngSoft.ui.OrderUi;

import org.IngSoft.database.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = null; // Declarar la conexión fuera del try-catch
        Conexion conexion = new Conexion();

        try {
            conexion.establecerConexion(); // Establecer la conexión primero
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_delivery", "root", "Basededatos2023");

            OrderUi ui = new OrderUi(connection);
            ui.iniciar();

        } catch (SQLException e) { // Capturar SQLException (más específico)
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close(); // Cerrar la conexión en el finally
                }
                conexion.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


