package com.example.asdfasd;

public class User {
    private String Name;
    private String IdPicture;
    private String ShippingCompany;


    public User(String name, String idPicture, String shippingCompany) {
        Name = name;
        IdPicture = idPicture;
        ShippingCompany = shippingCompany;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", IdPicture='" + IdPicture + '\'' +
                ", ShippingCompany='" + ShippingCompany + '\'' +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIdPicture() {
        return IdPicture;
    }

    public void setIdPicture(String idPicture) {
        IdPicture = idPicture;
    }

    public String getShippingCompany() {
        return ShippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        ShippingCompany = shippingCompany;
    }
}
