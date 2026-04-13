import java.util.Scanner;

public class Login {

    private String username;
    private String password;
    private String cellPhone;

    // 1. Username check
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // 2. Password check
    public boolean checkPasswordComplexity(String password) {
        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        if (password.length() < 8) return false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // 3. Cell phone check
    public boolean checkCellPhoneNumber(String number) {
        return number.startsWith("+27") && number.length() <= 12;
    }

    // 4. Register user
    public String registerUser(String username, String password, String number) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(number)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        this.username = username;
        this.password = password;
        this.cellPhone = number;

        return "User successfully registered.";
    }

    // 5. Login user
    public boolean loginUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // 6. Login status message
    public String returnLoginStatus(boolean loginSuccess, String firstName, String lastName) {
        if (loginSuccess) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // MAIN PROGRAM
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login system = new Login();

        // REGISTRATION
        System.out.println("=== REGISTRATION ===");

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter SA phone number (+27...): ");
        String phone = input.nextLine();

        String registerMessage = system.registerUser(username, password, phone);
        System.out.println(registerMessage);

        // If registration failed, stop
        if (!registerMessage.equals("User successfully registered.")) {
            return;
        }

        // LOGIN
        System.out.println("\n=== LOGIN ===");

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username: ");
        String loginUser = input.nextLine();

        System.out.print("Enter password: ");
        String loginPass = input.nextLine();

        boolean loginStatus = system.loginUser(loginUser, loginPass);

        String message = system.returnLoginStatus(loginStatus, firstName, lastName);
        System.out.println(message);
    }
}