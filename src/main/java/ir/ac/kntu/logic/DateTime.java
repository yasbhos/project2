package ir.ac.kntu.logic;

import java.time.LocalDateTime;

import ir.ac.kntu.util.ScannerWrapper;

public class DateTime implements Comparable<DateTime> {

    private int year;

    private int month;

    private int day;

    private int hour;

    private int minute;

    private int second;

    public DateTime(int year, int month, int day, int hour, int minute, int second) {
        checkAndSetDateTime(year, month, day, hour, minute, second);
    }

    public DateTime(DateTime dateTime) {
        this.year = dateTime.year;
        this.month = dateTime.month;
        this.day = dateTime.day;
        this.hour = dateTime.hour;
        this.minute = dateTime.minute;
        this.second = dateTime.second;
    }

    private void checkAndSetDateTime(int year, int month, int day, int hour, int minute, int second) {
        if (checkInputs(year, month, day, hour, minute, second)) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        } else {
            this.year = 0;
            this.month = 0;
            this.day = 0;
            this.hour = 0;
            this.minute = 0;
            this.second = 0;
        }
    }

    private boolean checkInputs(int year, int month, int day, int hour, int minute, int second) {
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }
        if (month > 6 && day == 31) {
            return false;
        }
        if (month == 12 && day == 30 && !isLeapYear(year)) {
            return false;
        }
        if (hour < 0 || hour > 23) {
            return false;
        }
        if (minute < 0 || minute > 59) {
            return false;
        }
        if (second < 0 || second > 59) {
            return false;
        }
        return true;
    }

    public void setYear(int year) {
        checkAndSetDateTime(year, this.month, this.day, this.hour, this.minute, this.second);
    }

    public int getYear() {
        return year;
    }

    public void setMonth(int month) {
        checkAndSetDateTime(this.year, month, this.day, this.hour, this.minute, this.second);
    }

    public int getMonth() {
        return month;
    }

    public void setDay(int day) {
        checkAndSetDateTime(this.year, this.month, day, this.hour, this.minute, this.second);
    }

    public int getDay() {
        return day;
    }

    public void setHour(int hour) {
        checkAndSetDateTime(this.year, this.month, this.day, hour, this.minute, this.second);
    }

    public int getHour() {
        return hour;
    }

    public void setMinute(int minute) {
        checkAndSetDateTime(this.year, this.month, this.day, this.hour, minute, this.second);
    }

    public int getMinute() {
        return minute;
    }

    public void setSecond(int second) {
        checkAndSetDateTime(this.year, this.month, this.day, this.hour, this.minute, second);
    }

    public int getSecond() {
        return second;
    }

    private boolean isLeapYear(int year) {
        double a = 0.025;
        double b = 266;
        double leapDays0, leapDays1;
        int frac0, frac1;
        if (year > 0) {
            leapDays0 = ((year + 38) % 2820) * 0.24219 + a;  //0.24219 ~ extra days of one year
            leapDays1 = ((year + 39) % 2820) * 0.24219 + a;  //38 days is the difference of epoch to
            //2820-year cycle
        } else if (year < 0) {
            leapDays0 = ((year + 39) % 2820) * 0.24219 + a;
            leapDays1 = ((year + 40) % 2820) * 0.24219 + a;
        } else {
            return false;
        }

        frac0 = (int) ((leapDays0 - (int) (leapDays0)) * 1000);
        frac1 = (int) ((leapDays1 - (int) (leapDays1)) * 1000);

        return frac0 <= b && frac1 > b;
    }

    public static DateTime readDate(String message) {
        System.out.println(message);

        int year = ScannerWrapper.readInt("Year: ");
        int month = ScannerWrapper.readInt("Month: ");
        int day = ScannerWrapper.readInt("Day: ");

        return new DateTime(year, month, day, 0, 0, 0);
    }

    public static DateTime readDateTime(String message) {
        System.out.println(message);

        int year = ScannerWrapper.readInt("Year: ");
        int month = ScannerWrapper.readInt("Month: ");
        int day = ScannerWrapper.readInt("Day: ");
        int hour = ScannerWrapper.readInt("Hour: ");
        int minute = ScannerWrapper.readInt("Minute: ");
        int second = ScannerWrapper.readInt("Second: ");

        return new DateTime(year, month, day, hour, minute, second);
    }

    public DateTime deepCopy() {
        return new DateTime(year, month, day, hour, minute, second);
    }

    @Override
    public int compareTo(DateTime other) {
        if (this.year > other.year) {
            return 1;
        } else if (this.year < other.year) {
            return -1;
        }
        if (this.month > other.month) {
            return 1;
        } else if (this.month < other.month) {
            return -1;
        }
        if (this.day > other.day) {
            return 1;
        } else if (this.day < other.day) {
            return -1;
        }
        if (this.hour > other.hour) {
            return 1;
        } else if (this.hour < other.hour) {
            return -1;
        }
        if (this.minute > other.minute) {
            return 1;
        } else if (this.minute < other.minute) {
            return -1;
        }
        if (this.second > other.second) {
            return 1;
        } else if (this.second < other.second) {
            return -1;
        }

        return 0;
    }

    public static DateTime now() {
        LocalDateTime ldt = LocalDateTime.now();

        return new DateTime(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), ldt.getSecond());
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + day;
        result = prime * result + hour;
        result = prime * result + minute;
        result = prime * result + month;
        result = prime * result + second;
        result = prime * result + year;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DateTime other = (DateTime) obj;
        if (day != other.day) {
            return false;
        }
        if (hour != other.hour) {
            return false;
        }
        if (minute != other.minute) {
            return false;
        }
        if (month != other.month) {
            return false;
        }
        if (second != other.second) {
            return false;
        }
        if (year != other.year) {
            return false;
        }

        return true;
    }

}