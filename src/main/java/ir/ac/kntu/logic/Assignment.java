package ir.ac.kntu.logic;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import ir.ac.kntu.Main;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.logic.Options.Color;

public class Assignment {
    public enum Status {ACTIVE, INACTIVE}

    public enum StudentOption {LIST_OF_QUESTIONS, LIST_OF_FINAL_SENT_ANSWERS, SCOREBOARD, BACK}

    public enum LecturerOption {
        CHANGE_NAME, CHANGE_DESCRIPTION, CHANGE_START_DATE, CHANGE_END_DATE,
        CHANGE_DELAY_COEFFICIENT, CHANGE_DELAY_DATETIME, CHANGE_ASSIGNMENT_STATUS,
        CHANGE_SCOREBOARD_STATUS, LIST_OF_QUESTIONS, ADD_QUESTION_TO_ASSIGNMENT,
        REMOVE_QUESTION, SCOREBOARD, REGISTER_MARK_TO_FINAL_SENDS, BACK
    }

    private String name;

    private String description;

    private DateTime startDate;

    private DateTime endDate;

    private int delayCoefficient;

    private DateTime delayDateTime;

    private Status assignmentStatus;

    private Status scoreBoardStatus;

    private ArrayList<Question> questions;

    public Assignment(String name, String description, DateTime startDate, DateTime endDate, int delayCoefficient,
                      DateTime delayDateTime, Status assignmentStatus, Status scoreBoardStatus) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.delayCoefficient = delayCoefficient;
        this.delayDateTime = delayDateTime;
        this.assignmentStatus = assignmentStatus;
        this.scoreBoardStatus = scoreBoardStatus;
        this.questions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public int getDelayCoefficient() {
        return delayCoefficient;
    }

    public DateTime getDelayDateTime() {
        return delayDateTime;
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

    public void studentHandler() {
        StudentOption option;
        do {
            option = ScannerWrapper.readEnum(StudentOption.values(), Color.YELLOW.getCode());
            studentOptionHandler(option);
        } while (option != StudentOption.BACK);
    }

    private void studentOptionHandler(StudentOption option) {
        switch (option) {
            case LIST_OF_QUESTIONS -> {
                Question question = searchQuestion();
                if (question != null) {
                    question.studentHandler(Main.currentUser);
                }
            }
            case LIST_OF_FINAL_SENT_ANSWERS -> {
                for (Question question : questions) {
                    ArrayList<Answer> answers = question.getSentAnswers().get(Main.currentUser);
                    if (answers == null) {
                        continue;
                    }
                    for (Answer answer : answers) {
                        if (answer.isFinalSent()) {
                            System.out.println(answer);
                        }
                    }
                }
            }
            case SCOREBOARD -> {
                if (scoreBoardStatus == Status.ACTIVE) {
                    scoreBoard();
                } else {
                    System.out.println("Scoreboard is not active.");
                }
            }
            case BACK -> {
            }
            default -> {}
        }
    }

    public void lecturerHandler() {
        LecturerOption option;
        do {
            option = ScannerWrapper.readEnum(LecturerOption.values(), Color.YELLOW.getCode());
            lecturerOptionHandler(option);
        } while (option != LecturerOption.BACK);
    }

    private void lecturerOptionHandler(LecturerOption option) {
        switch (option) {
            case CHANGE_NAME -> this.name = ScannerWrapper.readString("Enter new name: ");
            case CHANGE_DESCRIPTION -> this.description = ScannerWrapper.readString("Enter new description: ");
            case CHANGE_START_DATE -> this.startDate = DateTime.readDateTime("Enter new start date: ");
            case CHANGE_END_DATE -> this.endDate = DateTime.readDateTime("Enter new end date: ");
            case CHANGE_DELAY_COEFFICIENT -> this.delayCoefficient = ScannerWrapper.readInt("Enter new delay coefficient: ");
            case CHANGE_DELAY_DATETIME -> this.delayDateTime = DateTime.readDateTime("Enter new delay date and time: ");
            case CHANGE_ASSIGNMENT_STATUS -> this.assignmentStatus = ScannerWrapper.readEnum(Status.values());
            case CHANGE_SCOREBOARD_STATUS -> this.scoreBoardStatus = ScannerWrapper.readEnum(Status.values());
            case LIST_OF_QUESTIONS -> {
                Question question = searchQuestion();
                if (question != null) {
                    question.lecturerHandler();
                }
            }
            case ADD_QUESTION_TO_ASSIGNMENT -> {
                String choice = ScannerWrapper.readString("New question or existing question? (n/e): ");
                if (choice.equals("n")) {
                    Question question = Question.readQuestion("Enter question: ");
                    this.addQuestion(question);
                } else if (choice.equals("e")) {
                    Question question = Main.searchQuestion();
                    if (question != null) {
                        this.addQuestion(question.copy());
                    }
                } else {
                    System.out.println("Invalid choice");
                }
            }
            case REMOVE_QUESTION -> questions.remove(searchQuestion());
            case SCOREBOARD -> scoreBoard();
            case REGISTER_MARK_TO_FINAL_SENDS -> registerMarkToFinalSent();
            case BACK -> {
            }
            default -> {}
        }
    }

    private Question searchQuestion() {
        for (int i = 0; i < this.questions.size(); i++) {
            System.out.println(i + 1 + ". " + this.questions.get(i).getName());
        }
        int index = ScannerWrapper.readInt("Enter question index: ");
        return this.questions.get(index - 1);
    }

    public void scoreBoard() {
        System.out.println("Scoreboard for " + this.name);
        System.out.println("------------------------------------------------------------");
        System.out.println("| Student name | Mark |");
        Map<User, Double> marks = new HashMap<>();
        for (Question question : questions) {
            for (Map.Entry<User, ArrayList<Answer>> entry : question.getSentAnswers().entrySet()) {
                ArrayList<Answer> answers = question.getSentAnswers().get(entry.getKey());
                if (marks.containsKey(entry.getKey())) {
                    marks.put(entry.getKey(), marks.get(entry.getKey()) + answers.get(answers.size() - 1).getScoreWithDelay());
                } else {
                    marks.put(entry.getKey(), answers.get(answers.size() - 1).getScoreWithDelay());
                }
            }
        }
        for (Map.Entry<User, Double> entry : marks.entrySet()) {
            System.out.println("| " + entry.getKey().getUsername() + " | " + entry.getValue() + " |");
        }
        System.out.println("------------------------------------------------------------");
    }

    public void registerMarkToFinalSent() {
        System.out.println("Register mark to final sent for " + this.name);
        System.out.println("------------------------------------------------------------");
        for (int i = 0; i < this.questions.size(); i++) {
            Question question = this.questions.get(i);
            System.out.println(i + 1 + ". " + question.getName());
            for (User user : question.getSentAnswers().keySet()) {
                System.out.println(user.getFirstName() + ": ");
                ArrayList<Answer> answers = question.getSentAnswers().get(user);
                answers.get(answers.size() - 1).setDelayCoefficient(delayCoefficient);
                System.out.println(answers.get(answers.size() -1));
                Double mark = ScannerWrapper.readDouble("Enter mark: ");
                answers.get(answers.size() - 1).setScore(mark);
            }
        }
        System.out.println("------------------------------------------------------------");
    }

    public static Assignment readAssignment(String message) {
        System.out.println(message);
        String name = ScannerWrapper.readString("Enter name: ");
        String description = ScannerWrapper.readString("Enter description: ");
        DateTime startDate = DateTime.readDateTime("Enter start date: ");
        DateTime endDate = DateTime.readDateTime("Enter end date: ");
        int delayCoefficient = ScannerWrapper.readInt("Enter delay coefficient: ");
        DateTime delayDate = DateTime.readDateTime("Enter delay date and time: ");
        Status assignmentStatus = ScannerWrapper.readEnum(Status.values(), Color.RESET.getCode(), "Enter assignment state: ");
        Status scoreBoardStatus = ScannerWrapper.readEnum(Status.values(), Color.RESET.getCode(), "Enter scoreBoard state: ");

        return new Assignment(name, description, startDate, endDate, delayCoefficient, delayDate, assignmentStatus, scoreBoardStatus);
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