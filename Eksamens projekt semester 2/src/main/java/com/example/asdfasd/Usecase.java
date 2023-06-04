package com.example.asdfasd;

public class Usecase {


    /*public String checkin(String FirstName, String LastName, String DriverLicenseNumber ) {
        if (FirstName == null || LastName == null || DriverLicenseNumber == null){
            return "Alle felter var ikke udfyldte";
        } else {
            DBController dbc = new DBController();
            String ok = dbc.registerCheckIn(FirstName, LastName, DriverLicenseNumber);
            if (ok == null){
                return "Der skete en fejl. Registeringen gik ikke igennem";

            } else {
                return "Det gik godt";
            }

        }

    }


     */
    public User createUser(String firstName, String lastName, String driverLicenseNumber) {
        User user = new User();
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setDriverLicenseNumber(driverLicenseNumber);
        // Set any other necessary properties for the User object
        return user;
    }

    public Firms insertFirmID(String FirmID) {
        Firms firms = new Firms();
        firms.setFirmID(FirmID);
        // Set any other necessary properties for the User object
        return firms;
    }



}