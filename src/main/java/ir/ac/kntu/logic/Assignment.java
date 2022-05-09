package ir.ac.kntu.logic;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import ir.ac.kntu.util.ScannerWrapper;

public class Assignment {
    public enum Status {ACTIVE, INACTIVE, UNDEFINED}

    private String name;

    private String description;

    private DateTime startDate;

    private DateTime endDate;

    private int delayCoefficient;

    private DateTime delayDateTime;

    private Status assignmentStatus;

    private Status scoreBoardStatus;

    private ArrayList<Question> questions;

    private Map<User, Double> scoreBoard;

    public Assignment(String name, String description, DateTime startDate, DateTime endDate, int delayCoefficient,
                      DateTime delayDateTime, Status assignmentStatus, Status scoreBoardStatus, ArrayList<Question> questions) {
        this.name = name;
        this.description = description;
        this.startDate = startDate.deepCopy();
        this.endDate = endDate.deepCopy();
        this.delayCoefficient = delayCoefficient;
        this.delayDateTime = delayDateTime.deepCopy();
        this.assignmentStatus = assignmentStatus;
        this.scoreBoardStatus = scoreBoardStatus;
        this.questions = questions;
        this.scoreBoard = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getStartDate() {
        return startDate.deepCopy();
    }

    public DateTime getEndDate() {
        return endDate.deepCopy();
    }

    public int getDelayCoefficient() {
        return delayCoefficient;
    }

    public DateTime getDelayDateTime() {
        return delayDateTime.deepCopy();
    }

    public Status getAssignmentStatus() {
        return assignmentStatus;
    }

    public Status getScoreBoardStatus() {
        return scoreBoardStatus;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public Map<User, Double> getScoreBoard() {
        Map<User, Double> deepCopyOfScoreBoard = new HashMap<>();
        for (Map.Entry<User, Double> entry : scoreBoard.entrySet()) {
            deepCopyOfScoreBoard.put(entry.getKey().deepCopy(), entry.getValue());
        }

        return deepCopyOfScoreBoard;
    }

    public void scoreBoard() {
        for (Map.Entry<User, Double> entry : scoreBoard.entrySet()) {
            System.out.println(entry.getKey().getFirstname() + ": " + entry.getValue());
        }
    }

    public void pointing() {
        for (Question question : questions) {
            for (User user : question.getSentAnswers().keySet()) {
                System.out.println(user.getFirstname() + ": " + question.getFinalAnswer(user));
                double score = ScannerWrapper.readDouble("Enter score: ");
                question.getFinalAnswer(user).setScore(score);
                if (scoreBoard.containsKey(user)) {
                    scoreBoard.put(user, scoreBoard.get(user) + score);
                } else {
                    scoreBoard.put(user, score);
                }
            }
        }
    }

    public void changeHandler() {
        Options.AssignmentChangeMenuOption option;
        do {
            option = ScannerWrapper.readEnum(Options.AssignmentChangeMenuOption.values(), Options.Color.YELLOW.getCode());
            handleTheOption(option);
        } while (option != Options.AssignmentChangeMenuOption.BACK);
    }

    public void handleTheOption(Options.AssignmentChangeMenuOption option) {
        switch (option) {
            case CHANGE_NAME -> this.name = ScannerWrapper.readString("Enter new name: ");
            case CHANGE_DESCRIPTION -> this.description = ScannerWrapper.readString("Enter new description: ");
            case CHANGE_START_DATE -> this.startDate = DateTime.readDateTime("Enter new start date: ");
            case CHANGE_END_DATE -> this.endDate = DateTime.readDateTime("Enter new end date: ");
            case CHANGE_DELAY_COEFFICIENT -> this.delayCoefficient = ScannerWrapper.readInt("Enter new delay coefficient: ");
            case CHANGE_DELAY_DATETIME -> this.delayDateTime = DateTime.readDateTime("Enter new delay date and time: ");
            case CHANGE_ASSIGNMENT_STATUS -> this.assignmentStatus = ScannerWrapper.readEnum(Status.values());
            case CHANGE_SCOREBOARD_STATUS -> this.scoreBoardStatus = ScannerWrapper.readEnum(Status.values());
            case UNDEFINED -> {
            }
            default -> System.out.println("Invalid option!");
        }
    }

    public static Assignment readAssignment(String message) {
        System.out.println(message);

        String name = ScannerWrapper.readString("Enter name: ");
        String description = ScannerWrapper.readString("Enter description: ");
        DateTime startDate = DateTime.readDateTime("Enter start date: ");
        DateTime endDate = DateTime.readDateTime("Enter end date: ");
        int delayCoefficient = ScannerWrapper.readInt("Enter delay coefficient: ");
        DateTime delayDate = DateTime.readDateTime("Enter delay date and time: ");
        Status assignmentStatus = ScannerWrapper.readEnum(Status.values(), Options.Color.RESET.getCode(), "Enter assignment state: ");
        Status scoreBoardStatus = ScannerWrapper.readEnum(Status.values(), Options.Color.RESET.getCode(), "Enter score board state: ");

        return new Assignment(name, description, startDate, endDate, delayCoefficient, delayDate, assignmentStatus, scoreBoardStatus, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", delayCoefficient=" + delayCoefficient +
                ", delayDateTime=" + delayDateTime +
                ", assignment status=" + assignmentStatus +
                ", scoreBoard status=" + scoreBoardStatus +
                '}';
    }

}