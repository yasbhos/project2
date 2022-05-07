package ir.ac.kntu.logic;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import ir.ac.kntu.util.ScannerWrapper;

public class Assignment {

    private String name;

    private String description;

    private DateTime startDate;

    private DateTime endDate;

    private int delayCoefficient;

    private DateTime delayDateTime;

    private boolean assignmentActive;

    private boolean scoreBoardOpen;

    private ArrayList<Question> questions;

    private Map<User, Double> scoreBoard;

    public Assignment(String name, String description, DateTime startDate, DateTime endDate, int delayCoefficient,
                      DateTime delayDateTime, ArrayList<Question> questions, boolean assignmentActive, boolean scoreBoardOpen) {
        this.name = name;
        this.description = description;
        this.startDate = startDate.deepCopy();
        this.endDate = endDate.deepCopy();
        this.delayCoefficient = delayCoefficient;
        this.delayDateTime = delayDateTime.deepCopy();
        this.questions = questions;
        this.scoreBoard = new HashMap<>();
        this.assignmentActive = assignmentActive;
        this.scoreBoardOpen = scoreBoardOpen;
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

    public ArrayList<Question> getQuestions() {
        ArrayList<Question> deepCopyOfQuestions = new ArrayList<>();
        for (Question question : questions) {
            deepCopyOfQuestions.add(question.deepCopy());
        }

        return deepCopyOfQuestions;
    }

    public boolean isAssignmentActive() {
        return assignmentActive;
    }

    public boolean isScoreBoardOpen() {
        return scoreBoardOpen;
    }

    public void addQuestion(Question question) {
        this.questions.add(question.deepCopy());
    }

    public void removeQuestion(Question question) {
        this.questions.remove(question.deepCopy());
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
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }
    }

    public void updateScoreBoard() {
        for (Question question : questions) {
            for (User user : question.getAnswers().keySet()) {
                if (scoreBoard.containsKey(user)) {
                    scoreBoard.put(user, scoreBoard.get(user) + question.getFinalAnswer(user).getScore());
                } else {
                    scoreBoard.put(user, question.getFinalAnswer(user).getScore());
                }
            }
        }
    }

    public void pointing() {
        for (Question question : questions) {
            for (User user : question.getAnswers().keySet()) {
                System.out.println(user.getName() + ": " + question.getFinalAnswer(user));
                double score = ScannerWrapper.readDouble("Enter score: ");
                question.getFinalAnswer(user).setScore(score);
                if (scoreBoard.containsKey(user)) {
                    scoreBoard.put(user, scoreBoard.get(user) + question.getFinalAnswer(user).getScore());
                } else {
                    scoreBoard.put(user, question.getFinalAnswer(user).getScore());
                }
            }
        }
    }

    public void changeHandler() {
        Options.AssignmentChangeMenuOption option;
        do {
            option = Graphics.scanTheOption(Options.AssignmentChangeMenuOption.values(), Graphics.Color.YELLOW);
            handleTheOption(option);
        } while (option != Options.AssignmentChangeMenuOption.BACK);
    }

    public void handleTheOption(Options.AssignmentChangeMenuOption option) {
        switch (option) {
            case CHANGE_NAME:
                this.name = ScannerWrapper.readString("Enter new name: ");
                break;
            case CHANGE_DESCRIPTION:
                this.description = ScannerWrapper.readString("Enter new description: ");
                break;
            case CHANGE_START_DATE: {
                System.out.println("Enter new start date: ");
                DateTime dateTime = DateTime.readDateTime();
                this.startDate = dateTime;
            }
            break;
            case CHANGE_END_DATE: {
                System.out.println("Enter new end date: ");
                DateTime dateTime = DateTime.readDateTime();
                this.endDate = dateTime;
            }
            break;
            case CHANGE_DELAY_COEFFICIENT:
                this.delayCoefficient = ScannerWrapper.readInt("Enter new delay coefficient: ");
                break;
            case CHANGE_DELAY_DATETIME: {
                System.out.println("Enter new delay date and time: ");
                DateTime dateTime = DateTime.readDateTime();
                this.delayDateTime = dateTime;
            }
            break;
            case CHANGE_ASSIGNMENT_STATE:
                this.assignmentActive = ScannerWrapper.readBoolean("Enter new assignment state: ");
                break;
            case CHANGE_SCOREBOARD_STATE:
                this.scoreBoardOpen = ScannerWrapper.readBoolean("Enter new score board state: ");
                break;
            case BACK:
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    public static Assignment read() {
        String name = ScannerWrapper.readString("Enter name: ");
        String description = ScannerWrapper.readString("Enter description: ");
        System.out.println("Enter start date: ");
        DateTime startDate = DateTime.readDateTime();
        System.out.println("Enter end date: ");
        DateTime endDate = DateTime.readDateTime();
        int delayCoefficient = ScannerWrapper.readInt("Enter delay coefficient: ");
        System.out.println("Enter delay date and time: ");
        DateTime delayDate = DateTime.readDateTime();

        return new Assignment(name, description, startDate, endDate, delayCoefficient, delayDate, new ArrayList<>(),
                false, false);
    }

    public Assignment deepCopy() {
        Assignment assignment = new Assignment(name, description, startDate, endDate, delayCoefficient,
                delayDateTime, getQuestions(), assignmentActive, scoreBoardOpen);
        assignment.scoreBoard = getScoreBoard();

        return assignment;
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
                ", assignment state=" + assignmentActive +
                ", scoreBoard state=" + scoreBoardOpen +
                '}';
    }

}