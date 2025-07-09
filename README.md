# 📈 Stock Price Notifier – Backend

A real-time backend system that notifies users via **email** and **WebSocket** when a stock price crosses a defined threshold. Built using **Spring Boot**, **Apache Kafka**, **MySQL**, and **WebSocket** technologies.

---

## 🚀 Features

- ✅ Register user alerts (email, stock symbol, threshold, above/below condition)
- 🔁 Real-time stock price processing using Apache Kafka
- 📬 Email alerts triggered on threshold breach
- 📡 WebSocket notifications for in-app real-time alerts
- 🧪 Test endpoint to simulate stock price updates via Kafka
- 💾 MySQL database for storing user alert preferences

---

## 🧩 Tech Stack

| Layer       | Technology                  |
|-------------|------------------------------|
| Backend     | Java, Spring Boot            |
| Messaging   | Apache Kafka                 |
| Real-time   | Spring Boot WebSocket        |
| Database    | MySQL                        |
| Email       | Spring Mail + Gmail SMTP     |

---

## 🛠️ Project Structure

📦 backend/
├── src/main/java/com/stock/demo/
│ ├── controller/ # REST + WebSocket endpoints
│ ├── service/ # Business logic
│ ├── consumer/ # Kafka consumers
│ ├── model/ # Entity classes
│ ├── repository/ # JPA repositories
│ ├── websocket/ # WebSocket config and handler
│ └── DemoApplication.java
├── src/main/resources/
│ └── application.properties




---

## ⚙️ How It Works

1. 🧾 User registers an alert (email, stock symbol, threshold) via REST API.
2. 🗃️ Alert data is saved in **MySQL**.
3. 📩 Kafka receives stock price updates (manually or via API).
4. 🧠 Kafka consumer compares price with stored user thresholds.
5. ✅ If a condition is met:
   - An **email** alert is sent.
   - A **WebSocket** message is pushed to connected clients.
6. 🖥️ Frontend displays **real-time in-app notifications**.

---

## 📬 Email Setup (Gmail)

To enable email alerts via Gmail:

1. Enable **2-Factor Authentication** on your Gmail account.
2. Generate a **16-character App Password**.
3. Add the following to application.properties:

properties
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password

🔹 Start Kafka & Zookeeper
# Terminal 1 - Zookeeper
zookeeper-server-start.bat config/zookeeper.properties

# Terminal 2 - Kafka Broker
kafka-server-start.bat config/server.properties

# Create Kafka Topic
kafka-topics.bat --create --topic stock-prices --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
