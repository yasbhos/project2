package ir.ac.kntu.util;

import java.io.Console;
import java.util.Scanner;

public final class ScannerWrapper {

    private static final Console CONSOLE = System.console();

    private static final Scanner SCANNER_GENERATOR = new Scanner(System.in);

    private ScannerWrapper() {
    }

    public static int nextInt() {
        int nextInt = SCANNER_GENERATOR.nextInt();
        SCANNER_GENERATOR.nextLine();

        return nextInt;
    }

    public static int readInt(String message) {
        System.out.print(message);
        int nextInt = SCANNER_GENERATOR.nextInt();
        SCANNER_GENERATOR.nextLine();

        return nextInt;
    }

    public static double readDouble(String message) {
        System.out.print(message);
        double nextDouble = SCANNER_GENERATOR.nextDouble();
        SCANNER_GENERATOR.nextLine();

        return nextDouble;
    }

    public static String readString(String message) {
        System.out.print(message);

        return SCANNER_GENERATOR.nextLine();
    }

    public static String readPassword(String message) {
        if (CONSOLE == null) {
            System.out.println("No console available");
            return null;
        }
        System.out.print(message);

        return String.valueOf(CONSOLE.readPassword());
    }

    public static <T extends Enum<T>> T readEnum(T[] options) {
        showOptions(options);

        return scanTheEnum(options);
    }

    public static <T extends Enum<T>> T readEnum(T[] options, String color) {
        System.out.print(color);
        showOptions(options);
        System.out.print("\u001B[0m");

        return scanTheEnum(options);
    }

    public static <T extends Enum<T>> T readEnum(T[] options, String color, String message) {
        System.out.print(color);
        System.out.println(message);
        showOptions(options);
        System.out.print("\u001B[0m");

        return scanTheEnum(options);
    }

    private static <T extends Enum<T>> T scanTheEnum(T[] options) {
        int userInput = ScannerWrapper.nextInt();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        System.out.println("Invalid option!");
        return scanTheEnum(options);
    }

    private static <T extends Enum<T>> void showOptions(T[] options) {
        System.out.println("******************************");
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%2d. %s\n", i + 1, options[i].name());
        }
        System.out.println("******************************");
        System.out.println("Please enter your choice: ");
    }

    public static void close() {
        SCANNER_GENERATOR.close();
    }

}
