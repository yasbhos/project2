package ir.ac.kntu.logic;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import ir.ac.kntu.util.ScannerWrapper;

public class Course {

    enum Option {
        CHANGE_NAME, CHANGE_INSTITUTE, CHANGE_LECTURER, CHANGE_START_DATE, CHANGE_DESCRIPTION,
        CHANGE_OPEN_COURSE, CHANGE_PRIVATE_COURSE, CHANGE_PASSWORD, BACK, UNDEFINED
    }

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    private User owner;

    private String name;

    private String institute;

    private String lecturer;

    private DateTime startDate;

    private String description;

    private boolean openCourse;

    private boolean privateCourse;

    private String password;

    private ArrayList<User> register;

    private Map<User, Double> marks;

    private ArrayList<Assignment> assignments;

    public Course(User owner, String name, String institute, String lecturer, DateTime startDate, String description,
                  boolean openCourse, boolean privateCourse, String password) {
        this.owner = owner.deepCopy();
        this.name = name;
        this.institute = institute;
        this.lecturer = lecturer;
        this.startDate = startDate.deepCopy();
        this.description = description;
        this.openCourse = openCourse;
        this.privateCourse = privateCourse;
        this.password = password;
        register = new ArrayList<>();
        marks = new HashMap<>();
        assignments = new ArrayList<>();
    }

    public User getOwner() {
        return owner.deepCopy();
    }

    public String getName() {
        return name;
    }

    public String getInstitute() {
        return institute;
    }

    public String getLecturer() {
        return lecturer;
    }

    public DateTime getStartDate() {
        return startDate.deepCopy();
    }

    public boolean isOpenCourse() {
        return openCourse;
    }

    public boolean isPrivateCourse() {
        return privateCourse;
    }

    public String getPassword() {
        return password;
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

    public Map<User, Double> getMarks() {
        return new HashMap<>(marks);
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

    public void changeHandler() {
        Option option;
        do {
            printTheMenu();
            option = scanOption();
            handleTheOption(option);
        } while (option != Option.BACK);
    }

    public void handleTheOption(Option option) {
        switch (option) {
            case CHANGE_NAME:
                this.name = ScannerWrapper.readString("Enter new name: ");
                break;
            case CHANGE_INSTITUTE:
                this.institute = ScannerWrapper.readString("Enter new institute: ");
                break;
            case CHANGE_LECTURER:
                this.lecturer = ScannerWrapper.readString("Enter new lecturer: ");
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
                if (ScannerWrapper.readString("Enter old password: ").equals(password)) {
                    this.password = ScannerWrapper.readString("Enter new password: ");
                } else {
                    System.out.println("Wrong password");
                }
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
        System.out.println("Course change menu: ");
        System.out.println("1. Change name");
        System.out.println("2. Change institute");
        System.out.println("3. Change lecturer");
        System.out.println("4. Change start date");
        System.out.println("5. Change description");
        System.out.println("6. Change open-course");
        System.out.println("7. Change private-course");
        System.out.println("8. Change password");
        System.out.println("9. BACK");
        System.out.println("*************************");
        System.out.print("\r\nPlease enter your choice: ");
        System.out.println(ANSI_RESET);
    }

    public static Course read(User owner) {
        String name = ScannerWrapper.readString("Enter name: ");
        String institute = ScannerWrapper.readString("Enter institute: ");
        String lecturer = ScannerWrapper.readString("Enter lecturer: ");
        System.out.println("Enter start date: ");
        DateTime startDate = DateTime.readDate();
        String description = ScannerWrapper.readString("Enter description: ");
        boolean isOpenCourse = ScannerWrapper.readBoolean("Is an open-course? (true/false): ");
        boolean isPrivateCourse = ScannerWrapper.readBoolean("Is a private-course? (true/false): ");
        ScannerWrapper.nextLine();
        String password = null;
        if (isPrivateCourse) {
            password = ScannerWrapper.readString("Enter password: ");
        }

        return new Course(owner, name, institute, lecturer, startDate, description, isOpenCourse, isPrivateCourse, password);
    }

    @Override
    public String toString() {
        return "Course{" +
                "owner: " + owner +
                ", name: '" + name + '\'' +
                ", institute: '" + institute + '\'' +
                ", lecturer: '" + lecturer + '\'' +
                ", startDate: " + startDate +
                ", openCourse: " + openCourse +
                ", privateCourse: " + privateCourse +
                '}';
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (institute == null) {
            if (other.institute != null)
                return false;
        } else if (!institute.equals(other.institute))
            return false;
        if (lecturer == null) {
            if (other.lecturer != null)
                return false;
        } else if (!lecturer.equals(other.lecturer))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}