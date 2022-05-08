package ir.ac.kntu.logic;

import java.util.ArrayList;

import ir.ac.kntu.util.Cipher;
import ir.ac.kntu.util.ScannerWrapper;

public class Course {
    public enum CourseStatus {ACTIVE_PUBLIC, ACTIVE_PRIVATE, INACTIVE, UNDEFINED}

    private String name;

    private String institute;

    private User lecturer;

    private DateTime startDate;

    private CourseStatus status;

    private String hashedPassword;

    private String description;

    private ArrayList<User> register;

    private ArrayList<Assignment> assignments;

    public Course(String name, String institute, User lecturer, DateTime startDate, CourseStatus status, String password, String description) {
        this.name = name;
        this.institute = institute;
        this.lecturer = lecturer.deepCopy();
        this.startDate = startDate.deepCopy();
        this.status = status;
        this.hashedPassword = Cipher.sha256(password);
        this.description = description;
        register = new ArrayList<>();
        assignments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getInstitute() {
        return institute;
    }

    public User getLecturer() {
        return lecturer.deepCopy();
    }

    public DateTime getStartDate() {
        return startDate.deepCopy();
    }

    public CourseStatus getStatus() {
        return status;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<User> getRegister() {
        ArrayList<User> deepCopy = new ArrayList<>();
        for (User user : register) {
            deepCopy.add(user.deepCopy());
        }

        return deepCopy;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public boolean register(User student) {
        if (!register.contains(student)) {
            register.add(student);
            return true;
        }

        return false;
    }

    public boolean unregister(User student) {
        if (register.contains(student)) {
            register.remove(student);
            return true;
        }

        return false;
    }

    public boolean addAssignment(Assignment assignment) {
        if (!assignments.contains(assignment)) {
            assignments.add(assignment);
            return true;
        }

        return false;
    }

    public boolean removeAssignment(Assignment assignment) {
        if (assignments.contains(assignment)) {
            assignments.remove(assignment);
            return true;
        }

        return false;
    }

    public void changeHandler() {
        Options.CourseChangeMenuOption option;
        do {
            option = ScannerWrapper.readEnum(Options.CourseChangeMenuOption.values(), Options.Color.YELLOW.getCode());
            handleTheOption(option);
        } while (option != Options.CourseChangeMenuOption.BACK);
    }

    public void handleTheOption(Options.CourseChangeMenuOption option) {
        switch (option) {
            case CHANGE_NAME -> this.name = ScannerWrapper.readString("Enter new name: ");
            case CHANGE_INSTITUTE -> this.institute = ScannerWrapper.readString("Enter new institute: ");
            case CHANGE_LECTURER -> this.lecturer = User.readUser("Enter new lecturer: ");
            case CHANGE_START_DATE -> this.startDate = DateTime.readDate("Enter new start date: ");
            case CHANGE_DESCRIPTION -> this.description = ScannerWrapper.readString("Enter new description: ");
            case CHANGE_STATUS -> this.status = ScannerWrapper.readEnum(CourseStatus.values());
            case CHANGE_PASSWORD -> {
                String oldPassword = ScannerWrapper.readPassword("Enter old password: ");
                assert oldPassword != null;
                if (Cipher.sha256(oldPassword).equals(hashedPassword)) {
                    String newPassword = ScannerWrapper.readPassword("Enter new password: ");
                    assert newPassword != null;
                    this.hashedPassword = Cipher.sha256(newPassword);
                } else {
                    System.out.println("Wrong password");
                }
            }
            case BACK -> {
            }
            default -> System.out.println("Invalid option!");
        }
    }

    public static Course readCourse(User lecturer, String massage) {
        System.out.println(massage);

        String name = ScannerWrapper.readString("Enter name: ");
        String institute = ScannerWrapper.readString("Enter institute: ");
        DateTime startDate = DateTime.readDate("Enter start date");
        CourseStatus status = ScannerWrapper.readEnum(CourseStatus.values(), Options.Color.RESET.getCode(), "Enter status: ");
        String password = "";
        if (status == CourseStatus.ACTIVE_PRIVATE) {
            password = ScannerWrapper.readPassword("Enter password: ");
        }
        if (password == null) {
            return null;
        }
        String description = ScannerWrapper.readString("Enter description: ");

        return new Course(name, institute, lecturer, startDate, status, password, description);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", institute='" + institute + '\'' +
                ", lecturer=" + lecturer +
                ", startDate=" + startDate +
                ", status=" + status +
                ", description='" + description + '\'' +
                "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((institute == null) ? 0 : institute.hashCode());
        result = prime * result + ((lecturer == null) ? 0 : lecturer.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Course other = (Course) obj;
        if (institute == null) {
            if (other.institute != null) {
                return false;
            }
        } else if (!institute.equals(other.institute)) {
            return false;
        }
        if (lecturer == null) {
            if (other.lecturer != null) {
                return false;
            }
        } else if (!lecturer.equals(other.lecturer)) {
            return false;
        }
        if (name == null) {
            return other.name == null;
        } else {
            return name.equals(other.name);
        }
    }

}