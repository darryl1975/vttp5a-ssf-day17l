FROM openjdk:21-jdk-oracle

ARG DEPLOY_DIR=/app

WORKDIR ${DEPLOY_DIR}

COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn ./mvn

RUN ./mvnw -Dmaven.skip.tests=true clean package

ENV SERVER_PORT 3000

EXPOSE ${SERVER_PORT}

# ENTRYPOINT ./mvnw spring-boot:RUN
ENTRYPOINT java -jar target/vttp5a-ssf-day17l-0.0.1-SNAPSHOT.jar