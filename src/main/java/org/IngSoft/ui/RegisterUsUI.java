package org.IngSoft.ui;

import org.IngSoft.models.Client;
import org.IngSoft.models.Dealer;
import org.IngSoft.models.Enum.Role;
import org.IngSoft.models.Restaurant;
import org.IngSoft.models.User;
import org.IngSoft.service.UserService;

import java.util.Scanner;

public class RegisterUsUI {
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();

    public void mostrarMenuRegistro() {
        System.out.println("==== REGISTRO DE USUARIO ====");

        System.out.print("Ingrese nombre de usuario: ");
        String username = scanner.nextLine();

        // Validación: usuario existente
        if (userService.existeUsuario(username)) {
            System.out.println("❌ El usuario ya existe. Intente con otro nombre.");
            return;
        }

        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();

        System.out.println("Seleccione el tipo de usuario:");
        System.out.println("1. Cliente");
        System.out.println("2. Restaurante");
        System.out.println("3. Repartidor");
        System.out.print("Opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());

        Role rol = null;

        switch (opcion) {
            case 1:
                rol = Role.Client;
                break;
            case 2:
                rol = Role.Restaurant;
                break;
            case 3:
                rol = Role.Dealer;
                break;
            default:
                System.out.println("❌ Opción inválida.");
                return;
        }


        User nuevoUsuario = new User(username, password, rol);

        // Registrar según el tipo
        boolean registrado = switch (rol) {
            case Client -> {
                System.out.print("Ingrese su nombre: ");
                String name = scanner.nextLine();
                System.out.println("Ingrese su apellido: ");
                String lastname= scanner.nextLine();
                System.out.println("Ingrese su numero de teléfono: ");
                String phone = scanner.nextLine();
                System.out.print("Ingrese dirección: ");
                String address = scanner.nextLine();
                Client cliente = new Client();
                cliente.setName(name);
                cliente.setLastName(lastname);
                cliente.setPhone(phone);
                cliente.setAdress(address);
                yield userService.registrarCliente(nuevoUsuario, cliente);
            }
            case Restaurant -> {
                System.out.print("Ingrese nombre del restaurante o su nombre: ");
                String name = scanner.nextLine();
                System.out.print("Ingrese dirección: ");
                String address = scanner.nextLine();

                Restaurant restaurant = new Restaurant();
                restaurant.setName(name);
                restaurant.setAddres(address);

                yield userService.registrarrestaurant(nuevoUsuario, restaurant);
            }

            case Dealer -> {
                System.out.print("Ingrese nombre: ");
                String name = scanner.nextLine();
                System.out.print("Ingrese apellido: ");
                String lastName = scanner.nextLine();
                System.out.print("Ingrese teléfono: ");
                String phone = scanner.nextLine();

                Dealer dealer = new Dealer();
                dealer.setName(name);
                dealer.setLastName(lastName);
                dealer.setPhone(phone);
                // El user aún no tiene ID hasta que se guarda, pero asumimos que el UserDao lo resuelve internamente

                yield userService.registrarDealer(nuevoUsuario, dealer);
            }
            default -> throw new IllegalStateException("Rol inesperado: " + rol);

        };

        if (registrado) {
            System.out.println("✅ Usuario registrado correctamente.");
        } else {
            System.out.println("❌ Ocurrió un error al registrar el usuario.");
        }
    }

    private boolean usuarioExiste(String username) {
        return userService.login(username, "noImporta") != null;
    }
}