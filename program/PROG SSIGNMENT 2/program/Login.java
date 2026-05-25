import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Login {

    // USER VARIABLES

    private String username;
    private String password;
    private String cellPhone;

    // MESSAGE VARIABLES
  
    static ArrayList<String> sentMessages = new ArrayList<>();
    static int totalMessages = 0;

    
    // 1. USERNAME CHECK
    
    public boolean checkUserName(String username) {

        return username.contains("_")
                && username.length() <= 5;
    }

    
    // 2. PASSWORD CHECK

    public boolean checkPasswordComplexity(String password) {

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        if (password.length() < 8) {
            return false;
        }

        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }

            else if (Character.isDigit(c)) {
                hasNumber = true;
            }

            else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

  
    // 3. CELL PHONE CHECK

    public boolean checkCellPhoneNumber(String number) {

        return number.startsWith("+27")
                && number.length() <= 12;
    }


    // 4. REGISTER USER
  
    public String registerUser(String username,
                               String password,
                               String number) {

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

    // 5. LOGIN USER
   
    public boolean loginUser(String username,
                             String password) {

        return username != null
                && password != null
                && username.equals(this.username)
                && password.equals(this.password);
    }

    
    // 6. LOGIN STATUS
    
    public String returnLoginStatus(boolean loginSuccess,
                                    String firstName,
                                    String lastName) {

        if (loginSuccess) {

            return "Welcome "
                    + firstName
                    + " "
                    + lastName
                    + ", it is great to see you again.";
        }

        else {

            return "Username or password incorrect, please try again.";
        }
    }

    
    // ================= PART 2 METHODS =====================
  

    // CHECK MESSAGE ID
    public boolean checkMessageID(String messageID) {

        return messageID.length() <= 10;
    }

    // CHECK RECIPIENT CELL
    public boolean checkRecipientCell(String recipient) {

        return recipient.startsWith("+27")
                && recipient.length() <= 12;
    }

    // CHECK MESSAGE LENGTH
    public String checkMessageLength(String message) {

        if (message.length() <= 250) {

            return "Message ready to send.";
        }

        else {

            int extra = message.length() - 250;

            return "Message exceeds 250 characters by "
                    + extra
                    + ", please reduce the size.";
        }
    }

    // CREATE MESSAGE HASH
    public String createMessageHash(String messageID,
                                    int messageNumber,
                                    String message) {

        String[] words = message.split(" ");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        return messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;
    }

    // SEND MESSAGE OPTIONS
    public String SentMessage(int option,
                              String message) {

        switch (option) {

            case 1:

                sentMessages.add(message);
                totalMessages++;

                return "Message successfully sent.";

            case 2:

                return "Press 0 to delete the message.";

            case 3:

                return "Message successfully stored.";

            default:

                return "Invalid option.";
        }
    }

    // PRINT MESSAGES
    public String printMessages() {

        String allMessages = "";

        for (String msg : sentMessages) {

            allMessages += msg + "\n";
        }

        return allMessages;
    }

    // RETURN TOTAL MESSAGES
    public int returnTotalMessages() {

        return totalMessages;
    }

    // GENERATE MESSAGE ID
    public String generateMessageID() {

        Random random = new Random();

        long number =
                1000000000L
                        + (long)(random.nextDouble()
                        * 8999999999L);

        return String.valueOf(number);
    }

   
    // ===================== MAIN PROGRAM ===================
 

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Login system = new Login();

   
        // REGISTRATION
     
        System.out.println("===== REGISTRATION =====");

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter SA phone number (+27...): ");
        String phone = input.nextLine();

        String registerMessage =
                system.registerUser(
                        username,
                        password,
                        phone
                );

        System.out.println(registerMessage);

        // STOP IF REGISTRATION FAILS
        if (!registerMessage.equals(
                "User successfully registered.")) {

            return;
        }

      
        // LOGIN
  
        System.out.println("\n===== LOGIN =====");

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username: ");
        String loginUser = input.nextLine();

        System.out.print("Enter password: ");
        String loginPass = input.nextLine();

        boolean loginStatus =
                system.loginUser(
                        loginUser,
                        loginPass
                );

        String message =
                system.returnLoginStatus(
                        loginStatus,
                        firstName,
                        lastName
                );

        System.out.println(message);

        // STOP IF LOGIN FAILS
        if (!loginStatus) {

            return;
        }

        
        // ================= QUICKCHAT SECTION ==================
       

        System.out.println("\nWelcome to QuickChat");

        System.out.print(
                "How many messages would you like to send? "
        );

        int numMessages = input.nextInt();
        input.nextLine();

        int sentCount = 0;

        // MENU LOOP
        while (true) {

            System.out.println("\n===== MENU =====");

            System.out.println("1. Send Messages");

            System.out.println(
                    "2. Show recently sent messages"
            );

            System.out.println("3. Quit");

            System.out.print("Choose option: ");

            int choice = input.nextInt();
            input.nextLine();

          
            // SEND MESSAGES
       
            if (choice == 1) {

                while (sentCount < numMessages) {

                    System.out.println(
                            "\nMESSAGE "
                                    + (sentCount + 1)
                    );

                    // GENERATE MESSAGE ID
                    String messageID =
                            system.generateMessageID();

                    System.out.println(
                            "Message ID: "
                                    + messageID
                    );

                    // CHECK MESSAGE ID
                    if (system.checkMessageID(messageID)) {

                        System.out.println(
                                "Message ID generated successfully."
                        );
                    }

                    else {

                        System.out.println(
                                "Invalid message ID."
                        );
                    }

                    // RECIPIENT NUMBER
                    System.out.print(
                            "Enter recipient number (+27...): "
                    );

                    String recipient =
                            input.nextLine();

                    // CHECK RECIPIENT NUMBER
                    if (system.checkRecipientCell(recipient)) {

                        System.out.println(
                                "Cell phone number successfully captured."
                        );
                    }

                    else {

                        System.out.println(
                                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again."
                        );

                        continue;
                    }

                    // MESSAGE
                    System.out.print(
                            "Enter your message: "
                    );

                    String userMessage =
                            input.nextLine();

                    String lengthCheck =
                            system.checkMessageLength(
                                    userMessage
                            );

                    System.out.println(lengthCheck);

                    // STOP IF MESSAGE TOO LONG
                    if (!lengthCheck.equals(
                            "Message ready to send.")) {

                        continue;
                    }

                    // CREATE HASH
                    String hash =
                            system.createMessageHash(
                                    messageID,
                                    sentCount,
                                    userMessage
                            );

                    System.out.println(
                            "Message Hash: "
                                    + hash
                    );

                    // SEND OPTIONS
                    System.out.println("\nChoose:");

                    System.out.println(
                            "1. Send Message"
                    );

                    System.out.println(
                            "2. Disregard Message"
                    );

                    System.out.println(
                            "3. Store Message"
                    );

                    int sendOption =
                            input.nextInt();

                    input.nextLine();

                    String sendResult =
                            system.SentMessage(
                                    sendOption,
                                    userMessage
                            );

                    System.out.println(sendResult);

                    // DISPLAY MESSAGE DETAILS
                    if (sendOption == 1) {

                        System.out.println(
                                "\n===== MESSAGE DETAILS ====="
                        );

                        System.out.println(
                                "Message ID: "
                                        + messageID
                        );

                        System.out.println(
                                "Message Hash: "
                                        + hash
                        );

                        System.out.println(
                                "Recipient: "
                                        + recipient
                        );

                        System.out.println(
                                "Message: "
                                        + userMessage
                        );

                        sentCount++;
                    }
                }
            }

           
            // SHOW SENT MESSAGES
            
            else if (choice == 2) {

                System.out.println(
                        "Coming Soon."
                );
            }

           
            // QUIT
            
            else if (choice == 3) {

                System.out.println(
                        "\nTotal messages sent: "
                                + system.returnTotalMessages()
                );

                System.out.println("Goodbye!");

                break;
            }

            else {

                System.out.println(
                        "Invalid option."
                );
            }
        }
    }
}