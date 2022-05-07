package ir.ac.kntu;

import ir.ac.kntu.logic.Graphics;
import ir.ac.kntu.logic.Options;
import ir.ac.kntu.logic.*;

public class Main {

    public static void main(String[] args) {
        Courses courses = new Courses();

        Options.LoginMenuOption option;
        do {
            option = Graphics.scanTheOption(Options.LoginMenuOption.values(), Graphics.Color.RED);
            handleTheLoginMenuOption(courses, option);
        } while (option != Options.LoginMenuOption.EXIT);
    }

    public static void handleTheLoginMenuOption(Courses courses, Options.LoginMenuOption loginMenuOption) {
        switch (loginMenuOption) {
            case SIGN_UP: {
                if (!courses.signUp()) {
                    break;
                }

                Options.MainMenuOption option;
                do {
                    option = Graphics.scanTheOption(Options.MainMenuOption.values(), Graphics.Color.BLUE);
                    handleTheMainMenuOption(courses, option);
                } while (option != Options.MainMenuOption.BACK && option != Options.MainMenuOption.LOGOUT);

                break;
            }
            case LOGIN: {
                if (!courses.login()) {
                    break;
                }

                Options.MainMenuOption option;
                do {
                    option = Graphics.scanTheOption(Options.MainMenuOption.values(), Graphics.Color.BLUE);
                    handleTheMainMenuOption(courses, option);
                } while (option != Options.MainMenuOption.BACK && option != Options.MainMenuOption.LOGOUT);

                break;
            }
            case UNDEFINED:
                System.out.println("Invalid option!");
                break;
            default:
                break;
        }
    }

    public static void handleTheMainMenuOption(Courses courses, Options.MainMenuOption mainMenuOption) {
        switch (mainMenuOption) {
            case SEARCH: {
                Options.SearchMenuOption option;
                do {
                    option = Graphics.scanTheOption(Options.SearchMenuOption.values(), Graphics.Color.GREEN);
                    handleTheSearchMenuOption(courses, option);
                } while (option != Options.SearchMenuOption.BACK);
            }
            break;
            case ADD: {
                Options.AddMenuOption option;
                do {
                    option = Graphics.scanTheOption(Options.AddMenuOption.values(), Graphics.Color.GREEN);
                    handleTheAddMenuOption(courses, option);
                } while (option != Options.AddMenuOption.BACK);
            }
            break;
            case CHANGE: {
                Options.ChangeMenuOption option;
                do {
                    option = Graphics.scanTheOption(Options.ChangeMenuOption.values(), Graphics.Color.GREEN);
                    handleTheChangeMenuOption(courses, option);
                } while (option != Options.ChangeMenuOption.BACK);
            }
            break;
            case REMOVE: {
                Options.RemoveMenuOption option;
                do {
                    option = Graphics.scanTheOption(Options.RemoveMenuOption.values(), Graphics.Color.GREEN);
                    handleTheRemoveMenuOption(courses, option);
                } while (option != Options.RemoveMenuOption.BACK);
            }
            break;
            case LIST_OF: {
                Options.ListOfMenuOption option;
                do {
                    option = Graphics.scanTheOption(Options.ListOfMenuOption.values(), Graphics.Color.GREEN);
                    handleTheListOfMenuOption(courses, option);
                } while (option != Options.ListOfMenuOption.BACK);
            }
            break;
            case OTHER: {
                Options.OtherMenuOption option;
                do {
                    option = Graphics.scanTheOption(Options.OtherMenuOption.values(), Graphics.Color.GREEN);
                    handleTheOtherMenuOption(courses, option);
                } while (option != Options.OtherMenuOption.BACK);
            }
            break;
            case LOGOUT:
                courses.logout();
                break;
            case UNDEFINED:
                System.out.println("Invalid option!");
            default:
                break;
        }
    }

    public static void handleTheSearchMenuOption(Courses courses, Options.SearchMenuOption searchMenuOption) {
        switch (searchMenuOption) {
            case SEARCH_USER:
                User user = courses.searchUser();
                if (user != null) {
                    System.out.println(user);
                }
                break;
            case SEARCH_COURSE:
                Course course = courses.searchCourse();
                if (course != null) {
                    System.out.println(course);
                }
                break;
            case SEARCH_ANSWERS_BY_EMAIL:
                courses.searchAnswersByEmail();
                break;
            case UNDEFINED:
                System.out.println("Invalid option!");
                break;
            default:
                break;
        }
    }

    public static void handleTheAddMenuOption(Courses courses, Options.AddMenuOption addMenuOption) {
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
            case UNDEFINED:
                System.out.println("Invalid option!");
                break;
            default:
                break;
        }
    }

    public static void handleTheChangeMenuOption(Courses courses, Options.ChangeMenuOption changeMenuOption) {
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
            case UNDEFINED:
                System.out.println("Invalid option!");
                break;
            default:
                break;
        }
    }

    public static void handleTheRemoveMenuOption(Courses courses, Options.RemoveMenuOption removeMenuOption) {
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
            case UNDEFINED:
                System.out.println("Invalid option!");
                break;
            default:
                break;
        }
    }

    public static void handleTheListOfMenuOption(Courses courses, Options.ListOfMenuOption listOfMenuOption) {
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
            case UNDEFINED:
                System.out.println("Invalid option!");
                break;
            default:
                break;
        }
    }

    public static void handleTheOtherMenuOption(Courses courses, Options.OtherMenuOption otherMenuOption) {
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
            case POINTING:
                courses.pointing();
                break;
            case UNDEFINED:
                System.out.println("Invalid option!");
                break;
            default:
                break;
        }
    }

}