FROM openjdk:8-jdk-oracle

ARG JAR_FILE=target/*.jar

COPY target/*jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]