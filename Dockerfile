FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} CyberDoneDeviceMicroservice.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/CyberDoneDeviceMicroservice.jar"]