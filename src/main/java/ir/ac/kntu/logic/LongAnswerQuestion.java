package ir.ac.kntu.logic;

import ir.ac.kntu.logic.Options.QuestionLevel;
import ir.ac.kntu.logic.Options.QuestionType;
import ir.ac.kntu.util.ScannerWrapper;

public class LongAnswerQuestion extends Question{
    public LongAnswerQuestion(String name, double score, String description, QuestionLevel level, QuestionType type) {
        super(name, score, description, level, type);
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

        return new LongAnswerQuestion(name, score, description, level, QuestionType.LONG_ANSWER);
    }
        
}
