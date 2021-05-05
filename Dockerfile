FROM openjdk:11.0.11-oraclelinux7
COPY ${JAR_FILE} app.jar
CMD ["JAVA","-jar","/app.jar"]