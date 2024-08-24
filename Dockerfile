# Use the official OpenJDK image based on Oracle Linux 8
FROM openjdk:21-jdk-oraclelinux8

# Add the Spring Boot application's JAR file to the container
# The ADD instruction can also be used to download files from URLs and extract TAR files
ADD target/encryption-0.0.1-SNAPSHOT.jar encryption-0.0.1-SNAPSHOT.jar

# Expose port 80 to the outside world
# This doesn't actually publish the port, but it serves as documentation and helps with tools that automatically configure network settings
EXPOSE 80

# Run the application
# ENTRYPOINT defines the command that will run when the container starts
ENTRYPOINT ["java", "-jar", "encryption-0.0.1-SNAPSHOT.jar"]

# Optional: Set environment variables
# You can set environment variables that the application needs
# ENV SPRING_PROFILES_ACTIVE=prod

# Optional: Set the working directory
# You can set the working directory for the application
# WORKDIR /usr/src/app

# Optional: Copy additional files or directories
# You can copy additional files or directories into the container
# COPY config /usr/src/app/config

# Optional: Run additional commands to install dependencies or perform setup tasks
# You can run commands to install additional software or perform setup tasks
# RUN apt-get update && apt-get install -y curl

# Optional: Define a health check to monitor the container's health
# You can define a health check to ensure the container is running properly
# HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
#   CMD curl -f http://localhost/actuator/health || exit 1

# Optional: Define a volume to persist data
# You can define a volume to persist data between container restarts
# VOLUME /var/lib/myapp

# Optional: Add metadata to the image
# You can add metadata to the image, such as a description, version, or maintainer
# LABEL maintainer="your-email@example.com"
# LABEL description="My Spring Boot application"
# LABEL version="0.0.1-SNAPSHOT"
