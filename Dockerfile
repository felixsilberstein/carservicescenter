# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine
# copy fat WAR
COPY target/car-service-center-1.0-SNAPSHOT.jar app.jar
#COPY car-service-center-1.0-SNAPSHOT.jar /app.jar
# runs application
#java -jar target/car-service-center-1.0-SNAPSHOT.jar
CMD ["/usr/bin/java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "-Dspring.profiles.active=default", "/app.jar"]
