package com.example.asdfasd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {





    private final String connectString = "jdbc:mysql://aws.connect.psdb.cloud/eksamen-semester2?sslMode=VERIFY_IDENTITY";
    private final String userName = "o9qv1zc2dmo17fb1ris6";
    private final String passWord = "pscale_pw_AGqNzeNrfqqq3HZjylbjhV93GNAXACdlhOPec6YXydX";






    public Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(connectString, userName, passWord);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public DBController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        User user = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            connection = connect();
            String sql = "SELECT * FROM User WHERE UserID = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setFirstname(resultSet.getString("Firstname"));
                user.setLastname(resultSet.getString("Lastname"));
                user.setDriverLicenseNumber(resultSet.getString("DriverLicenseNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }


    public void InsertUser(User user, Firms firms) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = connect();
            String sql = "INSERT INTO User (Firstname, Lastname, DriverLicenseNumber) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getFirstname());
            pstmt.setString(2, user.getLastname());
            pstmt.setString(3, user.getDriverLicenseNumber());
            pstmt.executeUpdate();
            // Retrieve the auto-generated UserID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            int userID = -1;
            if (generatedKeys.next()) {
                userID = generatedKeys.getInt(1);
            }
            generatedKeys.close();
            if (userID != -1) {
                String sql2 = "INSERT INTO UserCheckin (UserID, TimeForCheckIn, FirmID) VALUES (?, CURRENT_TIMESTAMP, ?)";
                pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, userID);
                pstmt.setString(2, firms.getFirmID());
                pstmt.executeUpdate();
            }
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

  /*  @GetMapping("/displayUser")
    @ResponseBody
    public User displayUser(@RequestParam("UserID") int userId) {
        String query = "SELECT Firstname, Lastname, DriverLicenseNumber FROM user WHERE UserID = ?";
        User user = null;
        try (Connection connection = DriverManager.getConnection(connectString, userName, passWord);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setFirstname(resultSet.getString("Firstname"));
                user.setLastname(resultSet.getString("Lastname"));
                user.setDriverLicenseNumber(resultSet.getString("DriverLicenseNumber"));

                System.out.println("User Data:");
                System.out.println("First Name: " + user.getFirstname());
                System.out.println("Last Name: " + user.getLastname());
                System.out.println("Driver's License Number: " + user.getDriverLicenseNumber());
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

   */
  @PostMapping("/User")
  public User displayUser(@RequestParam("UserID") int UserID) {
      // Perform database query and retrieval logic here
      // Replace the code below with your actual database queries and retrieval logic
      String sql = "SELECT Firstname, Lastname, DriverLicenseNumber FROM user WHERE UserID = ?";
      User user = null;
      try (Connection connection = DriverManager.getConnection(connectString, userName, passWord);
           PreparedStatement statement = connection.prepareStatement(sql)) {
          statement.setInt(1, UserID);
          ResultSet resultSet = statement.executeQuery();
          if (resultSet.next()) {
              user = new User();
              user.setFirstname(resultSet.getString("Firstname"));
              user.setLastname(resultSet.getString("Lastname"));
              user.setDriverLicenseNumber(resultSet.getString("DriverLicenseNumber"));
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return user;
  }


}


// Add other methods here if needed

   /* public void indsaetKvittering(Kvittering k) {
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

    */



















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
            e.printStackTarce();
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