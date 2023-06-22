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
    private final String userName = "dis7giynq1h9d0b39w61";
    private final String passWord = "pscale_pw_vh0Mu4P9GoY0A86uvhQnMM0MDqVN5J8qNLy9R3TXMP5";






    public Connection connect() {
        //Establish a database connection
        try {
            Connection connection = DriverManager.getConnection(connectString, userName, passWord);//use DriverManager to establish a connection using the provided connectString, userName, and passWord
            return connection;//Return the established connection
        } catch (SQLException e) {
            e.printStackTrace();//Print the stack trace if an SQLException occurs during the connection establishment
        }
        return null;//Return null if the connection could not be established
    }

    public void close(Connection connection) {
        //Close the database connection
        if (connection != null) {
            try {
                connection.close();//Close the provided connection if it is not null
            } catch (SQLException e) {
                e.printStackTrace();//Print the stack trace if an SQLException occurs during the connection closing
            }
        }
    }

    public DBController() {
        //Constructor for DBController
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//Load the JDBC driver class for MySQL
        } catch (ClassNotFoundException e) {
            e.printStackTrace();//print the stack trace if the driver class is not found
        }
    }


    public User getUserById(int userId) {
        //Declare and initialize variables
        User user = null;//Store the retrieved user object
        Connection connection = null;//Database connection object
        PreparedStatement pstmt = null;//Prepared statement object
        ResultSet resultSet = null;//Result set object

        try {
            //Establish a database connection
            connection = connect();//Call the connect() method to get a database connection

            //Prepare and execute the SQL query
            String sql = "SELECT * FROM User WHERE UserID = ?";//SQL query to select user by UserID
            pstmt = connection.prepareStatement(sql);//Create a prepared statement with the SQL query
            pstmt.setInt(1, userId);//Set the UserID as a parameter in the prepared statement
            resultSet = pstmt.executeQuery();//Execute the query and get the result set

            //Check if a user is found in the result set
            if (resultSet.next()) {
                //Create a new User object and set its attributes
                user = new User();
                user.setFirstname(resultSet.getString("Firstname"));
                user.setLastname(resultSet.getString("Lastname"));
                user.setDriverLicenseNumber(resultSet.getString("DriverLicenseNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();//Print the stack trace if a SQLException occurs
        } finally {
            //Close the resources in the finally-block
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                close(connection);//Close the database connection
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;//Return the retrieved user object
    }


    public void InsertUser(User user, Firms firms) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = connect();//Establish a database connection
            String sql = "INSERT INTO User (Firstname, Lastname, DriverLicenseNumber) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//Prepare the SQL statement for inserting a new user into the User table, with auto-generated keys
            pstmt.setString(1, user.getFirstname());//Set the first parameter (Firstname) of the prepared statement as the user's first name
            pstmt.setString(2, user.getLastname());//Set the second parameter (Lastname) of the prepared statement as the user's last name
            pstmt.setString(3, user.getDriverLicenseNumber());//Set the third parameter (DriverLicenseNumber) of the prepared statement as the user's driver license number
            pstmt.executeUpdate();//Execute the SQL statement to insert the user into the User table

            //Retrieve the auto-generated UserID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();//Retrieve the auto-generated keys after the user insertion
            int userID = -1;
            if (generatedKeys.next()) {
                userID = generatedKeys.getInt(1);//Get the auto-generated UserID
            }
            generatedKeys.close();

            if (userID != -1) {
                String sql2 = "INSERT INTO UserCheckin (UserID, TimeForCheckIn, FirmID) VALUES (?, CURRENT_TIMESTAMP, ?)";
                pstmt = connection.prepareStatement(sql2);//Prepare the SQL statement for inserting a new user check-in record into the UserCheckin table
                pstmt.setInt(1, userID);//Set the first parameter (UserID) of the prepared statement as the retrieved UserID
                pstmt.setString(2, firms.getFirmID());//Set the second parameter (FirmID) of the prepared statement as the firm's ID
                pstmt.executeUpdate();//Execute the SQL statement to insert the user check-in record into the UserCheckin table
            }

            System.out.println("Data inserted successfully.");//Print a success message

        } catch (SQLException e) {
            e.printStackTrace();//Print the stack trace if an SQLException occurs during the database operations

        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();//Close the prepared statement
                }
                close(connection);//Close the database connection

            } catch (SQLException e) {
                e.printStackTrace();//Print the stack trace if an SQLException occurs during the resource closing
            }
        }
    }


    @PostMapping("/User")
  public User displayUser(@RequestParam("UserID") int UserID) {
      //Prepare and execute the SQL query
      String sql = "SELECT Firstname, Lastname, DriverLicenseNumber FROM user WHERE UserID = ?";
      User user = null;//Store the retrieved user object
      //Establish a database connection, by calling the connect() method.
      try (Connection connection = DriverManager.getConnection(connectString, userName, passWord);
          //Create a prepared statement with the SQL query
          PreparedStatement statement = connection.prepareStatement(sql)) {
          //Set the UserID as a parameter.
          statement.setInt(1, UserID);
          //Execute the query and get the result set
          ResultSet resultSet = statement.executeQuery();
          //Check if a user is found in the result set
          if (resultSet.next()) {
              user = new User();
              user.setFirstname(resultSet.getString("Firstname"));
              user.setLastname(resultSet.getString("Lastname"));
              user.setDriverLicenseNumber(resultSet.getString("DriverLicenseNumber"));
          }
          //Print the stack trace if a SQLException occurs
      } catch (SQLException e) {
          e.printStackTrace();
      }
      //Display the retrieved user object
      return user;
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