# Use an official Maven image with Java 21 to build the application
FROM maven AS build

# Set the working directory in the container
WORKDIR /app

# Copy the POM file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the project source code
COPY src ./src

# Build the application
RUN mvn package

# Use an official OpenJDK runtime with Java 21 as a parent image
FROM openjdk

# Set the working directory to /app
WORKDIR /app

# Copy the packaged JAR file from the build image
COPY --from=build /app/target/saisiedetemps.jar .

# Specify the command to run on container start
CMD ["java", "-jar", "saisiedetemps.jar"]
