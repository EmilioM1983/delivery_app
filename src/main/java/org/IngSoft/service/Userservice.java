package org.IngSoft.service;

import org.IngSoft.models.Client;
import org.IngSoft.models.Dealer;
import org.IngSoft.models.Restaurant;
import org.IngSoft.models.User;
import org.IngSoft.repository.ClientDao;
import org.IngSoft.repository.DealerDao;
import org.IngSoft.repository.RestauratDao;
import org.IngSoft.repository.UserDao;

import java.util.List;

public class Userservice implements IUserService{
    UserDao userDao = new UserDao();
    ClientDao clientDao = new ClientDao();
    RestauratDao restauratDao = new RestauratDao();
    DealerDao dealerDao = new DealerDao();

    @Override
    public boolean registrarCliente(User user, Client client) {
        boolean confUser = userDao.save(user);
        boolean confCliente= clientDao.save(client);
        return confUser && confCliente;
    }

    @Override
    public boolean registrarrestaurant(User user, Restaurant restaurant) {
        boolean confUser = userDao.save(user);
        boolean confRest= restauratDao.save(restaurant);
        return confUser && confRest;
    }

    @Override
    public boolean registrarDealer(User user, Dealer dealer) {
        boolean confUser = userDao.save(user);
        boolean confDealer= dealerDao.save(dealer);
        return confUser && confDealer;
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
        return restauratDao.findRestaurantIdByUserId(user.getId());
    }

    public Dealer getDealerByUser(User user) {
        return dealerDao.findDealerIdByUserId(user.getId());
    }

}
