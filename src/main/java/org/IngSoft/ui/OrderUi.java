package org.IngSoft.ui;

import org.IngSoft.models.OrderItem;
import org.IngSoft.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderUi {

    OrderService service = new OrderService();
    Scanner scanner = new Scanner(System.in);
    List<OrderItem> listItems = new ArrayList<>();

    public void iniciar() {
        try {
            System.out.print("Ingrese ID del cliente: ");
            int customerId = Integer.parseInt(scanner.nextLine());

            boolean opcion = true;
            while (opcion) {
                System.out.print("Ingrese ID del producto (o 0 para finalizar): ");
                int productId = Integer.parseInt(scanner.nextLine());

                if (productId == 0) {
                    opcion = false;
                } else {
                    System.out.print("Ingrese cantidad: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    OrderItem item = new OrderItem();
                    item.setProductId(productId);
                    item.setQuantity(quantity);
                    listItems.add(item);
                }
            }

            service.createOrder(customerId, listItems);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}