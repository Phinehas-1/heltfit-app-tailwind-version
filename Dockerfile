FROM eclipse-temurin:21-jre-alpine
WORKDIR /usr/app
COPY ./target/heltfit-app-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "heltfit-app-0.0.1-SNAPSHOT.jar"]