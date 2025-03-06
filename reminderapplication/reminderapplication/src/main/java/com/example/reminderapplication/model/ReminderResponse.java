package com.example.reminderapplication.model;
import java.util.List;

public class ReminderResponse {
    private String status;
    private List<String> reminders;

    public ReminderResponse(String status, List<String> reminders) {
        this.status = status;
        this.reminders = reminders;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getReminders() {
        return reminders;
    }
}
