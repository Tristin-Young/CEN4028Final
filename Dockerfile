FROM openjdk:20 as builder
WORKDIR /CEN4802FINAL
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
COPY src ./src
RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix mvnw
RUN chmod +x mvnw
RUN ./mvnw clean package

FROM openjdk:20

COPY --from=builder /CEN4802FINAL/target/CEN4802Final-1.0.0-SNAPSHOT.jar CEN4802Final-1.0.0-SNAPSHOT.jar
EXPOSE 9000
CMD ["java", "-jar", "CEN4802Final-1.0.0-SNAPSHOT.jar"]



