FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY target/*.jar app.jar

# Porta que Render vai passar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
