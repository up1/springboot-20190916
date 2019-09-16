FROM openjdk:8u222-jre-stretch
COPY ./target/docker-0.0.1-SNAPSHOT.jar /work
EXPOSE 8080
WORKDIR /work
CMD ["java", "-jar", "docker-0.0.1-SNAPSHOT.jar"]