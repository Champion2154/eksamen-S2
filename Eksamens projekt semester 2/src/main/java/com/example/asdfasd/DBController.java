package com.example.asdfasd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {

    public Connection databaseLink;
    private Connection conn;
    private Connection connection;
    private Statement stmt;
    private Statement stmt1;


    String connectString = "jdbc:mysql://aws.connect.psdb.cloud/eksamen-semester2?sslMode=VERIFY_IDENTITY";
    String userName = "8xjzf9n7nex9x6d2kbhp";
    String passWord = "pscale_pw_cXbtI5YQjqVcZKp80877SKiTpis7IzSAOiLyOBLJrfP";


    public void Sql() {
        try {
            Connection connection = DriverManager.getConnection(this.connectString, this.userName, this.passWord);

            if (connection == null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

}
/*
    public boolean validate(String UUsername, String PPassword) {
        boolean status = false;
        try {
            // Query the database to retrieve the user's record
            Connection connection = DriverManager.getConnection(this.connectString, this.userName, this.passWord);
            PreparedStatement ps = connection.prepareStatement("select * from medarbejder where brugernavn=? and password=?");
            ps.setString(1, (UUsername));
            ps.setString(2, (PPassword));

            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

}

  /*  public void indsaetKvittering(Kvittering k) {
        try {
            String sql = "INSERT INTO kvittering (kvitID, dato, tidspunkt, kfnavn, kenavn, mnr) VALUES('" + k.getKvitID() + "','" + k.getDato() + "','" + k.getTidspunkt() + "','" + k.getKfnavn() + "','" + k.getKenavn() + "','" + k.getMnr() + "')";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Connection to SQLite has been established.");
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Kvittering alleoplysninger() {
        String sql = "select * from kvittering";
        try {
            Statement stmt = connection.createStatement();
            Statement stmt1 = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Kvittering k = new Kvittering();
                int nr = rs.getInt("kvitID");
                k.setKvitID(nr);
                k.setDato(rs.getString("dato"));
                k.setTidspunkt(rs.getString("tidspunkt"));
                k.setKfnavn(rs.getString("kfnavn"));
                k.setKenavn(rs.getString("kenavn"));
                k.setMnr(rs.getInt("mnr"));
                String sql1 = "SELECT * from kvittering left join tidsbestilling on kvittering.kvitID=tidsbestilling.mnr where kvitID.mnr=" + nr;
                ResultSet rs1 = stmt1.executeQuery(sql1);
                //while (rs1.next()) {
                //kvittering kvit = new kvittering();
                //k..add(kvit);
                //}
            }
            stmt.close();
            stmt1.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alleoplysninger();
    }
}*/
