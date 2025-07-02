📈 Stock Price Notifier – Backend
A real-time backend system to notify users via email and WebSocket when a stock price crosses a defined threshold. Built using Spring Boot, Apache Kafka, MySQL, and WebSocket.

🚀 Features
✅ User alert registration (email, stock symbol, threshold, above/below condition)

🔁 Real-time Kafka-based stock price processing

📬 Email alerts triggered on threshold breach

📡 WebSocket notifications for in-app real-time alerts

🧪 Kafka test endpoint to simulate stock price messages

💾 MySQL persistence for user alert preferences

🧩 Tech Stack
Layer	Technology
Backend	Java, Spring Boot
Messaging	Apache Kafka
Realtime	Spring Boot WebSocket
Database	MySQL
Email	Spring Boot Mail + Gmail SMTP

🛠️ Project Structure
bash
Copy
Edit
📦 backend/
├── src/main/java/com/Stock/demo/
│   ├── controller/         # REST + WebSocket endpoints
│   ├── service/            # Business logic
│   ├── consumer/           # Kafka consumers
│   ├── model/              # Entity classes
│   ├── repository/         # JPA repositories
│   ├── websocket/          # WebSocket config and handler
│   └── DemoApplication.java
├── src/main/resources/
│   └── application.properties
⚙️ How It Works
User registers an alert (email, stock symbol, threshold) via REST API.

Data is stored in MySQL.

Kafka receives stock price updates (manually or from API).

Kafka consumer compares the price with user thresholds.

If a condition is met:

An email alert is sent.

A WebSocket message is pushed to connected frontend clients.

Frontend receives real-time in-app notifications.

📬 Email Setup (Gmail)
Enable 2FA on your Gmail account.

Generate a 16-character App Password.

Add to application.properties:

properties
Copy
Edit
spring.mail.username=your@gmail.com
spring.mail.password=your_app_password
📡 WebSocket Endpoint
WebSocket connection endpoint:

bash
Copy
Edit
ws://localhost:8080/ws/alerts
Clients receive messages when a threshold is breached:

json
Copy
Edit
{
  "symbol": "AAPL",
  "price": 180.0,
  "message": "AAPL has crossed your alert threshold"
}
▶️ Run Instructions
🔹 Backend (Spring Boot)
bash
Copy
Edit
cd backend
./mvnw spring-boot:run
🔹 Start Kafka & Zookeeper
bash
Copy
Edit
# Terminal 1 - Zookeeper
zookeeper-server-start.bat config/zookeeper.properties

# Terminal 2 - Kafka Broker
kafka-server-start.bat config/server.properties

# Create topic
kafka-topics.bat --create --topic stock-prices --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
🧪 Kafka Test API
Simulate a stock price update:

css
Copy
Edit
POST /api/test-publish
Body:
{
  "symbol": "AAPL",
  "price": 180.0
}
✅ This will:

Check user alert conditions.

Trigger email + WebSocket notifications if matched.

📜 License
This project is licensed under the MIT License.
