# Use an official Tomcat runtime as a parent image
FROM tomcat:9.0-jdk17-openjdk-slim

# Remove the default Tomcat webapps to clear the webapp folder
RUN rm -rf /usr/local/tomcat/webapps/*

# Set the working directory in the container
WORKDIR /usr/local/tomcat/webapps

# Copy the application WAR file into the container at /usr/local/tomcat/webapps
COPY target/project2.war /usr/local/tomcat/webapps/app.war

# Copy the config.properties file into the container at /usr/local/tomcat/conf
COPY config.properties /usr/local/tomcat/conf/config.properties

# Expose the port that your application will run on
EXPOSE 8080

# Start Tomcat and deploy the application
CMD ["catalina.sh", "run"]
