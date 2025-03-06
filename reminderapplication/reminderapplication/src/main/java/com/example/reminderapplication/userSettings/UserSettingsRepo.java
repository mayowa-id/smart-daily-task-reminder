package com.example.reminderapplication.userSettings;

import com.example.reminderapplication.userSettings.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserSettingsRepo extends JpaRepository<Settings, Long> {
    Optional<Settings> findByUserId(String userId);
}
