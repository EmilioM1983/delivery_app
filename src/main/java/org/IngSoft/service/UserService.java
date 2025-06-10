package org.IngSoft.service;

import org.IngSoft.models.*;
import org.IngSoft.repository.ClientDao;
import org.IngSoft.repository.DealerDao;
import org.IngSoft.repository.RestauratDao;
import org.IngSoft.repository.UserDao;

import java.util.List;

public class UserService implements IUserService {


    ClientDao clientDao = new ClientDao();
    DealerDao dealerDao = new DealerDao();
    UserDao userDao = new UserDao();
    RestauratDao restaurantDao = new RestauratDao();


    @Override
    public boolean registrarCliente(User user, Client client) {
        boolean userSaved = userDao.save(user);

        if (!userSaved) {
            System.out.println("Error al guardar el usuario, no se guarda el cliente.");
            return false;
        }

        client.setUserId(user.getId());

        boolean clientSaved = clientDao.save(client);

        if (!clientSaved) {
            System.out.println("Error al guardar el cliente.");
            return false;
        }

        return true;
    }

    @Override
    public boolean registrarrestaurant(User user, Restaurant restaurant) {
        boolean userSaved = userDao.save(user);

        if (!userSaved) {
            System.out.println("❌ Error al guardar el usuario, no se guarda el restaurante.");
            return false;
        }

        // Verificamos que el ID del usuario haya sido generado correctamente
        if (user.getId() <= 0) {
            System.out.println("❌ ID del usuario no fue generado correctamente.");
            return false;
        }

        // Asignamos el ID del usuario al restaurante
        restaurant.setUserId(user.getId());

        boolean restaurantSaved = restaurantDao.save(restaurant);

        if (!restaurantSaved) {
            System.out.println("❌ Error al guardar el restaurante.");
            return false;
        }

        System.out.println("✅ Usuario y restaurante registrados correctamente.");
        return true;
    }

    @Override
    public boolean registrarDealer(User user, Dealer dealer) {
        boolean userSaved = userDao.save(user);

        if (!userSaved) {
            System.out.println("❌ Error al guardar el usuario, no se guarda el dealer.");
            return false;
        }

        if (user.getId() <= 0) {
            System.out.println("❌ ID del usuario no fue generado correctamente.");
            return false;
        }

        dealer.setUserId(user.getId());

        boolean dealerSaved = dealerDao.save(dealer);

        if (!dealerSaved) {
            System.out.println("❌ Error al guardar el dealer.");
            return false;
        }

        System.out.println("✅ Usuario y dealer registrados correctamente.");
        return true;
    }

    @Override
    public List<Dealer> listarRepartidores() {
        return dealerDao.getAllDealers();
    }


    @Override
    public boolean existeUsuario(String username) {
        List<User> userList = userDao.getAllUsers();
        for (User user : userList) {
            if (user.getUserName().equals(username))
                return true;
        }
        return false;
    }

    @Override
    public User login(String user, String pass) {
        List<User> listUsers = userDao.getAllUsers();
        User usuarioEncontrado= new User();
        for (User u : listUsers) {
            if (u.getUserName().equals(user) && u.getPassword().equals(pass)) {
                usuarioEncontrado.setId(u.getId());
                usuarioEncontrado.setUserName(u.getUserName());
                usuarioEncontrado.setPassword(u.getPassword());
                usuarioEncontrado.setRole(u.getRole());

                return usuarioEncontrado;
            }
        }
        return null;
    }

    public Client getClientByUser(User user) {
        return clientDao.findClientIdByUserId(user.getId());
    }

    public Restaurant getRestaurantByUser(User user) {
        return restaurantDao.findRestaurantIdByUserId(user.getId());
    }

    public Dealer getDealerByUser(User user) {
        return dealerDao.findDealerIdByUserId(user.getId());
    }

}

