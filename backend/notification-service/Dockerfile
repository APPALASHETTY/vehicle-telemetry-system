# ---------- Stage 1: Application Artifact ----------
FROM eclipse-temurin:21-jre AS artifact

WORKDIR /app

# Copy pre-built JAR downloaded from Nexus
COPY app.jar /app/app.jar


# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Copy application artifact from Stage 1
COPY --from=artifact /app/app.jar /app/app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

