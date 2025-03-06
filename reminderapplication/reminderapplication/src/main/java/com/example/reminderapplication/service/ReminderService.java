package com.example.reminderapplication.service;

import com.example.reminderapplication.model.ReminderResponse;
import com.example.reminderapplication.model.ReminderType;
import com.example.reminderapplication.userSettings.Settings;
import com.example.reminderapplication.userSettings.UserSettingsRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReminderService {

    private final UserSettingsRepo userSettingsRepository;
    private final EmailService emailService;
    private final TelegramService telegramService;
    private final WhatsappService whatsappService;
    private static final LocalTime SLEEP_START = LocalTime.of(23, 0);
    private static final LocalTime SLEEP_END = LocalTime.of(7, 0);
    private static final Duration WATER_INTERVAL = Duration.ofHours(2);
    private static final Duration STRETCH_INTERVAL = Duration.ofHours(1);

    private LocalDateTime lastWaterReminder = LocalDateTime.now().minus(WATER_INTERVAL);
    private LocalDateTime lastStretchReminder = LocalDateTime.now().minus(STRETCH_INTERVAL);

    public ReminderService(UserSettingsRepo userSettingsRepository,
                           EmailService emailService,
                           TelegramService telegramService,
                           WhatsappService whatsappService) {
        this.userSettingsRepository = userSettingsRepository;
        this.emailService = emailService;
        this.telegramService = telegramService;
        this.whatsappService = whatsappService;
    }
    @Scheduled(fixedRate = 60000) // Check every 1 minute
    public ReminderResponse sendReminders() {
        List<Settings> users = userSettingsRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = now.toLocalTime();

        for (Settings user : users) {
            LocalTime sleepStart = LocalTime.parse(user.getSleepStartTime());
            LocalTime sleepEnd = LocalTime.parse(user.getSleepEndTime());

            if (!isSleepingHours(currentTime, sleepStart, sleepEnd)) {
                String reminderMessage = generateReminderMessage(now);

                if (!reminderMessage.isEmpty()) {
                    if (user.isEmailNotifications()) {
                        sendEmailReminder(user, reminderMessage);
                    }
                    if (user.isTelegramNotifications()) {
                        sendTelegramReminder(user, reminderMessage);
                    }
                    if (user.isWhatsappNotifications()){
                        sendWhatsAppReminder(user, reminderMessage);
                    }
                }
            }
        }
        return null;
    }
    private void sendEmailReminder(Settings user, String message) {
        System.out.println("📧 Sending email to " + user.getEmail());
        emailService.sendReminderEmail(
                user.getEmail(),
                "Reminder: Stay Healthy!",
                message
        );
    }
    private void sendTelegramReminder(Settings user, String message) {
        System.out.println("📢 Sending Telegram message to user: " + user.getUserId());
        telegramService.sendReminder("Reminder: Stay Healthy!  " + message);
    }
    private void sendWhatsAppReminder(Settings user, String message) {
            System.out.println("📩 Sending WhatsApp message to " + user.getPhoneNumber());
            whatsappService.sendWhatsAppReminder(user.getPhoneNumber(), "Reminder: Stay Healthy! " + message);
    }
    private boolean isSleepingHours(LocalTime time, LocalTime sleepStart, LocalTime sleepEnd) {
        return !time.isBefore(sleepEnd) && time.isAfter(sleepStart);
    }

    private String generateReminderMessage(LocalDateTime now) {
        StringBuilder message = new StringBuilder();

        if (Duration.between(lastWaterReminder, now).compareTo(WATER_INTERVAL) >= 0) {
            message.append("<p>It's time to <strong>drink water</strong>! 💧</p>");
            lastWaterReminder = now;
        }

        if (Duration.between(lastStretchReminder, now).compareTo(STRETCH_INTERVAL) >= 0) {
            message.append("<p>Don't forget to <strong>stretch</strong>! 🏃‍♂️</p>");
            lastStretchReminder = now;
        }

        return message.toString();
    }
    @Scheduled(fixedRate = 60000) // Runs every minute
    public void scheduledReminderCheck() {
        checkReminders();
    }

    public ReminderResponse checkReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = now.toLocalTime();

        if (isSleepingHours(currentTime)) {
            return new ReminderResponse("Sleeping hours - no reminders", new ArrayList<>());
        }

        List<String> reminders = new ArrayList<>();

        if (Duration.between(lastWaterReminder, now).compareTo(WATER_INTERVAL) >= 0) {
            reminders.add(ReminderType.DRINK_WATER.getMessage());
            lastWaterReminder = now;
        }

        if (Duration.between(lastStretchReminder, now).compareTo(STRETCH_INTERVAL) >= 0) {
            reminders.add(ReminderType.STRETCH.getMessage());
            lastStretchReminder = now;
        }

        return new ReminderResponse("Reminders checked", reminders);
    }

    private boolean isSleepingHours(LocalTime time) {
        return (time.equals(SLEEP_START) || time.isAfter(SLEEP_START)) || time.isBefore(SLEEP_END);
    }
}
