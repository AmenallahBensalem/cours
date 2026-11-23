
# Etape 1 : compilation
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -B -DskipTests package

# Etape 2 : execution
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Installer curl pour le HEALTHCHECK
RUN apt-get update && \
    apt-get install -y --no-install-recommends curl && \
    rm -rf /var/lib/apt/lists/*

# Creer un utilisateur non-root
RUN groupadd --system appgroup && \
    useradd --system --gid appgroup appuser

# Executer l'application sans privileges root
USER appuser

# Port de Spring Boot
EXPOSE 8081

# Verification automatique de la sante
HEALTHCHECK --interval=10s --timeout=5s --start-period=60s --retries=3 \
    CMD curl --fail --silent --show-error http://localhost:8081/cours/health || exit 1

# Demarrer Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
