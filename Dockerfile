# Use an official Tomcat runtime as a parent image
FROM tomcat:10-jdk17-openjdk-slim

# Set the working directory in the container
WORKDIR /usr/local/tomcat/webapps

# Copy the application WAR file into the container at /usr/local/tomcat/webapps
COPY target/AutomateRoop.war /usr/local/tomcat/webapps/app.war

# Install curl in the final image
RUN apt-get update && apt-get install -y curl

# Expose the port that your application will run on
EXPOSE 8080

# Start Tomcat and deploy the application
CMD ["catalina.sh", "run"]
