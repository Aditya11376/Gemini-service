# Gemini Microservice
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)
[![Render](https://img.shields.io/badge/Deployment-Render-46e3b7.svg)](https://render.com)
[![Status](https://img.shields.io/badge/Port-8092-purple.svg)]()

A Spring Boot microservice that uses Google's Gemini AI model to generate responses based on user prompts.

## 🚀 Features

- REST API endpoint to interact with Gemini AI
- Environment-variable-based configuration
- Input validation and global exception handling
- Runs on port `8092`

## 📋 Prerequisites

- Java 21
- Maven
- A Gemini API key ([get one here](https://aistudio.google.com/app/apikey))

## ⚙️ Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/Aditya11376/Gemini-service.git
   cd Gemini-service
   ```

2. **Configure environment variables**

   Create `.env` and fill in your values:
   Then set your Gemini API key:
   ```
   GEMINI_API_KEY=your_actual_api_key_here
   GEMINI_MODEL=gemini-3.6-flash  (LLM)
   ```

   > ⚠️ **Never commit `.env` or any file containing real API keys to version control!**

3. **Run the service**
   ```bash
   # Set environment variable (Windows PowerShell)
   $env:GEMINI_API_KEY="your_api_key"

   # Or on Linux/macOS
   export GEMINI_API_KEY="your_api_key"

   # Then start the app
   ./mvnw spring-boot:run
   ```

## 📡 API Endpoints

### Check Health

```
GET {"/", "/health", "/gemini/health"}
```

**Response:**
```json
{
   "status":"UP",
   "service": "gemini-service",
   "port": 8092,
   "timestamp": "04-11-2026, 15:04 PM"
}
```


### Generate AI Response

```
POST /gemini/generate
Content-Type: application/json

{
    "message":"Pm of India?"
}
```

**Response:**
```json
{
    "reply": "The current Prime Minister of India is **Narendra Modi**. He has been in office since May 2014."
}
```

## 🔐 Security Notes

- The API key is injected via the `GEMINI_API_KEY` environment variable — **never hardcoded**
- `.env` files are gitignored
- In production, use a secrets manager (e.g., AWS Secrets Manager, GCP Secret Manager)

## 🏗️ Project Structure

```
src/
├── main/java/com/system/gemini/
│   ├── config/         # ChatClient bean configuration
│   ├── controller/     # REST endpoints
│   ├── dto/            # Request/Response models
│   ├── exception/      # Global exception handler
│   └── service/        # Business logic
└── main/resources/
    └── application.properties
```

---

## 🛡️ License & Copyright

&copy; 2026 **GUPTA SOFTWORKS ORGANISATION**. All rights reserved.  
