# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copy parent POM
COPY pom.xml ./

# Copy all module POMs
COPY platform/config-server/pom.xml ./platform/config-server/
COPY platform/discovery-server/pom.xml ./platform/discovery-server/
COPY platform/api-gateway/pom.xml ./platform/api-gateway/
COPY services/auth-service/pom.xml ./services/auth-service/
COPY services/refdata-service/pom.xml ./services/refdata-service/
COPY services/swaps-service/pom.xml ./services/swaps-service/

# Download dependencies
RUN mvn dependency:go-offline -B || true

# Copy source code
COPY platform/discovery-server/src ./platform/discovery-server/src

# Build only this module
RUN mvn -pl platform/discovery-server -am clean package -DskipTests

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/platform/discovery-server/target/*.jar /app/app.jar
ENV JAVA_OPTS=""
EXPOSE 8761
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]