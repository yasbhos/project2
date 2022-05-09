package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.logic.Options.QuestionLevel;
import ir.ac.kntu.logic.Options.QuestionType;

public class ShortAnswerQuestion extends Question{
    private String correctAnswer;

    public ShortAnswerQuestion(String name, double score, String description, QuestionLevel level, QuestionType type, String correctAnswer) {
        super(name, score, description, level, type);
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    
    @Override
    public void addAnswer(User user, Answer answer) {
        super.addAnswer(user, answer);
        if (answer.getDescription().equals(correctAnswer)) {
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

        String correctAnswer = ScannerWrapper.readString("Enter correct answer: ");

        return new ShortAnswerQuestion(name, score, description, level, QuestionType.SHORT_ANSWER, correctAnswer);
    }
}
