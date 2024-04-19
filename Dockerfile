# Use a specific version of the Maven base image with OpenJDK 17
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY . .

# Build the application with Maven
RUN mvn clean install

# Use the same base image for the runtime stage
FROM maven:3.8.5-openjdk-17

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/greenbay-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

# Specify the command to run the application
CMD ["java", "-jar", "greenbay-0.0.1-SNAPSHOT.jar"]
