package yandex_problems;

public class Log {
    private int day;
    private int hour;
    private int minute;
    char status;

    public Log() {
    }

    public Log(int day, int hour, int minute, char status) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.status = status;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public char getStatus() {
        return status;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
