package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.logic.Options.Color;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Question {
    public enum QuestionLevel {
        EASY, MEDIUM, HARD, VERY_HARD
    }

    public enum QuestionType {
        CHOICE_ONE, SHORT_ANSWER, LONG_ANSWER, FILL_IN_THE_BLANK
    }

    public enum StudentOption {
        SEND_ANSWER, LIST_OF_SENT_ANSWERS, SEARCH_ANSWERS_BY_EMAIL, BACK
    }

    public enum LecturerOption {
        CHANGE_NAME, CHANGE_SCORE, CHANGE_DESCRIPTION, CHANGE_LEVEL, CHANGE_TYPE, BACK
    }

    private String name;

    private double score;

    private String description;

    private QuestionLevel level;

    private QuestionType type;

    private DateTime uploadDateTime;

    private Map<User, ArrayList<Answer>> sentAnswers;

    public Question(String name, double score, String description, QuestionLevel level, QuestionType type) {
        this.name = name;
        this.score = score;
        this.description = description;
        this.level = level;
        this.type = type;
        this.uploadDateTime = DateTime.now();
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

    public DateTime getUploadDateTime() {
        return uploadDateTime;
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

    public void studentHandler(User user) {
        StudentOption userOption;
        do {
            userOption = ScannerWrapper.readEnum(StudentOption.values(), Color.YELLOW.getCode());
            studentOptionHandler(user, userOption);
        } while (userOption != StudentOption.BACK);
    }

    public void studentOptionHandler(User user, StudentOption userOption) {
        switch (userOption) {
            case SEND_ANSWER -> {
                Answer answer = readAnswer(user, "Enter your answer: ");
                addAnswer(user, answer);
                System.out.println("Answer sent.");
            }
            case LIST_OF_SENT_ANSWERS -> {
                if (sentAnswers.containsKey(user)) {
                    ArrayList<Answer> userAnswers = sentAnswers.get(user);
                    for (Answer answer : userAnswers) {
                        System.out.println(answer);
                    }
                } else {
                    System.out.println("You haven't sent any answer yet.");
                }
            }
            case SEARCH_ANSWERS_BY_EMAIL -> {
                String email = ScannerWrapper.readString("Enter email: ");
                for (User u : sentAnswers.keySet()) {
                    if (u.getEmail().equals(email)) {
                        ArrayList<Answer> userAnswers = sentAnswers.get(u);
                        for (Answer answer : userAnswers) {
                            System.out.println(answer);
                        }
                    }
                }
            }
            case BACK -> {
            }
            default -> {}
        }
    }

    public void lecturerHandler() {
        LecturerOption adminOption;
        do {
            adminOption = ScannerWrapper.readEnum(LecturerOption.values(), Color.YELLOW.getCode());
            lecturerOptionHandler(adminOption);
        } while (adminOption != LecturerOption.BACK);
    }

    public void lecturerOptionHandler(LecturerOption adminOption) {
        switch (adminOption) {
            case CHANGE_NAME -> this.name = ScannerWrapper.readString("Enter new name: ");
            case CHANGE_SCORE -> this.score = ScannerWrapper.readDouble("Enter new score: ");
            case CHANGE_DESCRIPTION -> this.description = ScannerWrapper.readString("Enter new description: ");
            case CHANGE_LEVEL -> this.level = ScannerWrapper.readEnum(QuestionLevel.values());
            case CHANGE_TYPE -> this.type = ScannerWrapper.readEnum(QuestionType.values());
            case BACK -> {
            }
            default -> {}
        }
    }

    public static Question readQuestion(String message) {
        System.out.println(message);
        String name = ScannerWrapper.readString("Enter question name: ");
        double score = ScannerWrapper.readDouble("Enter question score: ");
        String description = ScannerWrapper.readString("Enter question description: \n");
        QuestionLevel level = ScannerWrapper.readEnum(QuestionLevel.values());
        QuestionType type = ScannerWrapper.readEnum(QuestionType.values());
        switch (type) {
            case SHORT_ANSWER -> {
                String answer = ScannerWrapper.readString("Enter correct answer: ");
                return new ShortAnswerQuestion(name, score, description, level, type, answer);
            }
            case LONG_ANSWER, FILL_IN_THE_BLANK -> new Question(name, score, description, level, type);
            case CHOICE_ONE -> {
                ChoiceOneQuestion.Choices correctAnswer = ScannerWrapper.readEnum(ChoiceOneQuestion.Choices.values());
                return new ChoiceOneQuestion(name, score, description, level, type, correctAnswer);
            }
            default -> {}
        }

        return null;
    }

    public Answer readAnswer(User user, String message) {
        String description = ScannerWrapper.readString(message);
        return new Answer(user.getUsername(), this, description);
    }

    public Question copy() {
        Question question = new Question(this.name, this.score, this.description, this.level, this.type);
        question.uploadDateTime = this.uploadDateTime;
        return question;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", description='" + description + '\'' +
                ", level=" + level +
                ", type=" + type +
                ", uploadDateTime=" + uploadDateTime +
                '}';
    }

}