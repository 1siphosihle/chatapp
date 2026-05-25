import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private Login login;

    @BeforeEach
    public void setup() {
        login = new Login();
    }

    // Username Tests
    @Test
    public void testValidUsername() {
        assertTrue(login.checkUserName("k_yl"));
    }

    @Test
    public void testInvalidUsername() {
        assertFalse(login.checkUserName("kyle123"));
    }

    // Password Tests
    @Test
    public void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Pass@123"));
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // Phone Tests
    @Test
    public void testValidPhone() {
        assertTrue(login.checkCellPhoneNumber("+27831234567"));
    }

    @Test
    public void testInvalidPhone() {
        assertFalse(login.checkCellPhoneNumber("0831234567"));
    }

    // Registration Tests
    @Test
    public void testRegistrationSuccess() {
        String result = login.registerUser("k_yl", "Pass@123", "+27831234567");
        assertEquals("User successfully registered.", result);
    }

    @Test
    public void testRegistrationFail() {
        String result = login.registerUser("bad", "pass", "083");
        assertNotEquals("User successfully registered.", result);
    }

    // Login Tests
    @Test
    public void testLoginSuccess() {
        login.registerUser("k_yl", "Pass@123", "+27831234567");
        assertTrue(login.loginUser("k_yl", "Pass@123"));
    }

    @Test
    public void testLoginFail() {
        login.registerUser("k_yl", "Pass@123", "+27831234567");
        assertFalse(login.loginUser("wrong", "wrong"));
    }
}