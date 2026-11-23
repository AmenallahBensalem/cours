
# Étape 1 : compilation
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -B -DskipTests package

# Étape 2 : exécution
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Utilisateur non-root
RUN groupadd --system appgroup && \
    useradd --system --gid appgroup appuser

USER appuser

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
