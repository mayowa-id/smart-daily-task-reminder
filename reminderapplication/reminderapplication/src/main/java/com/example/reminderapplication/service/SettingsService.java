package com.example.reminderapplication.service;
import com.example.reminderapplication.userSettings.Settings;
import com.example.reminderapplication.userSettings.UserSettingsRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingsService {
    private final UserSettingsRepo repository;

    public SettingsService(UserSettingsRepo repository) {
        this.repository = repository;
    }

    public Settings getUserSettings(String userId) {
        return repository.findByUserId(userId).orElse(null);
    }

    public Settings updateUserSettings(String userId,Settings newSettings) {
        Optional<Settings> existingSettings = repository.findByUserId(userId);
        if (existingSettings.isPresent()) {
            Settings settings = existingSettings.get();
            settings.setDrinkWaterInterval(newSettings.getDrinkWaterInterval());
            settings.setStretchInterval(newSettings.getStretchInterval());
            settings.setSleepStartTime(newSettings.getSleepStartTime());
            settings.setSleepEndTime(newSettings.getSleepEndTime());
            settings.setEmailNotifications(newSettings.isEmailNotifications());
            settings.setTelegramNotifications(newSettings.isTelegramNotifications());
            settings.setWhatsappNotifications(newSettings.isWhatsappNotifications());

            return repository.save(settings);
        } else {
            newSettings.setUserId(userId);
            return repository.save(newSettings);
        }
    }
}
