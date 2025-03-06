package com.example.reminderapplication.controller;
import com.example.reminderapplication.model.ReminderResponse;
import com.example.reminderapplication.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reminders")
@Tag(name = "Reminders", description = "APIs for checking and sending reminders")
public class ReminderController {
    private final ReminderService reminderService;
    @Autowired
    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }
    @GetMapping("/check")
    @Operation(summary = "Check reminders", description = "Returns upcoming reminders if applicable.")
    public ReminderResponse checkReminders() {
        return reminderService.checkReminders();
    }

    @PostMapping("/send")
    @Operation(summary = "Send reminders manually", description = "Triggers reminders for all users manually.")
    public ReminderResponse sendReminder() {
        return reminderService.sendReminders();
    }
}


