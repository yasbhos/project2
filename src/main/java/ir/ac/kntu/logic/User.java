package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;

public class User {
    enum Option {
        CHANGE_NAME, CHANGE_USERNAME, CHANGE_PASSWORD, CHANGE_EMAIL,
        CHANGE_PHONE_NUMBER, CHANGE_NATIONAL_CODE, BACK, UNDEFINED
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String nationalCode;

    public User(String name, String username, String password, String email, String phoneNumber, String nationalCode) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void changeHandler() {
        Option option;
        do {
            printTheMenu();
            option = scanOption();
            handleTheOption(option);
        } while (option != Option.BACK);
    }

    public void handleTheOption(Option option) {
        switch(option) {
            case CHANGE_NAME:
                this.name = ScannerWrapper.readString("Enter new name: ");
                break;
            case CHANGE_USERNAME:
                this.username = ScannerWrapper.readString("Enter new username: ");
                break;
            case CHANGE_PASSWORD:
                if (ScannerWrapper.readString("Enter old password: ").equals(password)) {
                    this.password = ScannerWrapper.readString("Enter new password: ");
                } else {
                    System.out.println("Wrong password");
                }
                break;
            case CHANGE_EMAIL:
                this.email = ScannerWrapper.readString("Enter new e-mail: ");
                break;
            case CHANGE_PHONE_NUMBER:
                this.phoneNumber = ScannerWrapper.readString("Enter new phone number: ");
                break;
            case CHANGE_NATIONAL_CODE:
                this.nationalCode = ScannerWrapper.readString("Enter new national code: ");
                break;
            case BACK:
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
    }

    public static Option scanOption() {
        Option[] options = Option.values();
        int userInput = ScannerWrapper.nextInt();
        ScannerWrapper.nextLine();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return Option.UNDEFINED;
    }

    public void printTheMenu() {
        System.out.println(ANSI_YELLOW);
        System.out.println("*************************");
        System.out.println("User change menu: ");
        System.out.println("1. Change name");
        System.out.println("2. Change username");
        System.out.println("3. Change password");
        System.out.println("4. Change e-mail");
        System.out.println("5. Change phone number");
        System.out.println("6. Change national code");
        System.out.println("7. Back");
        System.out.println("*************************");
        System.out.print("\r\nPlease enter your choice: ");
        System.out.println(ANSI_RESET);
    }

    public static User read() {
        String name = ScannerWrapper.readString("Enter name: ");
        String username = ScannerWrapper.readString("Enter username: ");
        String password = ScannerWrapper.readString("Enter password: ");
        String email = ScannerWrapper.readString("Enter email: ");
        String phoneNumber = ScannerWrapper.readString("Enter phone number: ");
        String nationalCode = ScannerWrapper.readString("Enter national code: ");

        return new User(name, username, password, email, phoneNumber, nationalCode);
    }

    public User deepCopy() {
        return new User(name, username, password, email, phoneNumber, nationalCode);
    }

    @Override
    public String toString() {
        return "Username : " + username;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((nationalCode == null) ? 0 : nationalCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (nationalCode == null) {
            if (other.nationalCode != null)
                return false;
        } else if (!nationalCode.equals(other.nationalCode))
            return false;
        return true;
    }
}