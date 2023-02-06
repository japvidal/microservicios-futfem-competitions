FROM openjdk:8-jdk-alpine
ADD target/microservicios-futfem-competitions-0.0.1-SNAPSHOT.jar futfem_competitions.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/futfem_competitions.jar"]


