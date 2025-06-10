package org.IngSoft.service;

import org.IngSoft.models.Client;
import org.IngSoft.models.Dealer;
import org.IngSoft.models.Enum.Role;
import org.IngSoft.models.Restaurant;
import org.IngSoft.models.User;

import java.util.List;

public interface IUserService {
    boolean registrarCliente(User user, Client client);
    boolean registrarrestaurant(User user, Restaurant restaurant);
    boolean registrarDealer(User user, Dealer dealer);
    List<Dealer> listarRepartidores();
    //boolean registrarNuevoUsuario(String username, String password, String name, Enum.Role role);
    boolean existeUsuario(String username);

    User login(String user, String pass);
    Client getClientByUser(User user);
    Restaurant getRestaurantByUser(User user);
    Dealer getDealerByUser(User user);
}
