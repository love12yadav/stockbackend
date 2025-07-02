Stock Price Notifier 🚨
A real-time full-stack application to notify users via email when a stock price crosses a defined threshold using Kafka, Spring Boot, and React.

🚀 Features
✅ User alert registration (email, phone, stock symbol, threshold, above/below)

🔁 Real-time Kafka-based stock price processing

📬 Email alerts triggered on threshold breach

🧪 Kafka message trigger test button

📊 React frontend with form + live test

🧩 Tech Stack
Layer	Tech
Backend	Java, Spring Boot, Kafka
Frontend	React (Vite), Tailwind CSS
Broker	Apache Kafka
Database	MySQL
Email	Spring Boot Mail + Gmail SMTP

🛠️ Project Structure
css
Copy
Edit
📦 stock-price-notifier/
├── backend/
│   ├── src/main/java/com/Stock/demo/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── consumer/
│   │   ├── model/
│   │   ├── repository/
│   │   └── DemoApplication.java
│   └── application.properties
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   └── App.jsx
│   └── vite.config.js
└── README.md
⚙️ How It Works
User registers an alert on the frontend → stored in MySQL.

Kafka receives stock price updates (manual or API).

Kafka consumer checks user preferences and sends email if conditions match.

Emails sent via Gmail SMTP with logs in console.

📬 Email Setup (Gmail)
Enable App Passwords in Gmail.

Use the 16-character key in application.properties:

properties
Copy
Edit
spring.mail.username=your@gmail.com
spring.mail.password=your_app_password
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
# Terminal 1
zookeeper-server-start.bat config/zookeeper.properties

# Terminal 2
kafka-server-start.bat config/server.properties
kafka-topics.bat --create --topic stock-prices --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
🔹 Frontend (React + Vite)
bash
Copy
Edit
cd frontend
npm install
npm run dev


🤝 Contributions
PRs welcome! Feel free to suggest enhancements.

📜 License
MIT License

