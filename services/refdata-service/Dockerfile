# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy parent POM
COPY pom.xml ./

# Copy entire platform and services directories
COPY platform ./platform
COPY services ./services

# Build only this module (adjust the path for each service)
WORKDIR /workspace
RUN mvn -pl platform/config-server -am clean package -DskipTests
# ☝️ Change this line for each service:
# config-server: -pl platform/config-server
# discovery-server: -pl platform/discovery-server
# api-gateway: -pl platform/api-gateway
# auth-service: -pl services/auth-service
# refdata-service: -pl services/refdata-service
# swaps-service: -pl services/swaps-service

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/platform/config-server/target/*.jar /app/app.jar
# ☝️ Change this path for each service too

ENV JAVA_OPTS=""
EXPOSE 8888
# ☝️ Change port for each service

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]