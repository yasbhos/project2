package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Question {

    enum QuestionLevel {
        EASY, MEDIUM, HARD, VERY_HARD, UNDEFINED
    }

    enum QuestionType {
        CHOICE_ONE, SHORT_ANSWER, LONG_ANSWER, FILL_IN_THE_BLANK, UNDEFINED
    }

    private String name;

    private double score;

    private String description;

    private QuestionLevel level;

    private QuestionType type;

    private String answer;

    private Map<User, ArrayList<Answer>> sentAnswers;

    public Question() {
        sentAnswers = new HashMap<>();
    }

    public Question(String name, double score, String description, QuestionLevel level, QuestionType type, String answer) {
        this.name = name;
        this.score = score;
        this.description = description;
        this.level = level;
        this.type = type;
        this.answer = answer;
        this.sentAnswers = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionLevel getLevel() {
        return level;
    }

    public void setLevel(QuestionLevel level) {
        this.level = level;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Map<User, ArrayList<Answer>> getSentAnswers() {
        return sentAnswers;
    }

    public void setSentAnswers(Map<User, ArrayList<Answer>> sentAnswers) {
        this.sentAnswers = sentAnswers;
    }

    public static QuestionLevel scanQuestionLevel() {
        QuestionLevel[] options = QuestionLevel.values();
        int userInput = ScannerWrapper.nextInt();
        ScannerWrapper.nextLine();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return QuestionLevel.UNDEFINED;
    }

    public static QuestionType scanQuestionType() {
        QuestionType[] options = QuestionType.values();
        int userInput = ScannerWrapper.nextInt();
        ScannerWrapper.nextLine();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        return QuestionType.UNDEFINED;
    }

    public static void printQuestionLevel() {
        System.out.println("Question Level:");
        System.out.println("********************");
        System.out.println("1. EASY");
        System.out.println("2. MEDIUM");
        System.out.println("3. HARD");
        System.out.println("4. VERY HARD");
        System.out.println("********************");
        System.out.print("\r\nPlease enter your choice: ");
    }

    public static void printQuestionType() {
        System.out.println("Question Type:");
        System.out.println("********************");
        System.out.println("1. CHOICE_ONE");
        System.out.println("2. SHORT_ANSWER");
        System.out.println("3. LONG_ANSWER");
        System.out.println("4. FILL_IN_THE_BLANK");
        System.out.println("********************");
        System.out.print("\r\nPlease enter your choice: ");
    }

    public static Question read() {
        String name = ScannerWrapper.readString("Enter question name: ");
        double score = ScannerWrapper.readDouble("Enter question score: ");
        String description = ScannerWrapper.readString("Enter question description: ");
        QuestionLevel level;
        while (true) {
            printQuestionLevel();
            level = scanQuestionLevel();
            ScannerWrapper.nextLine();
            if (level != QuestionLevel.UNDEFINED) {
                break;
            }
            System.out.println("Invalid input!");
        }
        QuestionType type;
        while (true) {
            printQuestionType();
            type = scanQuestionType();
            if (type != QuestionType.UNDEFINED) {
                break;
            }
            System.out.println("Invalid input!");
        }
        String answer = ScannerWrapper.readString("Enter question answer: ");

        return new Question(name, score, description, level, type, answer);
    }

    public Question deepCopy() {
        return new Question(name, score, description, level, type, answer);
    }

    @Override
    public String toString() {
        return "Question{" +
                ", name: '" + name + '\'' +
                ", score: '" + score + '\'' +
                ", name: \"" + description + '\"' +
                '}';
    }
}