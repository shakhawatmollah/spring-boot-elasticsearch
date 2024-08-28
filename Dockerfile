FROM openjdk:17-jdk

WORKDIR /app

COPY target/spring-boot-elasticsearch-1.0.0.jar /app/spring-boot-elasticsearch.jar

EXPOSE 8080

CMD ["java", "-jar", "spring-boot-elasticsearch.jar"]