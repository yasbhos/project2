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

    public enum LoginMenu {SIGN_IN, SIGN_UP, EXIT}

    public enum MainMenu {SEARCH, COURSES, BANK_OF_QUESTIONS, ACCOUNT, LOGOUT}

}
