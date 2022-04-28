package ir.ac.kntu;

import ir.ac.kntu.util.ScannerWrapper;

public class Main {
    enum LoginMenuOption {
        SIGN_UP, LOGIN, EXIT, UNDEFINED
    }

    enum MainMenuOption {
        SEARCH, ADD, CHANGE, REMOVE, LIST_OF, OTHER, LOGOUT, BACK, UNDEFINED
    }

    enum SearchMenuOption {
        SEARCH_USER, SEARCH_COURSE, SEARCH_ANSWERS_BY_EMAIL, BACK, UNDEFINED
    }

    enum AddMenuOption {
        ADD_USER, ADD_COURSE, ADD_QUESTION_TO_ASSIGNMENT_FROM_QUESTION_BANK,
        ADD_QUESTION_TO_QUESTION_BANK, ADD_ASSIGNMENT, ADD_ANSWER, BACK, UNDEFINED
    }

    enum ChangeMenuOption {
        CHANGE_USER, CHANGE_COURSE, CHANGE_QUESTION, CHANGE_ASSIGNMENT, CHANGE_ANSWER, BACK, UNDEFINED
    }

    enum RemoveMenuOption {
        REMOVE_USER, REMOVE_COURSE, REMOVE_QUESTION, REMOVE_ASSIGNMENT, REMOVE_ANSWER, BACK, UNDEFINED
    }

    enum ListOfMenuOption {
        LIST_OF_USERS, LIST_OF_COURSES, LIST_OF_ASSIGNMENTS, LIST_OF_ANSWERS, QUESTION_BANK,
        LIST_OF_SEND_ANSWERS, LIST_OF_FINAL_SENDS, LIST_OF_FINAL_SENDS_AND_POINTING,
        BACK, UNDEFINED
    }

    enum OtherMenuOption {
        REGISTER_TO_COURSE, REGISTER_STUDENT_TO_COURSE, SCORE_BOARD, BACK, UNDEFINED
    }

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_BLUE = "\u001B[34m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        Courses courses = new Courses();

        LoginMenuOption option;
        do {
            option = scanLoginMenuOption();
            handleTheLoginMenuOption(courses, option);
        } while (option != LoginMenuOption.EXIT);
    }

    public static void handleTheLoginMenuOption(Courses courses, LoginMenuOption loginMenuOption) {
        switch (loginMenuOption) {
            case SIGN_UP: {
                if (!courses.signUp()) {
                    break;
                }
                MainMenuOption option;
                do {
                    option = scanMainMenuOption();
                    handleTheMainMenuOption(courses, option);
                } while (option != MainMenuOption.BACK && option != MainMenuOption.LOGOUT);

                break;
            }
            case LOGIN: {
                if (!courses.login()) {
                    break;
                }
                MainMenuOption option;
                do {
                    option = scanMainMenuOption();
                    handleTheMainMenuOption(courses, option);
                } while (option != MainMenuOption.BACK && option != MainMenuOption.LOGOUT);

                break;
            }
            case EXIT:
                break;
            case UNDEFINED:
                System.out.println("Invalid option");
                break;
        }
    }

    public static void handleTheMainMenuOption(Courses courses, MainMenuOption mainMenuOption) {
        switch (mainMenuOption) {
            case SEARCH: {
                SearchMenuOption option;
                do {
                    option = scanSearchMenuOption();
                    handleTheSearchMenuOption(courses, option);
                } while (option != SearchMenuOption.BACK);

                break;
            }
            case ADD: {
                AddMenuOption option;
                do {
                    option = scanAddMenuOption();
                    handleTheAddMenuOption(courses, option);
                } while (option != AddMenuOption.BACK);

                break;
            }
            case CHANGE: {
                ChangeMenuOption option;
                do {
                    option = scanChangeMenuOption();
                    handleTheChangeMenuOption(courses, option);
                } while (option != ChangeMenuOption.BACK);

                break;
            }
            case REMOVE: {
                RemoveMenuOption option;
                do {
                    option = scanRemoveMenuOption();
                    handleTheRemoveMenuOption(courses, option);
                } while (option != RemoveMenuOption.BACK);

                break;
            }
            case LIST_OF: {
                ListOfMenuOption option;
                do {
                    option = scanListOfMenuOption();
                    handleTheListOfMenuOption(courses, option);
                } while (option != ListOfMenuOption.BACK);

                break;
            }
            case OTHER: {
                OtherMenuOption option;
                do {
                    option = scanOtherMenuOption();
                    handleTheOtherMenuOption(courses, option);
                } while (option != OtherMenuOption.BACK);

                break;
            }
            case LOGOUT:
                courses.logout();
                break;
            case BACK:
                break;
            case UNDEFINED: {
                System.out.println("Invalid option");
                break;
            }
        }
    }

    public static void handleTheSearchMenuOption(Courses courses, SearchMenuOption searchMenuOption) {
        switch (searchMenuOption) {
            case SEARCH_USER:
                courses.searchUser();
                break;
            case SEARCH_COURSE:
                courses.searchCourse();
                break;
            case SEARCH_ANSWERS_BY_EMAIL:
                courses.searchAnswersByEmail();
                break;
            case BACK:
                break;
            case UNDEFINED:
                System.out.println("Invalid option");
                break;
        }
    }

    public static void handleTheAddMenuOption(Courses courses, AddMenuOption addMenuOption) {
        switch (addMenuOption) {
            case ADD_USER:
                courses.addUser();
                break;
            case ADD_COURSE:
                courses.addCourse();
                break;
            case ADD_QUESTION_TO_ASSIGNMENT_FROM_QUESTION_BANK:
                courses.addQuestionToAssignmentFromQuestionBank();
                break;
            case ADD_QUESTION_TO_QUESTION_BANK:
                courses.addQuestionToQuestionBank();
                break;
            case ADD_ASSIGNMENT:
                courses.addAssignment();
                break;
            case ADD_ANSWER:
                courses.addAnswer();
                break;
            case BACK:
                break;
            case UNDEFINED:
                System.out.println("Invalid option");
                break;
        }
    }

    public static void handleTheChangeMenuOption(Courses courses, ChangeMenuOption changeMenuOption) {
        switch (changeMenuOption) {
            case CHANGE_USER:
                courses.changeUser();
                break;
            case CHANGE_COURSE:
                courses.changeCourse();
                break;
            case CHANGE_ASSIGNMENT:
                courses.changeAssignment();
                break;
            case CHANGE_ANSWER:
                courses.changeAnswer();
                break;
            case BACK:
                break;
            case UNDEFINED:
                System.out.println("Invalid option");
                break;
        }
    }

    public static void handleTheRemoveMenuOption(Courses courses, RemoveMenuOption removeMenuOption) {
        switch (removeMenuOption) {
            case REMOVE_USER:
                courses.removeUser();
                break;
            case REMOVE_COURSE:
                courses.removeCourse();
                break;
            case REMOVE_ASSIGNMENT:
                courses.removeAssignment();
                break;
            case REMOVE_ANSWER:
                courses.removeAnswer();
                break;
            case BACK:
                break;
            case UNDEFINED:
                System.out.println("Invalid option");
                break;
        }
    }

    public static void handleTheListOfMenuOption(Courses courses, ListOfMenuOption listOfMenuOption) {
        switch (listOfMenuOption) {
            case LIST_OF_USERS:
                courses.listOfUsers();
                break;
            case LIST_OF_COURSES:
                courses.listOfCourses();
                break;
            case LIST_OF_ASSIGNMENTS:
                courses.listOfAssignments();
                break;
            case LIST_OF_ANSWERS:
                courses.listOfAnswers();
                break;
            case BACK:
                break;
            case UNDEFINED:
                System.out.println("Invalid option");
                break;
        }
    }

    public static void handleTheOtherMenuOption(Courses courses, OtherMenuOption otherMenuOption) {
        switch (otherMenuOption) {
            case REGISTER_TO_COURSE:
                courses.registerToCourse();
                break;
            case REGISTER_STUDENT_TO_COURSE:
                courses.registerStudentToCourse();
                break;
            case SCORE_BOARD:
                courses.scoreBoard();
                break;
            case BACK:
                break;
            case UNDEFINED:
                System.out.println("Invalid option");
                break;
        }
    }

    public static LoginMenuOption scanLoginMenuOption() {
        printTheLoginMenu();
        LoginMenuOption[] options = LoginMenuOption.values();
        int userInput = ScannerWrapper.nextInt();
        ScannerWrapper.nextLine();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return LoginMenuOption.UNDEFINED;
    }

    public static void printTheLoginMenu() {
        System.out.print(ANSI_RED);
        System.out.println("**************************************************");
        System.out.println("Courses Management System - login Menu Options:");
        System.out.println("1- Sign Up");
        System.out.println("2- Login");
        System.out.println("3- Exit");
        System.out.println("**************************************************");
        System.out.print("\r\nPlease enter your choice: ");
        System.out.print(ANSI_RESET);
    }

    public static MainMenuOption scanMainMenuOption() {
        printTheMainMenu();
        MainMenuOption[] options = MainMenuOption.values();
        int userInput = ScannerWrapper.nextInt();
        ScannerWrapper.nextLine();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return MainMenuOption.UNDEFINED;
    }

    public static void printTheMainMenu() {
        System.out.print(ANSI_BLUE);
        System.out.println("**************************************************");
        System.out.println("Courses Management System - main Menu Options:");
        System.out.println("1- Search");
        System.out.println("2- Add");
        System.out.println("3- Change");
        System.out.println("4- Remove");
        System.out.println("5- List of");
        System.out.println("6- Other");
        System.out.println("7- Logout");
        System.out.println("8- Exit");
        System.out.println("**************************************************");
        System.out.print("\r\nPlease enter your choice: ");
        System.out.print(ANSI_RESET);
    }

    public static SearchMenuOption scanSearchMenuOption() {
        printTheSearchMenu();
        SearchMenuOption[] options = SearchMenuOption.values();
        int userInput = ScannerWrapper.nextInt();
        ScannerWrapper.nextLine();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return SearchMenuOption.UNDEFINED;
    }

    public static void printTheSearchMenu() {
        System.out.print(ANSI_GREEN);
        System.out.println("**************************************************");
        System.out.println("Courses Management System - search Menu Options:");
        System.out.println("1- Search User");
        System.out.println("2- Search Course");
        System.out.println("3- Search Answers By Email");
        System.out.println("4- Back");
        System.out.println("5- Exit");
        System.out.println("**************************************************");
        System.out.print("\r\nPlease enter your choice: ");
        System.out.print(ANSI_RESET);
    }

    public static AddMenuOption scanAddMenuOption() {
        printTheAddMenu();
        AddMenuOption[] options = AddMenuOption.values();
        int userInput = ScannerWrapper.nextInt();
        ScannerWrapper.nextLine();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return AddMenuOption.UNDEFINED;
    }

    public static void printTheAddMenu() {
        System.out.print(ANSI_GREEN);
        System.out.println("**************************************************");
        System.out.println("Courses Management System - add Menu Options:");
        System.out.println("1- Add User");
        System.out.println("2- Add Course");
        System.out.println("3- Add Question to Assignment From Question Back");
        System.out.println("4- Add Question to Question Back");
        System.out.println("5- Add Assignment");
        System.out.println("6- Add Answer");
        System.out.println("7- Back");
        System.out.println("**************************************************");
        System.out.print("\r\nPlease enter your choice: ");
        System.out.print(ANSI_RESET);
    }

    public static ChangeMenuOption scanChangeMenuOption() {
        printTheChangeMenu();
        ChangeMenuOption[] options = ChangeMenuOption.values();
        int userInput = ScannerWrapper.nextInt();
        ScannerWrapper.nextLine();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return ChangeMenuOption.UNDEFINED;
    }

    public static void printTheChangeMenu() {
        System.out.print(ANSI_GREEN);
        System.out.println("**************************************************");
        System.out.println("Courses Management System - change Menu Options:");
        System.out.println("1- Change User");
        System.out.println("2- Change Course");
        System.out.println("3- Change Question");
        System.out.println("4- Change Assignment");
        System.out.println("5- Change Answer");
        System.out.println("6- Back");
        System.out.println("**************************************************");
        System.out.print("\r\nPlease enter your choice: ");
        System.out.print(ANSI_RESET);
    }

    public static RemoveMenuOption scanRemoveMenuOption() {
        printTheRemoveMenu();
        RemoveMenuOption[] options = RemoveMenuOption.values();
        int userInput = ScannerWrapper.nextInt();
        ScannerWrapper.nextLine();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return RemoveMenuOption.UNDEFINED;
    }

    public static void printTheRemoveMenu() {
        System.out.print(ANSI_GREEN);
        System.out.println("**************************************************");
        System.out.println("Courses Management System - remove Menu Options:");
        System.out.println("1- Remove User");
        System.out.println("2- Remove Course");
        System.out.println("3- Remove Question");
        System.out.println("4- Remove Assignment");
        System.out.println("5- Remove Answer");
        System.out.println("6- Back");
        System.out.println("**************************************************");
        System.out.print("\r\nPlease enter your choice: ");
        System.out.print(ANSI_RESET);
    }

    public static ListOfMenuOption scanListOfMenuOption() {
        printTheListOfMenu();
        ListOfMenuOption[] options = ListOfMenuOption.values();
        int userInput = ScannerWrapper.nextInt();
        ScannerWrapper.nextLine();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return ListOfMenuOption.UNDEFINED;
    }

    public static void printTheListOfMenu() {
        System.out.print(ANSI_GREEN);
        System.out.println("**************************************************");
        System.out.println("Courses Management System - list Menu Options:");
        System.out.println("1- List of Users");
        System.out.println("2- List of Courses");
        System.out.println("3- List of Questions");
        System.out.println("4- List of Assignments");
        System.out.println("5- List of Answers");
        System.out.println("6- Back");
        System.out.println("**************************************************");
        System.out.print("\r\nPlease enter your choice: ");
        System.out.print(ANSI_RESET);
    }

    public static OtherMenuOption scanOtherMenuOption() {
        printTheOtherMenuOption();
        OtherMenuOption[] options = OtherMenuOption.values();
        int userInput = ScannerWrapper.nextInt();
        ScannerWrapper.nextLine();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return OtherMenuOption.UNDEFINED;
    }

    public static void printTheOtherMenuOption() {
        System.out.print(ANSI_GREEN);
        System.out.println("**************************************************");
        System.out.println("Courses Management System - other Menu Options:");
        System.out.println("1- Register to Course");
        System.out.println("2- Register student to course");
        System.out.println("3- Score board");
        System.out.println("4- Back");
        System.out.println("**************************************************");
        System.out.print("\r\nPlease enter your choice: ");
        System.out.print(ANSI_RESET);
    }

}