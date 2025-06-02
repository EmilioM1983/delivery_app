package org.IngSoft.ui;

import org.IngSoft.models.OrderItem;
import org.IngSoft.service.OrderService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class OrderUi {
    private OrderService service;
    private Scanner scanner;

    public OrderUi(Connection connection) {
        this.service = new OrderService(connection);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        try {
            System.out.print("Ingrese ID del cliente: ");
            int customerId = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese ID del restaurante: ");
            long restaurantId = Long.parseLong(scanner.nextLine());

            List<OrderItem> items = new ArrayList<>();
            while (true) {
                System.out.print("Ingrese ID del producto (o 0 para finalizar): ");
                int productId = Integer.parseInt(scanner.nextLine());
                if (productId == 0) break;

                System.out.print("Ingrese cantidad: ");
                int quantity = Integer.parseInt(scanner.nextLine());

                OrderItem item = new OrderItem();  // Declarás y creás acá
                item.setProductId(productId);
                item.setQuantity(quantity);

            }

            service.createOrder(customerId, items);
        } catch (SQLException e) {
            System.err.println("Error al crear pedido: " + e.getMessage());
        }
    }
}
