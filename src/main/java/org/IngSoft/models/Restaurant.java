package org.IngSoft.models;

public class Restaurant {
    //Atributos
    private long id;
    private String name;
    private String addres;
    private long userId;

    //Constructores
    public Restaurant() {
    }

    public Restaurant(String name, String addres, long userId) {
        this.name = name;
        this.addres = addres;
        this.userId = userId;
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

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
