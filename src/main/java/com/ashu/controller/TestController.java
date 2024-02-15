package com.ashu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;

@Controller
@RequestMapping("/test")
public class TestController {

//    private static final String CONFIG_FILE = "config.properties";

    @GetMapping
    public ModelAndView showInstagramPage() {
        return new ModelAndView("instaPage");
    }

//    @PostMapping("/processUrl")
//    public String processInstagramUrl(@RequestParam String instagramUrl) {
//        System.out.println("in controller");
//        String postId = extractPostId(instagramUrl);
//
//        // Build and execute curl command
//        String curlCommand = buildCurlCommand(postId);
//        executeCurlCommand(curlCommand);
//
//        // Redirect to a different page after processing
//        return "redirect:/instaurl/success";
//    }

//    @GetMapping("/success")
//    public ModelAndView successUrl() {
//        return new ModelAndView("success");
//    }
//    private String extractPostId(String instagramUrl) {
//        String regex1 = "https://www.instagram.com/p/([\\w\\-]+)/";
//        String regex2 = "https://www.instagram.com/reel/([\\w\\-]+)/\\?igsh=[\\w\\-]+";
//
//        // Create Pattern objects
//        Pattern pattern1 = Pattern.compile(regex1);
//        Pattern pattern2 = Pattern.compile(regex2);
//
//        // Match the URL against the patterns
//        Matcher matcher1 = pattern1.matcher(instagramUrl);
//        Matcher matcher2 = pattern2.matcher(instagramUrl);
//
//        // Extract postId based on the matched pattern
//        String postId = "";
//        if (matcher1.find()) {
//            System.out.println(matcher1.group(1));
//            postId = matcher1.group(1);
//        } else if (matcher2.find()) {
//            System.out.println(matcher2.group(1));
//            postId = matcher2.group(1);
//        }
//        System.out.println(postId);
//        // Return an empty string if the URL doesn't match either pattern
//        return postId;
//    }
//
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