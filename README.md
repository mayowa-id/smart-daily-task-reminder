# Smart Daily Task Reminder
Smart Daily Task Reminder is a Java Spring Boot application that helps users schedule and receive automated reminders for essential daily activities like drinking water and stretching. Users can configure reminders based on their preferences and receive notifications via Email, Telegram, and WhatsApp (Twilio).

## Features

- 🕒 **Scheduled Reminders**: Drink water (every 2 hours) and stretch (every 1 hour).
- 🌙 **Sleeping Hours Support**: No reminders during user-configured sleep time.
- 📩 **Multiple Notification Methods**:
  - **Email Notifications** (via Spring Mail)
  - **Telegram Bot Messages**
  - **WhatsApp Notifications** (via Twilio API)
- 🎛 **User Configurable Settings** (via Web UI)
- 🛠 **Database Storage**: Stores user preferences using H2 database.
- 📜 **API Documentation**: Swagger UI for API testing.

## Tech Stack

- **Backend:** Java, Spring Boot
- **Database:** H2 (for development)
- **Messaging:** Spring Mail (Email), Telegram API, Twilio API (WhatsApp)
- **API Documentation:** Swagger UI

## Installation & Setup

### Prerequisites

- Java 17+
- Maven
- A Telegram Bot (get token from @BotFather)
- Twilio Account (for WhatsApp messaging)

### Steps

1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/smart-daily-task-reminder.git
   cd smart-daily-task-reminder
   ```
2. **Configure Environment Variables** in `application.yml`:
   ```yaml
   telegram:
     bot-token: YOUR_TELEGRAM_BOT_TOKEN
   twilio:
     account-sid: YOUR_TWILIO_SID
     auth-token: YOUR_TWILIO_AUTH_TOKEN
     phone-number: YOUR_TWILIO_WHATSAPP_NUMBER
   spring:
     mail:
       username: YOUR_EMAIL
       password: YOUR_EMAIL_PASSWORD
   ```
3. **Build & Run the Application:**
   ```sh
   mvn spring-boot:run
   ```
4. **Access API Documentation:**
   - Swagger UI: `http://localhost:9090/swagger-ui.html`
   - H2 Database Console: `http://localhost:9090/h2-console`

## API Endpoints

| Method | Endpoint               | Description                |
| ------ | ---------------------- | -------------------------- |
| `GET`  | `/api/reminders/check` | Check upcoming reminders   |
| `POST` | `/api/reminders/send`  | Trigger reminders manually |
| `GET`  | `/api/settings`        | View user settings         |
| `POST` | `/api/settings/update` | Update user settings       |

## Usage

1. **Start the application** and configure your notification settings via UI.
2. **Reminders will be sent automatically** based on the configured schedule.
3. Use Postman or Swagger UI to test API manually.

## Future Improvements

- 📝 Add a mobile app integration.
- 🎯 AI-based activity tracking for custom reminders.
- 🛡 More security features (OAuth2, JWT, etc.).

## License

This project is licensed under the MIT License.

---

# API Documentation (API\_DOCS.md)

## API Reference

### 1. Check Reminders

**Endpoint:** `GET /api/reminders/check`

- **Description:** Checks if reminders should be sent based on user settings.
- **Response Example:**
  ```json
  {
    "status": "Reminders checked",
    "reminders": ["Time to drink water!", "Time to stretch!"]
  }
  ```

### 2. Send Reminders (Manually Trigger)

**Endpoint:** `POST /api/reminders/send`

- **Description:** Manually triggers reminder notifications for all users.
- **Response Example:**
  ```json
  {
    "message": "Reminders sent successfully"
  }
  ```

### 3. Get User Settings

**Endpoint:** `GET /api/settings`

- **Description:** Fetches the user's notification and reminder settings.
- **Response Example:**
  ```json
  {
    "email": "user@example.com",
    "phoneNumber": "+1234567890",
    "emailNotifications": true,
    "telegramNotifications": true,
    "whatsappNotifications": false,
    "sleepStartTime": "23:00",
    "sleepEndTime": "07:00"
  }
  ```

### 4. Update User Settings

**Endpoint:** `POST /api/settings/update`

- **Description:** Updates the user's reminder preferences.
- **Request Body Example:**
  ```json
  {
    "email": "user@example.com",
    "phoneNumber": "+1234567890",
    "emailNotifications": true,
    "telegramNotifications": true,
    "whatsappNotifications": true,
    "sleepStartTime": "22:00",
    "sleepEndTime": "06:30"
  }
  ```
- **Response Example:**
  ```json
  {
    "message": "Settings updated successfully"
  }
  ```

