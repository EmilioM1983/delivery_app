package org.IngSoft.ui;

import org.IngSoft.models.*;
import org.IngSoft.models.Enum.Role;
import org.IngSoft.service.UserService;

import java.util.Scanner;

public class MenuAppUi {
    UserService userservice = new UserService();

    Scanner consolaNum = new Scanner(System.in);
    Scanner consolaText = new Scanner(System.in);

    int opcion, opcionCliente, opcionRest, opcionDealer;
    public void menuPrincipal(){
        do {
            System.out.println("""
                ***Bienvenido a delivery App***
                1. Inicie sesión.
                2. Regístrese.
                3. Salir
                """);
            opcion = consolaNum.nextInt();
            switch (opcion){
                case 1:
                    String user, pass;
                    System.out.println("Ingrese su usuario: ");
                    user = consolaText.next();
                    System.out.println("Ingrese su password: ");
                    pass = consolaText.next();
                    User usuario = userservice.login(user, pass);
                    if (usuario != null) {
                        Role role = usuario.getRole();
                        switch (role) {
                            case Client -> {
                                Client client = userservice.getClientByUser(usuario);
                                SessionData.setSesion(usuario, client, role);
                                menuCliente();
                            }
                            case Restaurant -> {
                                Restaurant restaurant = userservice.getRestaurantByUser(usuario);
                                SessionData.setSesion(usuario, restaurant, role);
                                menuRestaurat();
                            }
                            case Dealer -> {
                                Dealer dealer = userservice.getDealerByUser(usuario);
                                SessionData.setSesion(usuario, dealer, role);
                                menuDealer();
                            }
                            case Admin -> {
                                SessionData.setSesion(usuario, null, role);
                                menuAdmin();
                            }
                        }
                    } else {
                        System.out.println("Usuario o contraseña incorrectos.");
                        break;
                    }

                case 2:
                    RegisterUsUI registerUI = new RegisterUsUI();
                    registerUI.mostrarMenuRegistro();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        }while (opcion!=3);
    }

    //Cliente
    public void menuCliente(){
        OrderUi orderUi = new OrderUi();
        RastreoUi rastreoUi = new RastreoUi();
        do {
            Client cliente = (Client) SessionData.getEntidad();
            System.out.println("Bienvenido querido cliente " + cliente.getName() + "!");
            System.out.println("""
            1. Generar pedido.
            2. Rastrear pedido.
            3. Cerrar sesión.
            """);
            opcionCliente = consolaNum.nextInt();
            switch (opcionCliente){
                case 1:
                    orderUi.iniciar(cliente);
                    break;
                case 2:
                    rastreoUi.iniciar();
                    break;
                case 3:
                    System.out.println("cerrando sesión");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        }while (opcionCliente!=3);
        menuPrincipal();
    }

    //Restaurant
    public void menuRestaurat() {
        do {
            Restaurant restaurant = (Restaurant) SessionData.getEntidad();
            System.out.println("Bienvenido restaurant " + restaurant.getName() + "!");
            System.out.println("""
                        1. Gestión menus.
                        2. Cerrar sesión
                    """);
            opcionRest = consolaNum.nextInt();
            switch (opcionRest) {
                case 1:
                    ProductUi productUi = new ProductUi();
                    productUi.mostrarMenu(restaurant);
                    break;
                case 2:
                    System.out.println("Cerrando su sesión");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opcionRest != 2);
        menuPrincipal();
    }

    //Dealer
    public void menuDealer() {
        do {
            Dealer dealer = (Dealer) SessionData.getEntidad();
            System.out.println("Bienvenido querido repartidor " + dealer.getName() + "!");
            System.out.println("""
                        1. Gestión pedidos.
                        2. Cerrar sesión
                    """);
            opcionDealer = consolaNum.nextInt();
            switch (opcionDealer) {
                case 1:
                    DeliveryUi deliveryUi = new DeliveryUi();
                    deliveryUi.mostrarMenu();
                    break;
                case 2:
                    System.out.println("Cerrando su sesión");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opcionDealer != 2);
        menuPrincipal();
    }

    //Admin
    public void menuAdmin() {
        PaymentUI paymentUI = new PaymentUI();
        paymentUI.menuPagos();
        menuPrincipal();
    }

}
