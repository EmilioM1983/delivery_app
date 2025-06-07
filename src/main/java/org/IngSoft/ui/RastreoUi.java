package org.IngSoft.ui;

import org.IngSoft.service.DeliveryTrackingService;

import java.util.Scanner;

public class RastreoUi {
    Scanner scanner = new Scanner(System.in);
    DeliveryTrackingService trackingService = new DeliveryTrackingService();
    long orderId;

    public void iniciar(){
        System.out.print("Ingrese el número de pedido (ordersId): ");
        orderId = scanner.nextLong();

        String estado = trackingService.obtenerEstadoDePedido(orderId);

        System.out.println("Estado del pedido: " + estado);
    }
}
