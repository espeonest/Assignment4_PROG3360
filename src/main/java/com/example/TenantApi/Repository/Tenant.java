package com.example.TenantApi.Repository;

public class Tenant {
    private int id;
    private String name;
    private String address;
    private String email;
    private float rent;
    private float amountOwing;
    public Tenant(int id, String name, String address, String email, float rent) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.rent = rent;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getRent() {
        return rent;
    }

    public void setRent(float rent) {
        this.rent = rent;
    }

    public float getAmountOwing() {
        return amountOwing;
    }

    public void setAmountOwing(float amountOwing) {
        this.amountOwing = amountOwing;
    }
}
