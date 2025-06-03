package org.IngSoft;

import org.IngSoft.ui.ProductUi;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Crear la instancia de ProductUi
        ProductUi productUi = new ProductUi();


        // Crear un producto y asignarlo a un restaurant
        System.out.println("Creando un producto de ejemplo para el restaurante...");
        productUi.crearProductoConDatos("Pizza Margarita", 12.99, 1);

        // Mostrar el menú de la aplicación para gestionar productos
        productUi.mostrarMenu();
    }
}
