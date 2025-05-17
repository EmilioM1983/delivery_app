package org.IngSoft.ui;

import org.IngSoft.models.Enum.PaymentMethod;
import org.IngSoft.models.Enum.StatusPayment;
import org.IngSoft.models.OrderItem;
import org.IngSoft.models.Payment;
import org.IngSoft.service.PaymentService;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Scanner;

public class PaymentUI {
    PaymentService paymentService = new PaymentService();

    Scanner consolaNum = new Scanner(System.in);
    Scanner consolaText = new Scanner(System.in);

    boolean confirmar;
    int opcion;

    Payment payment = new Payment();
    long id_orderitem;

    public void menuPagos() {
        do {
            System.out.println("""
                    **Registro de pagos**
                    1. Registrar pago
                    2. Salir
                    """);
            opcion = consolaNum.nextInt();

            switch (opcion) {
                case 1 -> {
                    int opcionPagos;
                    // Mostrar los items de pedido disponibles
                    List<OrderItem> orderItems = paymentService.getAllOrdersItems();
                    System.out.println("=== Items de pedido disponibles ===");
                    for (OrderItem item : orderItems) {
                        double precio = paymentService.calcularPrecioTotal(item.getId());
                        System.out.println("ID: " + item.getId() +
                                " | Cantidad: " + item.getQuantity() +
                                " | ID Producto: " + item.getProductId() +
                                " | Precio Total: $" + precio);
                    }

                    // Solicitar al usuario que seleccione un item para pagar
                    System.out.println("Ingrese el ID del item que desea pagar:");
                    id_orderitem = consolaNum.nextLong();

                    // Verificar que el ID existe
                    boolean idValido = orderItems.stream().anyMatch(item -> item.getId() == id_orderitem);
                    if (!idValido) {
                        System.out.println("ID de item no válido. Volviendo al menú principal.");
                        continue;
                    }

                    // Mostrar el precio a pagar
                    double precioPagar = paymentService.calcularPrecioTotal(id_orderitem);
                    System.out.println("Precio a pagar: $" + precioPagar);

                    // Seleccionar método de pago
                    System.out.println("""
                            Ingrese método de pago...
                            1. Card
                            2. Cash
                            """);
                    opcionPagos = consolaNum.nextInt();
                    if (opcionPagos == 1)
                        payment.setPaymentMethod(PaymentMethod.Card);
                    else if (opcionPagos == 2)
                        payment.setPaymentMethod(PaymentMethod.Cash);
                    else {
                        System.out.println("Opción de pago incorrecta");
                        continue;
                    }

                    // Seleccionar estado del pago
                    System.out.println("""
                            Ingrese estado de pago...
                            1. Confirmar
                            2. Fallo
                            """);
                    opcionPagos = consolaNum.nextInt();
                    if (opcionPagos == 1)
                        payment.setStatusPayment(StatusPayment.Confirmed);
                    else if (opcionPagos == 2)
                        payment.setStatusPayment(StatusPayment.Failed);
                    else {
                        System.out.println("Opción de estado incorrecta");
                        continue;
                    }
                    payment.setTotalPrice(precioPagar);
                    payment.setOrderId(id_orderitem);
                    // Guardar el pago
                    confirmar = paymentService.paymentSave(payment);

                    if (confirmar) {
                        System.out.println("¡Pago registrado con éxito!");
                    } else {
                        System.out.println("Error al registrar el pago.");
                    }
                }
                case 2 -> System.out.println("Saliendo del sistema de pagos...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 2);


    }
}
