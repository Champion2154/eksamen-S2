package com.example.asdfasd;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class createUserTest {

    @Test
    void contextLoads() {
    }


//We will now create a test that tests the createUser method.
    @Test
    public void createNewUserTest() {
        String firstName = "John";
        String lastName = "Doe";
        String driverLicenseNumber = "12345";

        User user = createUser(firstName, lastName, driverLicenseNumber);

        assertNotNull(user);
        assertEquals(firstName, user.getFirstname());
        assertEquals(lastName, user.getLastname());
        assertEquals(driverLicenseNumber, user.getDriverLicenseNumber());
    }

    private User createUser(String firstName, String lastName, String driverLicenseNumber) {
        User user = new User();
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setDriverLicenseNumber(driverLicenseNumber);
        return user;
    }

}
