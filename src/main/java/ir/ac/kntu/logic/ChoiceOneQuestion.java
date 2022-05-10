package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;

public class ChoiceOneQuestion extends Question {
    public enum Choices {
        ONE, TWO, THREE, FOUR
    }

    private String correctAnswer;

    public ChoiceOneQuestion(String name, double score, String description, QuestionLevel level, QuestionType type,
                             Choices correctAnswer) {
        super(name, score, description, level, type);
        this.correctAnswer = correctAnswer.toString();
    }

    public String getCorrectOption() {
        return correctAnswer;
    }

    @Override
    public Answer readAnswer(User user, String message) {
        System.out.println(message);
        Choices answer = ScannerWrapper.readEnum(Choices.values());
        return new Answer(user.getUsername(), this, answer.toString());
    }

}
