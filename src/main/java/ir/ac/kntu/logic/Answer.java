package ir.ac.kntu.logic;

import java.time.LocalDateTime;
import ir.ac.kntu.util.ScannerWrapper;

public class Answer {

    private DateTime sentDateTime;

    private String description;

    private boolean finalSent;

    public Answer() {
    }

    public Answer(DateTime sentDateTime, String description, boolean finalSent) {
        this.sentDateTime = sentDateTime;
        this.description = description;
        this.finalSent = finalSent;
    }

    public DateTime getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(DateTime sentDateTime) {
        this.sentDateTime = sentDateTime;
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

    public static Answer read() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTime dt = new DateTime(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth(),
                dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond());
        String answer = ScannerWrapper.readString("Enter answer: ");
        boolean fs = ScannerWrapper.readBoolean("Is final set? (true/false)");

        return new Answer(dt, answer, fs);
    }
}