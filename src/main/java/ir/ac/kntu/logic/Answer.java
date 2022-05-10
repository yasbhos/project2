package ir.ac.kntu.logic;

public class Answer {

    private String senderUsername;

    private Question question;

    private DateTime sentDateTime;

    private double delayCoefficient;

    private double score;

    private double scoreWithDelay;

    private boolean finalSent;

    private String description;

    public Answer(String senderUsername, Question question, String description) {
        this.senderUsername = senderUsername;
        this.question = question;
        this.sentDateTime = DateTime.now();
        this.finalSent = true;
        this.description = description;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public Question getQuestion() {
        return question;
    }

    public DateTime getSentDateTime() {
        return sentDateTime;
    }

    public double getDelayCoefficient() {
        return delayCoefficient;
    }

    public void setDelayCoefficient(int delayCoefficient) {
        this.delayCoefficient = delayCoefficient;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
        this.scoreWithDelay = (1 - delayCoefficient / 100) * this.score;
    }

    public double getScoreWithDelay() {
        return scoreWithDelay;
    }

    public boolean isFinalSent() {
        return finalSent;
    }

    public void setFinalSent(boolean finalSent) {
        this.finalSent = finalSent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "senderUsername='" + senderUsername + '\'' +
                ", question=" + question +
                ", sentDateTime=" + sentDateTime +
                ", delayCoefficient=" + delayCoefficient +
                ", score=" + score +
                ", scoreWithDelay=" + scoreWithDelay +
                ", finalSent=" + finalSent +
                ", description='" + description + '\'' +
                '}';
    }

}