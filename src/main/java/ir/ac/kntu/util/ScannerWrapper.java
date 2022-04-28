package ir.ac.kntu.util;

import java.util.Scanner;

public final class ScannerWrapper {

    private static final Scanner SCANNER_GENERATOR = new Scanner(System.in);

    private ScannerWrapper() {
    }

    public static int nextInt() {
        return SCANNER_GENERATOR.nextInt();
    }

    public static String nextLine() {
        return SCANNER_GENERATOR.nextLine();
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
        return SCANNER_GENERATOR.nextInt();
    }

    public static double readDouble(String message) {
        System.out.print(message);
        return SCANNER_GENERATOR.nextDouble();
    }

}