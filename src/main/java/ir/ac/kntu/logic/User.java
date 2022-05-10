package ir.ac.kntu.logic;

import ir.ac.kntu.util.Cipher;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.logic.Options.Color;

public class User {
    public enum UserChangeMenu {
        CHANGE_FIRST_NAME, CHANGE_USERNAME, CHANGE_PASSWORD,
        CHANGE_EMAIL, CHANGE_PHONE_NUMBER, CHANGE_NATIONAL_CODE, BACK
    }

    private String firstName;

    private String username;

    private String hashedPassword;

    private String email;

    private String phoneNumber;

    private String nationalCode;

    public User(String firstName, String username, String password, String email, String phoneNumber, String nationalCode) {
        this.firstName = firstName;
        this.username = username;
        this.hashedPassword = Cipher.sha256(password);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
    }

    public String getFirstName() {
        return firstName;
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
        UserChangeMenu option;
        do {
            option = ScannerWrapper.readEnum(UserChangeMenu.values(), Color.YELLOW.getCode());
            handleTheOption(option);
        } while (option != UserChangeMenu.BACK);
    }

    private void handleTheOption(UserChangeMenu option) {
        switch (option) {
            case CHANGE_FIRST_NAME -> this.firstName = ScannerWrapper.readString("Enter new firstName: ");
            case CHANGE_USERNAME -> this.username = ScannerWrapper.readString("Enter new username: ");
            case CHANGE_PASSWORD -> {
                String oldPassToHash = ScannerWrapper.readPassword("Enter old password: ");
                if (oldPassToHash == null) {
                    break;
                }
                if (Cipher.sha256(oldPassToHash).equals(hashedPassword)) {
                    String newPassToHash = ScannerWrapper.readPassword("Enter new password: ");
                    if (newPassToHash == null) {
                        break;
                    }
                    this.hashedPassword = Cipher.sha256(newPassToHash);
                } else {
                    System.out.println("Wrong password");
                }
            }
            case CHANGE_EMAIL -> this.email = ScannerWrapper.readString("Enter new email: ");
            case CHANGE_PHONE_NUMBER -> this.phoneNumber = ScannerWrapper.readString("Enter new phone number: ");
            case CHANGE_NATIONAL_CODE -> this.nationalCode = ScannerWrapper.readString("Enter new national code: ");
            case BACK -> {
            }
            default -> {}
        }
    }

    public static User readUser(String massage) {
        System.out.println(massage);
        String firstName = ScannerWrapper.readString("Enter firstname: ");
        String userName = ScannerWrapper.readString("Enter username: ");
        String passToHash = ScannerWrapper.readPassword("Enter password: ");
        if (passToHash == null) {
            return null;
        }
        String email = ScannerWrapper.readString("Enter email: ");
        String phoneNumber = ScannerWrapper.readString("Enter phone number: ");
        String nationalCode = ScannerWrapper.readString("Enter national code: ");

        return new User(firstName, userName, passToHash, email, phoneNumber, nationalCode);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + firstName + '\'' +
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
            return other.nationalCode == null;
        } else {
            return nationalCode.equals(other.nationalCode);
        }
    }

}