package ir.ac.kntu.logic;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import ir.ac.kntu.util.Cipher;

import ir.ac.kntu.util.ScannerWrapper;

public class Course {

    private String name;

    private String institute;

    private User lecturer;

    private DateTime startDate;

    private String description;

    private boolean openCourse;

    private boolean privateCourse;

    private String hashedPassword;

    private ArrayList<User> register;

    private Map<User, Double> marks;

    private ArrayList<Assignment> assignments;

    public Course(String name, String institute, User lecturer, DateTime startDate, String description,
                  boolean openCourse, boolean privateCourse, String password) {
        this.name = name;
        this.institute = institute;
        this.lecturer = lecturer.deepCopy();
        this.startDate = startDate.deepCopy();
        this.description = description;
        this.openCourse = openCourse;
        this.privateCourse = privateCourse;
        this.hashedPassword = Cipher.getInstance().sha256(password);
        register = new ArrayList<>();
        marks = new HashMap<>();
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

    public String getDescription() {
        return description;
    }

    public boolean isOpenCourse() {
        return openCourse;
    }

    public boolean isPrivateCourse() {
        return privateCourse;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public ArrayList<User> getRegister() {
        ArrayList<User> deepCopy = new ArrayList<>();
        for (User user : register) {
            deepCopy.add(user.deepCopy());
        }

        return deepCopy;
    }

    public Map<User, Double> getMarks() {
        Map<User, Double> deepCopy = new HashMap<>();
        for (Map.Entry<User, Double> entry : marks.entrySet()) {
            deepCopy.put(entry.getKey().deepCopy(), entry.getValue());
        }

        return deepCopy;
    }

    public void scoring(User user, double mark) {
        marks.put(user, mark);
    }

    public ArrayList<Assignment> getAssignments() {
        ArrayList<Assignment> deepCopy = new ArrayList<>();
        for (Assignment assignments : assignments) {
            deepCopy.add(assignments.deepCopy());
        }

        return deepCopy;
    }

    public boolean register(User student) {
        if (!register.contains(student)) {
            register.add(student);
            marks.put(student, 0.0);
            return true;
        }

        return false;
    }

    public boolean unregister(User student) {
        if (register.contains(student)) {
            register.remove(student);
            marks.remove(student);
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
            option = Graphics.scanTheOption(Options.CourseChangeMenuOption.values(), Graphics.Color.YELLOW);
            handleTheOption(option);
        } while (option != Options.CourseChangeMenuOption.BACK);
    }

    public void handleTheOption(Options.CourseChangeMenuOption option) {
        switch (option) {
            case CHANGE_NAME:
                this.name = ScannerWrapper.readString("Enter new name: ");
                break;
            case CHANGE_INSTITUTE:
                this.institute = ScannerWrapper.readString("Enter new institute: ");
                break;
            case CHANGE_LECTURER:
                System.out.println("Enter new lecturer: ");
                this.lecturer = User.read();
                break;
            case CHANGE_START_DATE:
                System.out.println("Enter new start date: ");
                this.startDate = DateTime.readDate();
                break;
            case CHANGE_DESCRIPTION:
                this.description = ScannerWrapper.readString("Enter new description: ");
                break;
            case CHANGE_OPEN_COURSE:
                this.openCourse = ScannerWrapper.readBoolean("Is an open-course? (true/false): ");
                break;
            case CHANGE_PRIVATE_COURSE:
                this.privateCourse = ScannerWrapper.readBoolean("Is a private-course? (true/false): ");
                break;
            case CHANGE_PASSWORD:
                String oldPassword = ScannerWrapper.readPassword("Enter old password: ");
                if (Cipher.getInstance().sha256(oldPassword).equals(hashedPassword)) {
                    String newPassword = ScannerWrapper.readPassword("Enter new password: ");
                    this.hashedPassword = Cipher.getInstance().sha256(newPassword);
                } else {
                    System.out.println("Wrong password");
                }
                break;
            case BACK:
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    public static Course read(User lecturer) {
        String name = ScannerWrapper.readString("Enter name: ");
        String institute = ScannerWrapper.readString("Enter institute: ");
        System.out.println("Enter start date: ");
        DateTime startDate = DateTime.readDate();
        String description = ScannerWrapper.readString("Enter description: ");
        boolean isOpenCourse = ScannerWrapper.readBoolean("Is an open-course? (true/false): ");
        boolean isPrivateCourse = false;
        String password = "";
        if (ScannerWrapper.readBoolean("Is a private-course? (true/false): ")) {
            isPrivateCourse = true;
            password = ScannerWrapper.readPassword("Enter password: ");
        }

        return new Course(name, institute, lecturer, startDate, description, isOpenCourse, isPrivateCourse, password);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", institute='" + institute + '\'' +
                ", lecturer=" + lecturer +
                ", startDate=" + startDate +
                ", description='" + description + '\'' +
                ", openCourse=" + openCourse +
                ", privateCourse=" + privateCourse +
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
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}