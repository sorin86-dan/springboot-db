#Run first 'mvn package' to generate the jar file

FROM openjdk:11-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} db.jar
ENTRYPOINT ["java","-jar","/db.jar"]
EXPOSE 8080
