package org.IngSoft.models;

public class Client {
    //Atributos
    private long id;
    private String name;
    private String lastName;
    private String phone;
    private String adress;
    private long userId;

    //Constructores
    public Client() {
    }

    public Client(String name, String lastName, String phone, String adress, long userId) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.adress = adress;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
