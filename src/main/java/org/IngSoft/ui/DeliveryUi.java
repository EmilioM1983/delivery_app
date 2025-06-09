package org.IngSoft.ui;

import org.IngSoft.models.Delivery;
import org.IngSoft.models.Enum.StatusDelivery;
import org.IngSoft.service.DeliveryService;

import java.util.List;
import java.util.Scanner;

public class DeliveryUi {
    private final DeliveryService deliveryService;
    private final Scanner scanner;

    public DeliveryUi() {
        this.deliveryService = new DeliveryService();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ DE DELIVERY ---");
            System.out.println("1. Asignar pedido a repartidor");
            System.out.println("2. Listar todos los deliveries");
            System.out.println("3. Cambiar estado de un delivery");
            System.out.println("4. Eliminar un delivery");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> asignarPedido();
                case 2 -> listarDeliveries();
                case 3 -> cambiarEstado();
                case 4 -> eliminarDelivery();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void asignarPedido() {
        System.out.print("Ingrese ID del pedido: ");
        long orderId = scanner.nextLong();
        System.out.print("Ingrese ID del repartidor: ");
        long dealerId = scanner.nextLong();
        deliveryService.asignarPedidoARepartidor(orderId, dealerId);
    }

    private void listarDeliveries() {
        List<Delivery> lista = deliveryService.listarDeliveries();
        if (lista.isEmpty()) {
            System.out.println("No hay deliveries registrados.");
        } else {
            System.out.println("\n--- LISTADO DE DELIVERIES ---");
            for (Delivery d : lista) {
                System.out.println("ID: " + d.getId() +
                        " | Pedido: " + d.getOrderId() +
                        " | Repartidor: " + d.getDealerId() +
                        " | Estado: " + d.getStatusDelivery());
            }
        }
    }

    private void cambiarEstado() {
        System.out.print("Ingrese ID del delivery: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // limpiar buffer

        Delivery d = deliveryService.obtenerDelivery(id);
        if (d == null) {
            System.out.println("❌ No se encontró el delivery.");
            return;
        }

        System.out.println("Seleccione el nuevo estado:");
        int opcion = 0;
        StatusDelivery nuevoStatus = null;

        // Mostrar las opciones de estado
        StatusDelivery[] estados = StatusDelivery.values();
        for (int i = 0; i < estados.length; i++) {
            System.out.println((i + 1) + ". " + estados[i].name());
        }

        System.out.print("Ingrese el número de la opción: ");
        if (scanner.hasNextInt()) {
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            if (opcion >= 1 && opcion <= estados.length) {
                nuevoStatus = estados[opcion - 1];
                d.setStatusDelivery(nuevoStatus);
                deliveryService.actualizarDelivery(d);
                System.out.println("✔ Estado actualizado a: " + nuevoStatus.name());
            } else {
                System.out.println("❌ Opción inválida.");
            }
        } else {
            System.out.println("❌ Entrada inválida. Debe ingresar un número.");
            scanner.nextLine(); // limpiar buffer
        }
    }

    private void eliminarDelivery() {
        System.out.print("Ingrese ID del delivery a eliminar: ");
        long id = scanner.nextLong();
        deliveryService.eliminarDelivery(id);
        System.out.println("✔ Delivery eliminado.");
    }
}