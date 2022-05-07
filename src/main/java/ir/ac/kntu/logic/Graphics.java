package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;

public class Graphics {

    public enum Color {
        RED("\u001B[31m"), GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"), BLUE("\u001B[34m"),
        WHITE("\u001B[37m"), RESET("\u001B[0m");

        private String code;

        Color(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public static <T extends Enum<T>> T scanTheOption(T[] options, Color color) {
        printTheOptions(options, color);
        int userInput = ScannerWrapper.nextInt();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }

        return options[options.length - 1];
    }

    public static <T extends Enum<T>> void printTheOptions(T[] options, Color color) {
        System.out.print(color.getCode());
        System.out.println("******************************");
        for (int i = 0; i < options.length - 1; i++) {
            System.out.println((i + 1) + ". " + options[i].name());
        }
        System.out.println("******************************");
        System.out.println("Please enter your choice: ");
        System.out.print(Color.RESET.getCode());
    }

}
