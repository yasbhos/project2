package ir.ac.kntu.util;

import java.util.Scanner;
import java.io.Console;

public final class ScannerWrapper {

    private static final Scanner SCANNER_GENERATOR = new Scanner(System.in);

    private static final Console CONSOLE = System.console();

    private ScannerWrapper() {
    }

    public static int nextInt() {
        int nextInt = SCANNER_GENERATOR.nextInt();
        SCANNER_GENERATOR.nextLine();
        return nextInt;
    }

    public static String readString(String message) {
        System.out.print(message);
        return SCANNER_GENERATOR.nextLine();
    }

    public static boolean readBoolean(String message) {
        System.out.print(message);
        return SCANNER_GENERATOR.nextBoolean();
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

    public static String readPassword(String message) {
        if (CONSOLE == null) {
            System.out.println("No console available");
            return null;
        }
        System.out.print(message);
        return String.valueOf(CONSOLE.readPassword());
    }

}
