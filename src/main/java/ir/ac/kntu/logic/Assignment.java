package ir.ac.kntu.logic;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import ir.ac.kntu.util.ScannerWrapper;

public class Assignment {

    enum Option {
        CHANGE_NAME, CHANGE_DESCRIPTION, CHANGE_START_DATE, CHANGE_END_DATE,
        CHANGE_DELAY_COEFFICIENT, CHANGE_DELAY_DATETIME, BACK, UNDEFINED
    }

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    private String name;

    private String description;

    private DateTime startDate;

    private DateTime endDate;

    private int delayCoefficient;

    private DateTime delayDateTime;

    private ArrayList<Question> questions;

    private Map<User, Double> marks;

    public Assignment(String name, String description, DateTime startDate, DateTime endDate, int delayCoefficient,
                      DateTime delayDateTime) {
        this.name = name;
        this.description = description;
        this.startDate = startDate.deepCopy();
        this.endDate = endDate.deepCopy();
        this.delayCoefficient = delayCoefficient;
        this.delayDateTime = delayDateTime.deepCopy();
        this.marks = new HashMap<>();
        this.questions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public int getDelayCoefficient() {
        return delayCoefficient;
    }

    public DateTime getDelayDateTime() {
        return delayDateTime;
    }

    public ArrayList<Question> getQuestions() {
        ArrayList<Question> deepCopyOfQuestions = new ArrayList<>();
        for (Question question : questions) {
            deepCopyOfQuestions.add(question.deepCopy());
        }
        return deepCopyOfQuestions;
    }

    public Map<User, Double> getMarks() {
        return new HashMap<>(marks);
    }

    public void addQuestion(Question question) {
        this.questions.add(question.deepCopy());
    }

    public void changeHandler() {
        Option option;
        do {
            printTheMenu();
            option = scanOption();
            handleTheOption(option);
        } while (option != Option.BACK);
    }

    public void handleTheOption(Option option) {
        switch(option) {
            case CHANGE_NAME:
                this.name = ScannerWrapper.readString("Enter new name: ");
                break;
            case CHANGE_DESCRIPTION:
                this.description = ScannerWrapper.readString("Enter new description: ");
                break;
            case CHANGE_START_DATE:
                System.out.println("Enter new start date: ");
                DateTime dt = DateTime.readDateTime();
                this.startDate = dt;
                break;
            case CHANGE_END_DATE:
                System.out.println("Enter new end date: ");
                DateTime d = DateTime.readDateTime();
                this.endDate = d;
                break;
            case CHANGE_DELAY_COEFFICIENT:
                this.delayCoefficient = ScannerWrapper.readInt("Enter new delay coefficient: ");
                break;
            case CHANGE_DELAY_DATETIME:
                System.out.println("Enter new delay date and time: ");
                DateTime t = DateTime.readDateTime();
                this.delayDateTime = t;
                break;
            case BACK:
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }
    }

    public static Option scanOption() {
        Option[] options = Option.values();
        int userInput = ScannerWrapper.nextInt();
        ScannerWrapper.nextLine();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }

        return Option.UNDEFINED;
    }

    public void printTheMenu() {
        System.out.println(ANSI_YELLOW);
        System.out.println("******************************");
        System.out.println("Assignment change menu: ");
        System.out.println("1. Change name");
        System.out.println("2. Change description");
        System.out.println("3. Change start date");
        System.out.println("4. Change end date");
        System.out.println("5. Change delay coefficient");
        System.out.println("6. Change delay date and time");
        System.out.println("7. BACK");
        System.out.println("******************************");
        System.out.print("\r\nPlease enter your choice: ");
        System.out.println(ANSI_RESET);
    }

    public static Assignment read() {
        String name = ScannerWrapper.readString("Enter name: ");
        String description = ScannerWrapper.readString("Enter description: ");
        DateTime startDate = DateTime.readDateTime();
        DateTime endDate = DateTime.readDateTime();
        int delayCoefficient = ScannerWrapper.readInt("Enter delay coefficient: ");
        DateTime delayDate = DateTime.readDateTime();

        return new Assignment(name, description, startDate, endDate, delayCoefficient, delayDate);
    }

    public Assignment deepCopy() {
        return new Assignment(name, description, startDate.deepCopy(), endDate.deepCopy(), delayCoefficient, delayDateTime.deepCopy());
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }

}