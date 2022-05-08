package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Question {
    //TODO: Make other children to support other type of questions
    public enum QuestionLevel {EASY, MEDIUM, HARD, VERY_HARD, UNDEFINED}

    public enum QuestionType {CHOICE_ONE, SHORT_ANSWER, LONG_ANSWER, FILL_IN_THE_BLANK, UNDEFINED}

    private String name;

    private double score;

    private String description;

    private QuestionLevel level;

    private QuestionType type;

    private Map<User, ArrayList<Answer>> sentAnswers;

    public Question(String name, double score, String description, QuestionLevel level, QuestionType type) {
        this.name = name;
        this.score = score;
        this.description = description;
        this.level = level;
        this.type = type;
        this.sentAnswers = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getDescription() {
        return description;
    }

    public QuestionLevel getLevel() {
        return level;
    }

    public QuestionType getType() {
        return type;
    }

    public Map<User, ArrayList<Answer>> getSentAnswers() {
        return sentAnswers;
    }

    public void addAnswer(User user, Answer answer) {
        if (sentAnswers.containsKey(user)) {
            ArrayList<Answer> userAnswers = sentAnswers.get(user);
            userAnswers.get(userAnswers.size() - 1).setFinalSent(false);
            userAnswers.add(answer);
        } else {
            ArrayList<Answer> userAnswers = new ArrayList<>();
            userAnswers.add(answer);
            sentAnswers.put(user, userAnswers);
        }
        //TODO: update scoreBoard(maybe just when we have auto pointing)
    }

    public void removeAnswer(User user, Answer answer) {
        sentAnswers.get(user).remove(answer);
        //TODO: update scoreBoard(complete at the end of developing)
    }

    public Answer getFinalAnswer(User user) {
        if (!sentAnswers.containsKey(user)) {
            return null;
        }
        ArrayList<Answer> userAnswers = sentAnswers.get(user);
        for (Answer answer : userAnswers) {
            if (answer.isFinalSent()) {
                return answer;
            }
        }

        return null;
    }

    public static Question readQuestion(String message) {
        System.out.println(message);

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

        QuestionType type;
        do {
            type = ScannerWrapper.readEnum(QuestionType.values());
            if (type == QuestionType.UNDEFINED) {
                System.out.println("Please enter a valid question type.");
            }
        } while (type == QuestionType.UNDEFINED);

        return new Question(name, score, description, level, type);
    }

    public Question deepCopy() {
        return new Question(name, score, description, level, type);
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", description='" + description + '\'' +
                ", level=" + level +
                ", type=" + type +
                '}';
    }

}