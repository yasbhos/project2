package ir.ac.kntu.logic;

import ir.ac.kntu.util.Cipher;
import ir.ac.kntu.util.ScannerWrapper;

public class User {

    private String name;

    private String username;

    private String hashedPassword;

    private String email;

    private String phoneNumber;

    private String nationalCode;

    public User(String name, String username, String password, String email, String phoneNumber, String nationalCode) {
        this.name = name;
        this.username = username;
        this.hashedPassword = Cipher.getInstance().sha256(password);
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

    public String getHashedPassword() {
        return hashedPassword;
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
        Options.UserChangeMenuOption option;
        do {
            option = Graphics.scanTheOption(Options.UserChangeMenuOption.values(), Graphics.Color.YELLOW);
            handleTheOption(option);
        } while (option != Options.UserChangeMenuOption.BACK);
    }

    public void handleTheOption(Options.UserChangeMenuOption option) {
        switch (option) {
            case CHANGE_NAME:
                this.name = ScannerWrapper.readString("Enter new name: ");
                break;
            case CHANGE_USERNAME:
                this.username = ScannerWrapper.readString("Enter new username: ");
                break;
            case CHANGE_PASSWORD:
                String oldPassToHash = ScannerWrapper.readPassword("Enter old password: ");
                if (Cipher.getInstance().sha256(oldPassToHash).equals(hashedPassword)) {
                    String newPassToHash = ScannerWrapper.readPassword("Enter new password: ");
                    this.hashedPassword = Cipher.getInstance().sha256(newPassToHash);
                } else {
                    System.out.println("Wrong password");
                }
                break;
            case CHANGE_EMAIL:
                this.email = ScannerWrapper.readString("Enter new email: ");
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
        }
    }

    public static User read() {
        String name = ScannerWrapper.readString("Enter name: ");
        String username = ScannerWrapper.readString("Enter username: ");
        String passToHash = ScannerWrapper.readPassword("Enter password: ");
        String email = ScannerWrapper.readString("Enter email: ");
        String phoneNumber = ScannerWrapper.readString("Enter phone number: ");
        String nationalCode = ScannerWrapper.readString("Enter national code: ");

        return new User(name, username, passToHash, email, phoneNumber, nationalCode);
    }

    public User deepCopy() {
        return new User(name, username, hashedPassword, email, phoneNumber, nationalCode);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((nationalCode == null) ? 0 : nationalCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        if (nationalCode == null) {
            if (other.nationalCode != null) {
                return false;
            }
        } else if (!nationalCode.equals(other.nationalCode)) {
            return false;
        }

        return true;
    }
}