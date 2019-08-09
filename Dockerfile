# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine

COPY target/car-service-center-1.0-SNAPSHOT.jar /app.jar

#COPY car-service-center-1.0-SNAPSHOT.jar /app.jar
# runs application
#java -jar target/car-service-center-1.0-SNAPSHOT.jar
CMD ["/usr/bin/java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=default", "-jar", "/app.jar"]
