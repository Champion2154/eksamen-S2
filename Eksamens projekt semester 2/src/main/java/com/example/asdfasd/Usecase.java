package com.example.asdfasd;

public class Usecase {


    public String checkin(String FirstName, String LastName, String koerekort ) {
        if (FirstName == null || LastName == null || koerekort == null){
                return "Alle felter var ikke udfyldte";
        } else {
            DBController dbc = new DBController();
            String ok = dbc.registerCheckIn(FirstName, LastName, koerekort);
        if (ok == null){
                return "Der skete en fejl. Registeringen gik ikke igennem";

        } else {
                return "Det gik godt";
        }

        }

    }


}
