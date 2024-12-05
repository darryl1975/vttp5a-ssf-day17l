FROM openjdk:23-jdk-oracle AS compiler

ARG COMPILE_DIR=/code_folder

WORKDIR ${COMPILE_DIR}

COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn

# RUN chmod a+x ./mvnw
RUN ./mvnw clean package -Dmaven.skip.tests=true

ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}

# ENTRYPOINT ./mvnw spring-boot:RUN
ENTRYPOINT ["java", "-jar", "target/vttp5a-ssf-day17l-0.0.1-SNAPSHOT.jar"]

# stage 2
FROM openjdk:23-jdk-oracle

ARG DEPLOY_DIR=/app

WORKDIR ${DEPLOY_DIR}

COPY --from=compiler /code_folder/target/vttp5a-ssf-day17l-0.0.1-SNAPSHOT.jar vttp5a-day17l.jar

ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "vttp5a-day17l.jar"]