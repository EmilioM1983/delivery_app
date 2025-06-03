package org.IngSoft.service;

import org.IngSoft.models.Product;
import org.IngSoft.repository.ProductDao;

import java.util.List;

public class ProductService {
    // Atributos
    private final ProductDao productDao;

    // Constructor
    public ProductService() {
        this.productDao = new ProductDao();
    }


    public Product createProduct(String name, double price, long restaurantId) {
        Product product = new Product(name, price, restaurantId);
        long id = productDao.create(product);

        if (id != -1) {
            return product;
        } else {
            return null;
        }
    }


    public Product getProductById(long id) {
        return productDao.findById(id);
    }


    public List<Product> getAllProducts() {
        return productDao.findAll();
    }


    public List<Product> getProductsByRestaurant(long restaurantId) {
        return productDao.findByRestaurant(restaurantId);
    }


    public boolean updateProduct(long id, String name, double price, long restaurantId) {
        Product product = productDao.findById(id);

        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            product.setRestaurantId(restaurantId);

            return productDao.update(product);
        }

        return false;
    }


    public boolean deleteProduct(long id) {
        return productDao.delete(id);
    }
}
