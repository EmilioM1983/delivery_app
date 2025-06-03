package org.IngSoft.ui;

import org.IngSoft.models.Product;
import org.IngSoft.service.ProductService;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class ProductUi {
    // Atributos
    private final ProductService productService;
    private final Scanner scanner;

    // Constructor
    public ProductUi() {
        this.productService = new ProductService();
        this.scanner = new Scanner(System.in);
    }


    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== GESTIÓN DE PRODUCTOS =====");
            System.out.println("1. Crear nuevo producto");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Listar todos los productos");
            System.out.println("4. Listar productos por restaurante");
            System.out.println("5. Actualizar producto");
            System.out.println("6. Eliminar producto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    crearProducto();
                    break;
                case 2:
                    buscarProductoPorId();
                    break;
                case 3:
                    listarTodosLosProductos();
                    break;
                case 4:
                    listarProductosPorRestaurante();
                    break;
                case 5:
                    actualizarProducto();
                    break;
                case 6:
                    eliminarProducto();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }


    public Product crearProducto() {
        System.out.println("\n--- Crear Nuevo Producto ---");

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio del producto: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("ID del restaurante: ");
        long restauranteId = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer

        Product producto = productService.createProduct(nombre, precio, restauranteId);

        if (producto != null) {
            System.out.println("Producto creado exitosamente con ID: " + producto.getId());
            return producto;
        } else {
            System.out.println("Error al crear el producto.");
            return null;
        }
    }


    public Product crearProductoConDatos(String nombre, double precio, long restauranteId) {
        Product producto = productService.createProduct(nombre, precio, restauranteId);

        if (producto != null) {
            System.out.println("Producto creado exitosamente con ID: " + producto.getId());
            return producto;
        } else {
            System.out.println("Error al crear el producto.");
            return null;
        }
    }

    private void buscarProductoPorId() {
        System.out.println("\n--- Buscar Producto por ID ---");

        System.out.print("Ingrese el ID del producto: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer

        Product producto = productService.getProductById(id);

        if (producto != null) {
            mostrarDetallesProducto(producto);
        } else {
            System.out.println("No se encontró ningún producto con el ID: " + id);
        }
    }

    private void listarTodosLosProductos() {
        System.out.println("\n--- Lista de Todos los Productos ---");

        List<Product> productos = productService.getAllProducts();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("ID | Nombre | Precio | ID Restaurante");
            System.out.println("----------------------------------");

            for (Product producto : productos) {
                System.out.printf("%d | %s | %.2f | %d%n",
                        producto.getId(),
                        producto.getName(),
                        producto.getPrice(),
                        producto.getRestaurantId());
            }
        }
    }

    private void listarProductosPorRestaurante() {
        System.out.println("\n--- Listar Productos por Restaurante ---");

        System.out.print("Ingrese el ID del restaurante: ");
        long restauranteId = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer

        List<Product> productos = productService.getProductsByRestaurant(restauranteId);

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados para el restaurante con ID: " + restauranteId);
        } else {
            System.out.println("Productos del Restaurante ID: " + restauranteId);
            System.out.println("ID | Nombre | Precio");
            System.out.println("--------------------");

            for (Product producto : productos) {
                System.out.printf("%d | %s | %.2f%n",
                        producto.getId(),
                        producto.getName(),
                        producto.getPrice());
            }
        }
    }

    private void actualizarProducto() {
        System.out.println("\n--- Actualizar Producto ---");

        System.out.print("Ingrese el ID del producto a actualizar: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer

        Product productoExistente = productService.getProductById(id);

        if (productoExistente == null) {
            System.out.println("No se encontró ningún producto con el ID: " + id);
            return;
        }

        System.out.println("Datos actuales del producto:");
        mostrarDetallesProducto(productoExistente);

        System.out.print("Nuevo nombre (deje en blanco para mantener el actual): ");
        String nombre = scanner.nextLine();
        if (nombre.isEmpty()) {
            nombre = productoExistente.getName();
        }

        System.out.print("Nuevo precio (ingrese 0 para mantener el actual): ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer
        if (precio == 0) {
            precio = productoExistente.getPrice();
        }

        System.out.print("Nuevo ID de restaurante (ingrese 0 para mantener el actual): ");
        long restauranteId = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer
        if (restauranteId == 0) {
            restauranteId = productoExistente.getRestaurantId();
        }

        boolean actualizado = productService.updateProduct(id, nombre, precio, restauranteId);

        if (actualizado) {
            System.out.println("Producto actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar el producto.");
        }
    }

    private void eliminarProducto() {
        System.out.println("\n--- Eliminar Producto ---");

        System.out.print("Ingrese el ID del producto a eliminar: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("¿Está seguro de eliminar este producto? (S/N): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            boolean eliminado = productService.deleteProduct(id);

            if (eliminado) {
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("Error al eliminar el producto o el producto no existe.");
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private void mostrarDetallesProducto(Product producto) {
        System.out.println("\nDetalles del Producto:");
        System.out.println("ID: " + producto.getId());
        System.out.println("Nombre: " + producto.getName());
        System.out.println("Precio: " + producto.getPrice());
        System.out.println("ID del Restaurante: " + producto.getRestaurantId());
    }
}
