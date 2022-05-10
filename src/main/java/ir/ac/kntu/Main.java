package ir.ac.kntu;

import ir.ac.kntu.logic.User;
import ir.ac.kntu.logic.Course;
import ir.ac.kntu.logic.Question;
import ir.ac.kntu.logic.Options.*;

import ir.ac.kntu.util.Cipher;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static User currentUser = null;

    public static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<Course> courses = new ArrayList<>();

    public static ArrayList<Question> bankOfQuestions = new ArrayList<>();

    public static void main(String[] args) {
        LoginMenu option;
        do {
            option = ScannerWrapper.readEnum(LoginMenu.values(), Color.RED.getCode());
            handleTheLoginMenu(option);
        } while (option != LoginMenu.EXIT);

        ScannerWrapper.close();
    }

    public static void handleTheLoginMenu(LoginMenu loginMenuOption) {
        switch (loginMenuOption) {
            case SIGN_IN -> {
                if (!signIn()) {
                    break;
                }
                MainMenu option;
                do {
                    option = ScannerWrapper.readEnum(MainMenu.values(), Color.BLUE.getCode());
                    handleTheMainMenu(option);
                } while (option != MainMenu.LOGOUT);
            }
            case SIGN_UP -> {
                if (!signUp()) {
                    break;
                }
                MainMenu option;
                do {
                    option = ScannerWrapper.readEnum(MainMenu.values(), Color.BLUE.getCode());
                    handleTheMainMenu(option);
                } while (option != MainMenu.LOGOUT);
            }
            case EXIT -> {
            }
            default -> {}
        }
    }

    public static boolean signIn() {
        if (users.size() == 0) {
            System.out.println("No users in the system");
            return false;
        }

        String username = ScannerWrapper.readString("username: ");
        String passToHash = ScannerWrapper.readPassword("Password: ");
        if (passToHash == null) {
            return false;
        }

        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getHashedPassword().equals(Cipher.sha256(passToHash))) {
                currentUser = user;
                return true;
            }
        }
        System.out.println("Invalid username or password");
        return false;
    }

    public static boolean signUp() {
        User user = User.readUser("Enter your attributes");
        if (user == null) {
            System.out.println("Sign up process failed");
            return false;
        }
        currentUser = user;
        users.add(user);
        return true;
    }

    public static void handleTheMainMenu(MainMenu mainMenuOption) {
        switch (mainMenuOption) {
            case COURSES -> courses();
            case SEARCH -> search();
            case BANK_OF_QUESTIONS -> questionBank();
            case ACCOUNT -> account();
            case LOGOUT -> logout();
            default -> {}
        }
    }

    public static void courses() {
        enum Option {ADD, REMOVE, LIST, REGISTER_TO_COURSE, BACK}

        Option option = ScannerWrapper.readEnum(Option.values());
        switch (option) {
            case ADD -> addCourse();
            case REMOVE -> removeCourse();
            case LIST -> {
                enum List {COURSES_IM_LECTURER, COURSES_IM_STUDENT}

                List list = ScannerWrapper.readEnum(List.values());
                switch (list) {
                    case COURSES_IM_LECTURER -> {
                        Integer index = getCourseIndexImLecturer();
                        if (index == null) {
                            break;
                        }
                        courses.get(index).lecturerHandler();
                    }
                    case COURSES_IM_STUDENT -> {
                        Integer index = getCourseIndexImStudent();
                        if (index == null) {
                            break;
                        }
                        courses.get(index).studentHandler();
                    }
                    default -> {}
                }
            }
            case REGISTER_TO_COURSE -> registerToCourse();
            case BACK -> {
                return;
            }
            default -> {}
        }
        courses();
    }

    private static void removeCourse() {
        Course course = searchCourse();
        if (course == null) {
            return;
        }
        if (course.getLecturer().equals(currentUser)) {
            courses.remove(course);
            System.out.println("Course removed successfully");
        } else {
            System.out.println("You are not the lecturer of this course");
        }
    }

    private static void registerToCourse() {
        Course course = searchCourse();
        if (course != null) {
            if (course.getRegister().contains(currentUser)) {
                System.out.println("You are already registered to this course");
            }
            if (course.getStatus() == Course.CourseStatus.CLOSE) {
                System.out.println("Course is closed");
            } else if (course.getStatus() == Course.CourseStatus.OPEN_PRIVATE) {
                String passToHash = ScannerWrapper.readPassword("Password: ");
                if (!Cipher.sha256(passToHash).equals(course.getHashedPassword())) {
                    System.out.println("Invalid password");
                    return;
                }
            }
            course.register(currentUser);
            System.out.println("Successfully registered to the course");
        }
    }

    private static Integer getCourseIndexImStudent() {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getRegister().contains(currentUser)) {
                System.out.println(i + 1 + ". " + courses.get(i).toString());
            }
        }
        int index = ScannerWrapper.readInt("Enter the course number: ") - 1;
        if (index < 0 || index >= courses.size()) {
            System.out.println("Invalid index");
            return null;
        }
        return index;
    }

    private static Integer getCourseIndexImLecturer() {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getLecturer().equals(currentUser)) {
                System.out.println(i + 1 + ". " + courses.get(i).toString());
            }
        }
        int index = ScannerWrapper.readInt("Enter the course number: ") - 1;
        if (index < 0 || index >= courses.size()) {
            System.out.println("Invalid index");
            return null;
        }
        return index;
    }

    private static void addCourse() {
        Course course = Course.readCourse(currentUser, "Enter your course");
        if (course == null) {
            System.out.println("Adding course failed");
            return;
        }
        courses.add(course);
    }

    public static void search() {
        enum Option {SEARCH_USER, SEARCH_COURSE, BACK}

        Option option = ScannerWrapper.readEnum(Option.values());
        switch (option) {
            case SEARCH_USER -> System.out.println(searchUser());
            case SEARCH_COURSE -> System.out.println(searchCourse());
            case BACK -> {
                return;
            }
            default -> {}
        }
        search();
    }

    public static Course searchCourse() {
        String choice = ScannerWrapper.readString("Search by name, lecturer or institute? (n/l/i)");
        switch (choice) {
            case "n" -> {
                String name = ScannerWrapper.readString("Enter name: ");
                for (Course course : courses) {
                    if (course.getName().equals(name)) {
                        return course;
                    }
                }
                System.out.println("Course not found");
                return null;
            }
            case "l" -> {
                String lecturer = ScannerWrapper.readString("Enter lecturer: ");
                for (Course course : courses) {
                    if (course.getLecturer().getUsername().equals(lecturer)) {
                        return course;
                    }
                }
                System.out.println("Course not found");
                return null;
            }
            case "i" -> {
                String institute = ScannerWrapper.readString("Enter institute: ");
                for (Course course : courses) {
                    if (course.getInstitute().equals(institute)) {
                        return course;
                    }
                }
                System.out.println("Course not found");
                return null;
            }
            default -> {
                System.out.println("Invalid choice!");
                return null;
            }
        }
    }

    public static User searchUser() {
        String choice = ScannerWrapper.readString("Search by username or email? (u/e)");
        switch (choice) {
            case "u" -> {
                String username = ScannerWrapper.readString("Enter username: ");
                for (User user : users) {
                    if (user.getUsername().equals(username)) {
                        return user;
                    }
                }
                System.out.println("User not found");
                return null;
            }
            case "e" -> {
                String email = ScannerWrapper.readString("Enter email: ");
                for (User user : users) {
                    if (user.getEmail().equals(email)) {
                        return user;
                    }
                }
                System.out.println("User not found");
                return null;
            }
            default -> {
                System.out.println("Invalid choice!");
                return null;
            }
        }
    }

    public static Question searchQuestion() {
        String questionName = ScannerWrapper.readString("Enter question name: ");
        for (Question question : bankOfQuestions) {
            if (question.getName().equals(questionName)) {
                return question;
            }
        }
        System.out.println("Question not found");
        return null;
    }

    public static void questionBank() {
        enum Option {ADD, LIST, BACK}

        Option option = ScannerWrapper.readEnum(Option.values());
        switch (option) {
            case ADD -> addQuestionToBank();
            case LIST -> {
                sortBank();
                Integer index = getQuestionIndex();
                if (index == null) {
                    break;
                }
                bankOfQuestions.get(index).studentHandler(currentUser);
            }
            case BACK -> {
                return;
            }
            default -> {}
        }
        questionBank();
    }

    private static Integer getQuestionIndex() {
        for (int i = 0; i < bankOfQuestions.size(); i++) {
            System.out.println(i + 1 + ". " + bankOfQuestions.get(i).toString());
        }
        int index = ScannerWrapper.readInt("Enter the question number: ") - 1;
        if (index < 0 || index >= bankOfQuestions.size()) {
            System.out.println("Invalid index");
            return null;
        }
        return index;
    }

    private static void sortBank() {
        enum SortBy {UPLOAD_TIME, DIFFICULTY, LIST}

        SortBy sortBy = ScannerWrapper.readEnum(SortBy.values());
        switch (sortBy) {
            case UPLOAD_TIME, LIST -> bankOfQuestions.sort(Comparator.comparing(Question::getUploadDateTime));
            case DIFFICULTY -> bankOfQuestions.sort(Comparator.comparing(Question::getLevel));
            default -> {}
        }
    }

    private static void addQuestionToBank() {
        Question question = Question.readQuestion("Enter your question");
        if (question == null) {
            System.out.println("Adding question failed");
            return;
        }
        bankOfQuestions.add(question);
    }

    public static void account() {
        enum Option {EDIT, BACK}

        Option option = ScannerWrapper.readEnum(Option.values());
        switch (option) {
            case EDIT -> currentUser.changeHandler();
            case BACK -> {
                return;
            }
            default -> {}
        }
        account();
    }

    public static void logout() {
        currentUser = null;
    }

}