package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

import ir.ac.kntu.logic.Options.QuestionLevel;
import ir.ac.kntu.logic.Options.QuestionType;

public class ChoiceOneQuestion extends Question{
    private ArrayList<String> options;

    private String correctOption;

    public ChoiceOneQuestion(String name, double score, String description, QuestionLevel level, QuestionType type, String correctOption) {
        super(name, score, description, level, type);
        this.correctOption = correctOption;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    @Override
    public void addAnswer(User user, Answer answer) {
        super.addAnswer(user, answer);
        if (answer.getDescription().equals(correctOption)) {
            answer.setScore(super.getScore());
        }
    }

    public static Question read() {
        String name = ScannerWrapper.readString("Enter question name: ");
        double score = ScannerWrapper.readDouble("Enter question score: ");
        String description = ScannerWrapper.readString("Enter question description: \n");
        QuestionLevel level;
        do {
            level = ScannerWrapper.readEnum(QuestionLevel.values());
            if (level == QuestionLevel.UNDEFINED) {
                System.out.println("Please enter a valid question level.");
            }
        } while (level == QuestionLevel.UNDEFINED);

        String correctOption = ScannerWrapper.readString("Enter correct option(a, b, c, d): ");

        return new ChoiceOneQuestion(name, score, description, level, QuestionType.CHOICE_ONE, correctOption);
    }

}
