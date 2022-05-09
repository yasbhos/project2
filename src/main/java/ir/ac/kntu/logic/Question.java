package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.logic.Options.QuestionLevel;
import ir.ac.kntu.logic.Options.QuestionType;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Question {
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
    }

    public void removeAnswer(User user, Answer answer) {
        sentAnswers.get(user).remove(answer);
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

        QuestionType type;
        do {
            type = ScannerWrapper.readEnum(QuestionType.values());
            if (type == QuestionType.UNDEFINED) {
                System.out.println("Please enter a valid question type.");
            }
        } while (type == QuestionType.UNDEFINED);

        switch (type) {
            case SHORT_ANSWER -> ShortAnswerQuestion.read();
            case LONG_ANSWER -> LongAnswerQuestion.read();
            case CHOICE_ONE -> ChoiceOneQuestion.read();
            case FILL_IN_THE_BLANK -> FillBlankQuestion.read();
            case UNDEFINED -> {}
            default -> {}
        }

        return null;
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