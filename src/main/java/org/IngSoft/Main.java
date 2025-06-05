package org.IngSoft;

import org.IngSoft.database.Conexion;
import org.IngSoft.ui.DeliveryUi;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

       //Conexion conexion = new Conexion();
       //conexion.establecerConexion();


        DeliveryUi ui = new DeliveryUi();
        ui.mostrarMenu();

        //conexion.cerrarConexion();
        }
    }
