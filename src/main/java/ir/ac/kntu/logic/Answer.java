package ir.ac.kntu.logic;

import ir.ac.kntu.util.ScannerWrapper;

public class Answer {

    private double score;

    private String description;

    private DateTime sentDateTime;

    private boolean finalSent;

    public Answer(String description, DateTime sentDateTime, boolean finalSent) {
        this.sentDateTime = sentDateTime;
        this.description = description;
        this.finalSent = finalSent;
    }

    public DateTime getSentDateTime() {
        return sentDateTime.deepCopy();
    }

    public void setSentDateTime(DateTime sentDateTime) {
        this.sentDateTime = sentDateTime.deepCopy();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinalSent() {
        return finalSent;
    }

    public void setFinalSent(boolean finalSent) {
        this.finalSent = finalSent;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public static Answer read() {
        String description = ScannerWrapper.readString("Enter answer: ");

        return new Answer(description, DateTime.now(), true);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "description='" + description + '\'' +
                ", sentDateTime=" + sentDateTime +
                ", finalSent=" + finalSent +
                '}';
    }

}