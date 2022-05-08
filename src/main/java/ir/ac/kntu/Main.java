package ir.ac.kntu;

import ir.ac.kntu.logic.*;
import ir.ac.kntu.util.ScannerWrapper;

public class Main {
    public static void main(String[] args) {
        Courses courses = new Courses();

        Options.LoginMenuOption option;
        do {
            option = ScannerWrapper.readEnum(Options.LoginMenuOption.values(), Options.Color.RED.getCode());
            handleTheLoginMenuOption(courses, option);
        } while (option != Options.LoginMenuOption.EXIT);

        ScannerWrapper.close();
    }

    public static void handleTheLoginMenuOption(Courses courses, Options.LoginMenuOption loginMenuOption) {
        switch (loginMenuOption) {
            case SIGN_UP -> {
                if (!courses.signUp()) {
                    break;
                }
                Options.MainMenuOption option;
                do {
                    option = ScannerWrapper.readEnum(Options.MainMenuOption.values(), Options.Color.BLUE.getCode());
                    handleTheMainMenuOption(courses, option);
                } while (option != Options.MainMenuOption.BACK && option != Options.MainMenuOption.LOGOUT);
            }
            case LOGIN -> {
                if (!courses.login()) {
                    break;
                }
                Options.MainMenuOption option;
                do {
                    option = ScannerWrapper.readEnum(Options.MainMenuOption.values(), Options.Color.BLUE.getCode());
                    handleTheMainMenuOption(courses, option);
                } while (option != Options.MainMenuOption.BACK && option != Options.MainMenuOption.LOGOUT);
            }
            case UNDEFINED -> System.out.println("Invalid option!");
            default -> {}
        }
    }

    public static void handleTheMainMenuOption(Courses courses, Options.MainMenuOption mainMenuOption) {
        switch (mainMenuOption) {
            case SEARCH -> {
                Options.SearchMenuOption option;
                do {
                    option = ScannerWrapper.readEnum(Options.SearchMenuOption.values(), Options.Color.GREEN.getCode());
                    handleTheSearchMenuOption(courses, option);
                } while (option != Options.SearchMenuOption.BACK);
            }
            case ADD -> {
                Options.AddMenuOption option;
                do {
                    option = ScannerWrapper.readEnum(Options.AddMenuOption.values(), Options.Color.GREEN.getCode());
                    handleTheAddMenuOption(courses, option);
                } while (option != Options.AddMenuOption.BACK);
            }
            case CHANGE -> {
                Options.ChangeMenuOption option;
                do {
                    option = ScannerWrapper.readEnum(Options.ChangeMenuOption.values(), Options.Color.GREEN.getCode());
                    handleTheChangeMenuOption(courses, option);
                } while (option != Options.ChangeMenuOption.BACK);
            }
            case REMOVE -> {
                Options.RemoveMenuOption option;
                do {
                    option = ScannerWrapper.readEnum(Options.RemoveMenuOption.values(), Options.Color.GREEN.getCode());
                    handleTheRemoveMenuOption(courses, option);
                } while (option != Options.RemoveMenuOption.BACK);
            }
            case LIST_OF -> {
                Options.ListOfMenuOption option;
                do {
                    option = ScannerWrapper.readEnum(Options.ListOfMenuOption.values(), Options.Color.GREEN.getCode());
                    handleTheListOfMenuOption(courses, option);
                } while (option != Options.ListOfMenuOption.BACK);
            }
            case OTHER -> {
                Options.OtherMenuOption option;
                do {
                    option = ScannerWrapper.readEnum(Options.OtherMenuOption.values(), Options.Color.GREEN.getCode());
                    handleTheOtherMenuOption(courses, option);
                } while (option != Options.OtherMenuOption.BACK);
            }
            case LOGOUT -> courses.logout();
            case UNDEFINED -> System.out.println("Invalid option!");
            default -> {}
        }
    }

    public static void handleTheSearchMenuOption(Courses courses, Options.SearchMenuOption searchMenuOption) {
        switch (searchMenuOption) {
            case SEARCH_USER -> {
                User user = courses.searchUser();
                if (user != null) {
                    System.out.println(user);
                }
            }
            case SEARCH_COURSE -> {
                Course course = courses.searchCourse();
                if (course != null) {
                    System.out.println(course);
                }
            }
            case SEARCH_ANSWERS_BY_EMAIL -> courses.searchAnswersByEmail();
            case UNDEFINED -> System.out.println("Invalid option!");
            default -> {}
        }
    }

    public static void handleTheAddMenuOption(Courses courses, Options.AddMenuOption addMenuOption) {
        switch (addMenuOption) {
            case ADD_USER -> courses.addUser();
            case ADD_COURSE -> courses.addCourse();
            case ADD_QUESTION_TO_ASSIGNMENT_FROM_QUESTION_BANK -> courses.addQuestionToAssignmentFromQuestionBank();
            case ADD_QUESTION_TO_QUESTION_BANK -> courses.addQuestionToQuestionBank();
            case ADD_ASSIGNMENT -> courses.addAssignment();
            case ADD_ANSWER -> courses.addAnswer();
            case UNDEFINED -> System.out.println("Invalid option!");
            default -> {}
        }
    }

    public static void handleTheChangeMenuOption(Courses courses, Options.ChangeMenuOption changeMenuOption) {
        switch (changeMenuOption) {
            case CHANGE_USER -> courses.changeUser();
            case CHANGE_COURSE -> courses.changeCourse();
            case CHANGE_QUESTION -> courses.changeQuestion();
            case CHANGE_ASSIGNMENT -> courses.changeAssignment();
            case CHANGE_ANSWER -> courses.changeAnswer();
            case UNDEFINED -> System.out.println("Invalid option!");
            default -> {}
        }
    }

    public static void handleTheRemoveMenuOption(Courses courses, Options.RemoveMenuOption removeMenuOption) {
        switch (removeMenuOption) {
            case REMOVE_USER -> courses.removeUser();
            case REMOVE_COURSE -> courses.removeCourse();
            case REMOVE_QUESTION -> courses.removeQuestion();
            case REMOVE_ASSIGNMENT -> courses.removeAssignment();
            case REMOVE_ANSWER -> courses.removeAnswer();
            case UNDEFINED -> System.out.println("Invalid option!");
            default -> {}
        }
    }

    public static void handleTheListOfMenuOption(Courses courses, Options.ListOfMenuOption listOfMenuOption) {
        switch (listOfMenuOption) {
            case LIST_OF_USERS -> courses.listOfUsers();
            case LIST_OF_COURSES -> courses.listOfCourses();
            case LIST_OF_ASSIGNMENTS -> courses.listOfAssignments();
            case LIST_OF_ANSWERS -> courses.listOfAnswers();
            case QUESTION_BANK -> courses.listQuestionBank();
            case LIST_OF_SEND_ANSWERS -> courses.listOfSentAnswers();
            case LIST_OF_FINAL_SENDS -> courses.listOfFinalSentAnswers();
            case LIST_OF_FINAL_SENDS_AND_POINTING -> courses.listOfFinalSentAndPointing();
            case UNDEFINED -> System.out.println("Invalid option!");
            default -> {}
        }
    }

    public static void handleTheOtherMenuOption(Courses courses, Options.OtherMenuOption otherMenuOption) {
        switch (otherMenuOption) {
            case REGISTER_TO_COURSE -> courses.registerToCourse();
            case REGISTER_STUDENT_TO_COURSE -> courses.registerStudentToCourse();
            case SCORE_BOARD -> courses.scoreBoard();
            case POINTING -> courses.pointing();
            case UNDEFINED -> System.out.println("Invalid option!");
            default -> {}
        }
    }

}