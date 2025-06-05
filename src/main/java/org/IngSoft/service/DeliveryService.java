package org.IngSoft.service;

import org.IngSoft.models.Delivery;
import org.IngSoft.models.Enum.StatusDelivery;
import org.IngSoft.repository.DeliveryDao;

import java.util.List;

public class DeliveryService {
    private DeliveryDao deliveryDao = new DeliveryDao();

    public DeliveryService() {
        this.deliveryDao = new DeliveryDao();
    }

    public void crearDelivery(Delivery delivery) {
        deliveryDao.guardar(delivery);
    }

    public Delivery obtenerDelivery(long id) {
        return deliveryDao.buscarPorId(id);
    }

    public List<Delivery> listarDeliveries() {
        return deliveryDao.obtenerTodos();
    }

    public void actualizarDelivery(Delivery delivery) {
        deliveryDao.actualizar(delivery);
    }

    public void eliminarDelivery(long id) {
        deliveryDao.eliminar(id);
    }

    /**
     * Crea un nuevo delivery asignando un pedido (orderId) a un repartidor (dealerId)
     * con estado por defecto InProgress
     */
    public void asignarPedidoARepartidor(long orderId, long dealerId) {
        Delivery delivery = new Delivery(StatusDelivery.InProgress, dealerId, orderId);
        deliveryDao.guardar(delivery);
        System.out.println("✔ Pedido asignado correctamente.");
    }
}