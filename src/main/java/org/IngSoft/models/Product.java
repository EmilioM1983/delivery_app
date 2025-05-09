package org.IngSoft.models;

public class Product {
    //Atributos
    private long id;
    private String name;
    private double price;
    private long restaurantId;

    //Constructores
    public Product() {
    }

    public Product(String name, double price, long restaurantId) {
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    //Gets & Sets
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
