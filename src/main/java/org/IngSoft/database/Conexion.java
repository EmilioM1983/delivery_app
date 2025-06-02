package org.IngSoft.database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
        Connection conectar = null;

        //Credenciales
        String usuario = "root";
        String contrasenia = "emi1";
        String bd = "db_delivery";
        String ip = "localhost";
        String puerto = "3306";

        //cadena de conexion
        String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;

        public Connection establecerConexion(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
                //JOptionPane.showMessageDialog(null, "Conexión correcta");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se realizo la conexión "+e.toString());
            }
            return conectar;
        }

        public void cerrarConexion(){
            try {
                if (conectar!=null && !conectar.isClosed()) {
                    conectar.close();
                    //JOptionPane.showMessageDialog(null, "Conexión cerrada");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se ha cerrado la conexión"+e.toString());
            }
        }
    }
