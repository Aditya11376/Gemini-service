# Gemini Service

A Spring Boot microservice that uses Google's Gemini AI model to generate responses based on user prompts.

## 🚀 Features

- REST API endpoint to interact with Gemini AI
- Environment-variable-based configuration (no hardcoded secrets)
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

   Copy `.env.example` to `.env` and fill in your values:
   ```bash
   cp .env.example .env
   ```

   Then set your Gemini API key:
   ```
   GEMINI_API_KEY=your_actual_api_key_here
   GEMINI_MODEL=gemini-3.6-flash   # optional
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

### Generate AI Response

```
POST /gemini/generate
Content-Type: application/json

{
    "message":"Pm of india?"
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
- `.env` files are gitignored — use `.env.example` as a reference template
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
