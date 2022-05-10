package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;

public class ShortAnswerQuestion extends Question {
    private String correctAnswer;

    public ShortAnswerQuestion(String name, double score, String description, QuestionLevel level, QuestionType type, String correctAnswer) {
        super(name, score, description, level, type);
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public Answer readAnswer(User user, String message) {
        String answer = ScannerWrapper.readString(message);
        return new Answer(user.getUsername(), this, answer);
    }

}
