package com.example.reminderapplication.model;



public enum ReminderType {
    DRINK_WATER("Time to drink some water!"),
    STRETCH("Let's do some stretches!");

    private final String message;

    ReminderType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

