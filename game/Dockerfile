FROM eclipse-temurin:21-jre
LABEL authors = "Firatt"
EXPOSE 8080
COPY target/game-0.0.1-SNAPSHOT.war game-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java", "-war", "game-0.0.1-SNAPSHOT.war"]
