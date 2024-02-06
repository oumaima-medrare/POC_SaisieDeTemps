# Stage 1: Build Stage
FROM maven AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Run Stage
FROM adoptopenjdk

WORKDIR /app

COPY --from=build /app/target/*.jar saisiedetemps.jar

CMD ["java", "-jar", "saisiedetemps.jar"]
