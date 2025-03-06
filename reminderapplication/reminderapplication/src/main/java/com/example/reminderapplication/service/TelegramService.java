package com.example.reminderapplication.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramService {

    private static final String BOT_TOKEN = "8090355677:AAGV4zRIKjlETmZ7kcjBNHlG6Z6tWPrrrbI";
    private static final String CHAT_ID = "7935546166";
    private static final String TELEGRAM_API_URL = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";

    public void sendReminder(String message) {
        String url = TELEGRAM_API_URL + "?chat_id=" + CHAT_ID + "&text=" + message;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, String.class);
    }
}
