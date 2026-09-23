# Build
FROM maven:3.9.11-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml ./
RUN mvn -q -B dependency:go-offline

COPY src ./src
RUN mvn -q -B -DskipTests package \
  && mv target/*.jar app.jar

# Runtime
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

RUN useradd --system --create-home --uid 10001 appuser
USER appuser

COPY --from=build /app/app.jar ./app.jar

EXPOSE 8080

ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]
