package org.IngSoft.ui;

import org.IngSoft.models.*;
import org.IngSoft.service.DeliveryService;
import org.IngSoft.service.OrderService;
import org.IngSoft.service.ProductService;
import org.IngSoft.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderUi {
    UserService userService = new UserService();
    List<Dealer> dealerList = new ArrayList<>();
    DeliveryService deliveryService = new DeliveryService();
    OrderService service = new OrderService();
    List<Product> listProduct = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    List<OrderItem> listItems = new ArrayList<>();
    ProductService productService = new ProductService();
    public void iniciar(Client client) {
        try {
            long customerId = client.getId();
            long dealerId;
            boolean opcion = true;
            while (opcion) {
                System.out.println("Ingrese ID del producto (o 0 para finalizar): ");
                listProduct = productService.getAllProducts();
                for(Product p : listProduct){
                    System.out.println(p.getId()+" "+p.getName() + " " + p.getPrice());
                }
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
            dealerList = userService.listarRepartidores();
            System.out.println("Seleccione un repartidor");
            for (Dealer d : dealerList){
                System.out.println(d.getId() + " " + d.getName() + " " + d.getLastName());
            }
            dealerId = scanner.nextLong();

            long orderId= service.createOrder((int) customerId, listItems);
            deliveryService.asignarPedidoARepartidor(orderId, dealerId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}