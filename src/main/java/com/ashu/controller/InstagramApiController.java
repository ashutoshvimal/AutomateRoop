package com.ashu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@RequestMapping("/instaurlapi")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class InstagramApiController {

//    private static final String CONFIG_FILE = "config.properties";

    @GetMapping
    public ModelAndView showInstagramPage() {
        return new ModelAndView("instaPageApi");
    }

    @PostMapping("/processUrl")
    public String processInstagramUrl(@RequestParam String instagramUrl) {
//        System.out.println("in api controller");
        String postId = extractPostId(instagramUrl);

        executeRequest(postId);

        // Redirect to a different page after processing
        return "redirect:/instaurlapi/success";
    }

    @GetMapping("/success")
    public ModelAndView successUrl() {
        return new ModelAndView("success");
    }
    private String extractPostId(String instagramUrl) {
        String regex1 = "https://www.instagram.com/p/([\\w\\-]+)/";
        String regex2 = "https://www.instagram.com/reel/([\\w\\-]+)/\\?igsh=[\\w\\-]+";

        // Create Pattern objects
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);

        // Match the URL against the patterns
        Matcher matcher1 = pattern1.matcher(instagramUrl);
        Matcher matcher2 = pattern2.matcher(instagramUrl);

        // Extract postId based on the matched pattern
        String postId = "";
        if (matcher1.find()) {
//            System.out.println(matcher1.group(1));
            postId = matcher1.group(1);
        } else if (matcher2.find()) {
//            System.out.println(matcher2.group(1));
            postId = matcher2.group(1);
        }
//        System.out.println(postId);
        // Return an empty string if the URL doesn't match either pattern
        return postId;
    }


    private String buildUrl(String postId) {
        return String.format("http://localhost:8081/job/Instaloader/buildWithParameters?param=value&postId=%s", postId);
    }

    private String buildAuthorizationHeader() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., log, throw, exit)
        }
        return properties.getProperty("authorization_header");
    }

    private void executeHttpRequest(String url, String authorizationHeader) {
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Set the authorization header
            connection.setRequestProperty("Authorization", authorizationHeader);

            // Enable input/output streams
            connection.setDoOutput(true);

            // Build the POST data (if needed)
            String postData = ""; // Add your POST data if required
            try (OutputStream os = connection.getOutputStream()) {
                os.write(postData.getBytes());
                os.flush();
            }

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Read and print the response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Close the connection
            connection.disconnect();

        } catch (IOException e) {
            // Handle exceptions appropriately based on your requirements
            e.printStackTrace();
        }
    }

    private void executeRequest(String postId) {
        String url = buildUrl(postId);
        String authorizationHeader = buildAuthorizationHeader();
        executeHttpRequest(url, authorizationHeader);
    }









//    private String buildCurlCommand(String postId) {
//        Properties properties = new Properties();
//
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
//        ) {
//            properties.load(input);
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle the exception appropriately (e.g., log, throw, exit)
//        }
//
//        String authorizationHeader = properties.getProperty("authorization_header");
//
//        // Build the curl command with the extracted post id and authorization header
//        return String.format("curl --location --request POST \"http://localhost:8081/job/Instaloader/buildWithParameters?param=value&postId=%s\" ^\n" +
//                "--header \"Authorization: %s\"", postId, authorizationHeader);
//    }
//
//    private void executeCurlCommand(String curlCommand) {
//        try {
//            // Execute the curl command
//            Process process = Runtime.getRuntime().exec(curlCommand);
//
//            // Wait for the process to finish
//            process.waitFor();
//
//            // Read and print the output of the process
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//        } catch (IOException | InterruptedException e) {
//            // Handle exceptions appropriately based on your requirements
//            e.printStackTrace();
//        }
//    }
}