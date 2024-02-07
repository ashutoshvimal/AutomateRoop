package com.ashu.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {
    private static final String CONFIG_FILE = "config.properties";

    public static void main(String[] args) {
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Retrieve the authorization header from the properties
        String authorizationHeader = properties.getProperty("authorization_header");

        // Use the authorization header as needed
        System.out.println("Authorization Header: " + authorizationHeader);
    }
}
