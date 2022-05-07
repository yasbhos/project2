package ir.ac.kntu.logic;

import ir.ac.kntu.logic.Graphics.Color;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Question {

    public enum QuestionLevel {
        EASY, MEDIUM, HARD, VERY_HARD, UNDEFINED
    }

    public enum QuestionType {
        CHOICE_ONE, SHORT_ANSWER, LONG_ANSWER, FILL_IN_THE_BLANK, UNDEFINED
    }

    private String name;

    private double score;

    private String description;

    private QuestionLevel level;

    private QuestionType type;

    private String answer;

    private Map<User, ArrayList<Answer>> answers;

    public Question(String name, double score, String description, QuestionLevel level, QuestionType type, String answer) {
        this.name = name;
        this.score = score;
        this.description = description;
        this.level = level;
        this.type = type;
        this.answer = answer;
        this.answers = new HashMap<>();
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

    public String getAnswer() {
        return answer;
    }

    public Map<User, ArrayList<Answer>> getAnswers() {
        return answers;
    }

    public void addAnswer(User user, Answer answer) {
        if (type == QuestionType.CHOICE_ONE || type == QuestionType.SHORT_ANSWER) {
            if (answer.getDescription().equals(this.answer)) {
                answer.setScore(score);
            }
        }

        if (answers.containsKey(user)) {
            ArrayList<Answer> userAnswers = answers.get(user);
            userAnswers.get(userAnswers.size() - 1).setFinalSent(false);
            answers.get(user).add(answer);
        } else {
            ArrayList<Answer> userAnswers = new ArrayList<>();
            userAnswers.add(answer);
            answers.put(user, userAnswers);
        }
    }

    public void removeAnswer(User user, Answer answer) {
        if (answers.containsKey(user)) {
            answers.get(user).remove(answer);
        }
    }

    public Answer getFinalAnswer(User user) {
        if (answers.containsKey(user)) {
            ArrayList<Answer> userAnswers = answers.get(user);
            for (Answer answer : userAnswers) {
                if (answer.isFinalSent()) {
                    return answer;
                }
            }
        }

        return null;    
    }

    public static Question read() {
        String name = ScannerWrapper.readString("Enter question name: ");
        double score = ScannerWrapper.readDouble("Enter question score: ");
        String description = ScannerWrapper.readString("Enter question description: \n");
        QuestionLevel level;
        do {
            level = Graphics.scanTheOption(QuestionLevel.values(), Color.WHITE);
            if (level == QuestionLevel.UNDEFINED) {
                System.out.println("Please enter a valid question level.");
            }
        } while (level == QuestionLevel.UNDEFINED);

        QuestionType type;
        do {
            type = Graphics.scanTheOption(QuestionType.values(), Color.WHITE);
            if (type == QuestionType.UNDEFINED) {
                System.out.println("Please enter a valid question type.");
            }
        } while (type == QuestionType.UNDEFINED);

        String answer = ScannerWrapper.readString("Enter question answer: ");

        return new Question(name, score, description, level, type, answer);
    }

    public Question deepCopy() {
        return new Question(name, score, description, level, type, answer);
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