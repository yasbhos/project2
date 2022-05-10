package ir.ac.kntu.logic;

import java.util.ArrayList;

import ir.ac.kntu.Main;
import ir.ac.kntu.util.Cipher;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.logic.Options.Color;

public class Course {
    public enum CourseStatus {
        OPEN_PUBLIC, OPEN_PRIVATE, CLOSE
    }

    public enum StudentOption {
        LIST_OF_ASSIGNMENTS, BACK
    }

    public enum LecturerOption {
        CHANGE_NAME, CHANGE_INSTITUTE, CHANGE_LECTURER, CHANGE_START_DATE, CHANGE_STATUS,
        CHANGE_PASSWORD, CHANGE_DESCRIPTION, REGISTER_STUDENT_TO_COURSE, LIST_OF_ASSIGNMENTS,
        REMOVE_ASSIGNMENT, ADD_ASSIGNMENT, BACK
    }

    private String name;

    private String institute;

    private User lecturer;

    private DateTime startDate;

    private CourseStatus status;

    private String hashedPassword;

    private String description;

    private ArrayList<User> register;

    private ArrayList<Assignment> assignments;

    public Course(String name, String institute, User lecturer, DateTime startDate, CourseStatus status,
                  String password, String description) {
        this.name = name;
        this.institute = institute;
        this.lecturer = lecturer;
        this.startDate = startDate;
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
        return lecturer;
    }

    public DateTime getStartDate() {
        return startDate;
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
        return register;
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

    private boolean addAssignment(Assignment assignment) {
        return assignments.add(assignment);
    }

    private boolean removeAssignment(Assignment assignment) {
        return assignments.remove(assignment);
    }

    public void lecturerHandler() {
        LecturerOption option;
        do {
            option = ScannerWrapper.readEnum(LecturerOption.values(), Color.YELLOW.getCode());
            lecturerOptionHandler(option);
        } while (option != LecturerOption.BACK);
    }

    private void lecturerOptionHandler(LecturerOption option) {
        switch (option) {
            case CHANGE_NAME -> this.name = ScannerWrapper.readString("Enter new name: ");
            case CHANGE_INSTITUTE -> this.institute = ScannerWrapper.readString("Enter new institute: ");
            case CHANGE_LECTURER -> this.lecturer = User.readUser("Enter new lecturer: ");
            case CHANGE_START_DATE -> this.startDate = DateTime.readDate("Enter new start date: ");
            case CHANGE_STATUS -> this.status = ScannerWrapper.readEnum(CourseStatus.values());
            case CHANGE_PASSWORD -> {
                String oldPassword = ScannerWrapper.readPassword("Enter old password: ");
                if (oldPassword == null) {
                    return;
                }
                if (Cipher.sha256(oldPassword).equals(hashedPassword)) {
                    String newPassword = ScannerWrapper.readPassword("Enter new password: ");
                    if (newPassword == null) {
                        return;
                    }
                    this.hashedPassword = Cipher.sha256(newPassword);
                } else {
                    System.out.println("Wrong password");
                }
            }
            case CHANGE_DESCRIPTION -> this.description = ScannerWrapper.readString("Enter new description: ");
            case REGISTER_STUDENT_TO_COURSE -> register(Main.searchUser());
            case LIST_OF_ASSIGNMENTS -> {
                Assignment assignment = searchAssignment();
                if (assignment != null) {
                    assignment.lecturerHandler();
                }
            }
            case ADD_ASSIGNMENT -> {
                Assignment assignment = Assignment.readAssignment("Enter assignment: ");
                addAssignment(assignment);
            }
            case REMOVE_ASSIGNMENT -> removeAssignment(searchAssignment());
            case BACK -> {
            }
            default -> {}
        }
    }

    public void studentHandler() {
        StudentOption option;
        do {
            option = ScannerWrapper.readEnum(StudentOption.values(), Color.YELLOW.getCode());
            studentOptionHandler(option);
        } while (option != StudentOption.BACK);
    }

    private void studentOptionHandler(StudentOption option) {
        switch (option) {
            case LIST_OF_ASSIGNMENTS -> {
                Assignment assignment;
                for (int i = 0; i < assignments.size(); i++) {
                    assignment = assignments.get(i);
                    if (assignment.getAssignmentStatus() == Assignment.Status.ACTIVE && 
                            assignment.getStartDate().compareTo(DateTime.now()) <= 0) {
                        System.out.println(i + 1 + ". " + assignment.getName());
                    }
                }

                int index = ScannerWrapper.readInt("Enter assignment index: ");
                if (index > 0 && index <= assignments.size()) {
                    assignments.get(index - 1).studentHandler();
                } else {
                    System.out.println("Wrong index");
                }
            }
            case BACK -> {
            }
            default -> {}
        }
    }

    private Assignment searchAssignment() {
        for (int i = 0; i < assignments.size(); i++) {
            System.out.println(i + 1 + ". " + assignments.get(i).getName());
        }
        int index = ScannerWrapper.readInt("Enter assignment index: ");
        if (index > 0 && index <= assignments.size()) {
            return assignments.get(index - 1);
        }
        return null;
    }

    public static Course readCourse(User lecturer, String massage) {
        System.out.println(massage);
        String name = ScannerWrapper.readString("Enter name: ");
        String institute = ScannerWrapper.readString("Enter institute: ");
        DateTime startDate = DateTime.readDate("Enter start date");
        CourseStatus status = ScannerWrapper.readEnum(CourseStatus.values(), Color.RESET.getCode(), "Enter status: ");
        String password = null;
        if (status == CourseStatus.OPEN_PRIVATE) {
            password = ScannerWrapper.readPassword("Enter password: ");
            if (password == null) {
                return null;
            }
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