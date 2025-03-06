package com.example.reminderapplication.service;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.whatsapp.sender}")
    private String whatsappSender;

    public void sendWhatsAppReminder(String recipient, String message) {
        if (!recipient.startsWith("whatsapp:")) {
            recipient = "whatsapp:" + recipient;
        }

        Message.creator(
                new PhoneNumber(recipient),
                new PhoneNumber(whatsappSender),
                message
        ).create();
    }
}
