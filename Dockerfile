FROM openjdk:11-jre-alpine
COPY ${JAR_FILE} app.jar
CMD ["JAVA","-jar","/app.jar"]