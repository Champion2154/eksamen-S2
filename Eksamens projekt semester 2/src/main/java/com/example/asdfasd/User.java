package com.example.asdfasd;

public class User {
    private String Firstname;
    private String Lastname;
    private String IdPicture;
    private String Firm;
    private String DriverLicenseNumber;


    @Override
    public String toString() {
        return "User{" +
                "Firstname='" + Firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", IdPicture='" + IdPicture + '\'' +
                ", Firm='" + Firm + '\'' +
                ", DriverLicenseNumber='" + DriverLicenseNumber + '\'' +
                '}';
    }

    public User(String firstname, String lastname, /*String idPicture, */ String firm, String driverLicenseNumber) {
        Firstname = firstname;
        Lastname = lastname;
        //IdPicture = idPicture;
        Firm = firm;
        DriverLicenseNumber = driverLicenseNumber;
    }

    public User() {
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getIdPicture() {
        return IdPicture;
    }

    public void setIdPicture(String idPicture) {
        IdPicture = idPicture;
    }

    public String getFirm() {
        return Firm;
    }

    public void setFirm(String firm) {
        Firm = firm;
    }

    public String getDriverLicenseNumber() {
        return DriverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        DriverLicenseNumber = driverLicenseNumber;
    }
}
