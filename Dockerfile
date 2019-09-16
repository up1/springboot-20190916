FROM openjdk:8u222-jre-stretch
COPY ./target/docker-0.0.1-SNAPSHOT.jar /work/xx.jar
EXPOSE 8080
WORKDIR /work
CMD ["java", "-jar", "xx.jar"]