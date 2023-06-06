package com.example.asdfasd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DBControllerTest {

    private DBController dbController;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        dbController = new DBController();
        mockUser = new User();
        mockUser.setFirstname("John");
        mockUser.setLastname("Doe");
        mockUser.setDriverLicenseNumber("ABC123");
    }

    @Test
    public void testGetUserById() throws SQLException {
        int userId = 1;
        User expectedUser = mockUser;

        // Mock the database connection and result set
        DBController mockedDbController = spy(dbController);
        User mockedUser = mock(User.class);
        Connection mockedConnection = mock(Connection.class);
        PreparedStatement mockedStatement = mock(PreparedStatement.class);
        ResultSet mockedResultSet = mock(ResultSet.class);

        when(mockedDbController.connect()).thenReturn(mockedConnection);
        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedStatement);
        when(mockedStatement.executeQuery()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true);
        when(mockedResultSet.getString("Firstname")).thenReturn(mockUser.getFirstname());
        when(mockedResultSet.getString("Lastname")).thenReturn(mockUser.getLastname());
        when(mockedResultSet.getString("DriverLicenseNumber")).thenReturn(mockUser.getDriverLicenseNumber());

        // Call the method under test
        User result = mockedDbController.getUserById(userId);

        // Verify the interactions and assertions
        verify(mockedDbController).connect();
        verify(mockedConnection).prepareStatement(anyString());
        verify(mockedStatement).setInt(1, userId);
        verify(mockedStatement).executeQuery();
        verify(mockedResultSet).next();
        verify(mockedResultSet).getString("Firstname");
        verify(mockedResultSet).getString("Lastname");
        verify(mockedResultSet).getString("DriverLicenseNumber");
        verify(mockedDbController).close(mockedConnection);

        assertEquals(expectedUser.getFirstname(), result.getFirstname());
        assertEquals(expectedUser.getLastname(), result.getLastname());
        assertEquals(expectedUser.getDriverLicenseNumber(), result.getDriverLicenseNumber());
    }
}
