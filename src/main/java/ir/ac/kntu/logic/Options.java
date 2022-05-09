package ir.ac.kntu.logic;

public class Options {
    public enum Color {
        RED("\u001B[31m"), GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"), BLUE("\u001B[34m"),
        RESET("\u001B[0m");

        private final String code;

        Color(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public enum QuestionLevel {
        EASY, MEDIUM, HARD, VERY_HARD, UNDEFINED
    }

    public enum QuestionType {
        CHOICE_ONE, SHORT_ANSWER, LONG_ANSWER, FILL_IN_THE_BLANK, UNDEFINED
    }

    public enum LoginMenuOption {
        SIGN_UP, LOGIN, EXIT, UNDEFINED
    }

    public enum MainMenuOption {
        SEARCH, ADD, CHANGE, REMOVE, LIST_OF, OTHER, LOGOUT, BACK, UNDEFINED
    }

    public enum SearchMenuOption {
        SEARCH_USER, SEARCH_COURSE, SEARCH_ANSWERS_BY_EMAIL, BACK, UNDEFINED
    }

    public enum AddMenuOption {
        ADD_USER, ADD_COURSE, ADD_QUESTION_TO_ASSIGNMENT_FROM_QUESTION_BANK,
        ADD_QUESTION_TO_QUESTION_BANK, ADD_ASSIGNMENT, ADD_ANSWER_TO_QUESTION_OF_ASSIGNMENT,
        ADD_ANSWER_TO_QUESTION_OF_QUESTION_BACK, BACK, UNDEFINED
    }

    public enum ChangeMenuOption {
        CHANGE_USER, CHANGE_COURSE, CHANGE_ASSIGNMENT, CHANGE_ANSWER, BACK, UNDEFINED
    }

    public enum RemoveMenuOption {
        REMOVE_USER, REMOVE_COURSE, REMOVE_ASSIGNMENT, REMOVE_ANSWER, BACK, UNDEFINED
    }

    public enum ListOfMenuOption {
        LIST_OF_USERS, LIST_OF_COURSES, LIST_OF_ASSIGNMENTS, LIST_OF_ANSWERS, QUESTION_BANK,
        BACK, UNDEFINED
    }

    public enum OtherMenuOption {
        REGISTER_TO_COURSE, REGISTER_STUDENT_TO_COURSE, SCORE_BOARD, POINTING, BACK, UNDEFINED
    }

    public enum UserChangeMenuOption {
        CHANGE_FIRST_NAME, CHANGE_USERNAME, CHANGE_PASSWORD, CHANGE_EMAIL,
        CHANGE_PHONE_NUMBER, CHANGE_NATIONAL_CODE, BACK, UNDEFINED
    }

    public enum CourseChangeMenuOption {
        CHANGE_NAME, CHANGE_INSTITUTE, CHANGE_LECTURER, CHANGE_START_DATE,
        CHANGE_STATUS, CHANGE_PASSWORD, CHANGE_DESCRIPTION, BACK, UNDEFINED
    }

    public enum AssignmentChangeMenuOption {
        CHANGE_NAME, CHANGE_DESCRIPTION, CHANGE_START_DATE, CHANGE_END_DATE,
        CHANGE_DELAY_COEFFICIENT, CHANGE_DELAY_DATETIME, CHANGE_ASSIGNMENT_STATUS,
        CHANGE_SCOREBOARD_STATUS, BACK, UNDEFINED
    }

}
