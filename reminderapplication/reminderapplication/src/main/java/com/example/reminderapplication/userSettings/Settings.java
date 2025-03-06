package com.example.reminderapplication.userSettings;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "user_settings")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId; // For multi-user support (could be email or UUID)
    private String email;
    private String phoneNumber;


    private int drinkWaterInterval; // In minutes
    private int stretchInterval; // In minutes

    private String sleepStartTime; // e.g., "22:00" (10 PM)
    private String sleepEndTime;   // e.g., "06:00" (6 AM)

    private boolean emailNotifications;
    private boolean telegramNotifications;
    private boolean whatsappNotifications;
}
