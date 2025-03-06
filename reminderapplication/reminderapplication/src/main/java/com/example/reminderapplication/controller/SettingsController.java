package com.example.reminderapplication.controller;

import com.example.reminderapplication.userSettings.Settings;
import com.example.reminderapplication.service.SettingsService;
import com.example.reminderapplication.userSettings.UserSettingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/user-settings")
public class SettingsController {

    private final SettingsService service;
    private final UserSettingsRepo userSettingsRepository;
    @Autowired
    public SettingsController(SettingsService service, UserSettingsRepo userSettingsRepository) {
        this.service = service;
        this.userSettingsRepository = userSettingsRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Settings> getUserSettings(@PathVariable String userId) {
        Settings settings = service.getUserSettings(userId);
        if (settings != null) {
            return ResponseEntity.ok(settings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Settings> updateUserSettings(@PathVariable String userId,
                                                           @RequestBody Settings newSettings) {
        Settings updatedSettings = service.updateUserSettings(userId, newSettings);
        return ResponseEntity.ok(updatedSettings);
    }
    @GetMapping("/settings")
    public String showSettingsForm(Model model) {
        model.addAttribute("settings", new Settings());
        return "settings";
    }
    @PostMapping("/settings/save")
    public String saveSettings(
            @RequestParam String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) boolean emailNotifications,
            @RequestParam(required = false) boolean telegramNotifications,
            @RequestParam(required = false) boolean whatsappNotifications,
            @RequestParam String sleepStartTime,
            @RequestParam String sleepEndTime
    ) {
        Settings settings = new Settings();
        settings.setEmail(email);
        settings.setPhoneNumber(phoneNumber);
        settings.setEmailNotifications(emailNotifications);
        settings.setTelegramNotifications(telegramNotifications);
        settings.setWhatsappNotifications(whatsappNotifications);
        settings.setSleepStartTime(sleepStartTime);
        settings.setSleepEndTime(sleepEndTime);

        userSettingsRepository.save(settings);
        return "redirect:/settings";
    }

}
