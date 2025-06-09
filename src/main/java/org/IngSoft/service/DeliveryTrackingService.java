package org.IngSoft.service;

import org.IngSoft.database.Conexion;
import org.IngSoft.models.Enum.StatusDelivery;
import org.IngSoft.repository.RastreoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeliveryTrackingService {

    RastreoDao rastreoDao = new RastreoDao();

    public String obtenerEstadoDePedido(long orderId){
        return rastreoDao.obtenerEstadoDePedido(orderId);
    }
}
