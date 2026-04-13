import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
    }

    // ✅ Username Tests
    @Test
    public void testCorrectUsername() {
        assertTrue(login.checkUserName("k_yl"));
    }

    @Test
    public void testIncorrectUsername() {
        assertFalse(login.checkUserName("kyle123"));
    }

    // ✅ Password Tests
    @Test
    public void testCorrectPassword() {
        assertTrue(login.checkPasswordComplexity("Pass@123"));
    }

    @Test
    public void testIncorrectPassword() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // ✅ Phone Number Tests
    @Test
    public void testCorrectPhoneNumber() {
        assertTrue(login.checkCellPhoneNumber("+27831234567"));
    }

    @Test
    public void testIncorrectPhoneNumber() {
        assertFalse(login.checkCellPhoneNumber("0831234567"));
    }

    // ✅ Registration Test
    @Test
    public void testSuccessfulRegistration() {
        String result = login.registerUser("k_yl", "Pass@123", "+27831234567");
        assertEquals("User successfully registered.", result);
    }

    @Test
    public void testFailedRegistration() {
        String result = login.registerUser("kyle", "pass", "083");
        assertNotEquals("User successfully registered.", result);
    }

    // ✅ Login Tests
    @Test
    public void testSuccessfulLogin() {
        login.registerUser("k_yl", "Pass@123", "+27831234567");
        assertTrue(login.loginUser("k_yl", "Pass@123"));
    }

    @Test
    public void testFailedLogin() {
        login.registerUser("k_yl", "Pass@123", "+27831234567");
        assertFalse(login.loginUser("wrong", "wrong"));
    }
}