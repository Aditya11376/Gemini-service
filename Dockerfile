# ─────────────────────────────────────────────
# Stage 1: BUILD  (Maven + Java 21)
# ─────────────────────────────────────────────
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom first (layer cache — dependencies download only if pom changes)
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests -q

# ─────────────────────────────────────────────
# Stage 2: RUN  (Slim JRE only — smaller image)
# ─────────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy built jar from builder stage
COPY --from=builder /app/target/gemini-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8092

# Environment variables (override at runtime via Render dashboard)
ENV GEMINI_API_KEY=your_api_key_here
ENV GEMINI_MODEL=gemini-3.6-flash

# Run
ENTRYPOINT ["java", "-jar", "app.jar"]
