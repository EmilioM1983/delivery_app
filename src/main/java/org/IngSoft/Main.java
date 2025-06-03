package org.IngSoft;

import org.IngSoft.service.DeliveryTrackingService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeliveryTrackingService trackingService = new DeliveryTrackingService();

        System.out.print("Ingrese el número de pedido (ordersId): ");
        long orderId = scanner.nextLong();

        String estado = trackingService.obtenerEstadoDePedido(orderId);

        System.out.println("Estado del pedido: " + estado);
    }
}
