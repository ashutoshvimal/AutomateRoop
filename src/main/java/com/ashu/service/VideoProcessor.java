package com.ashu.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VideoProcessor {

    public static void main(String[] args) {
        try {
            // Change directory to "F:\videos"
            ProcessBuilder changeDirProcess = new ProcessBuilder("cmd.exe", "/c", "cd F:\\videos");
            Process changeDir = changeDirProcess.start();
            changeDir.waitFor();

            // Run instaloader to download videos
            String instaloaderCommand = "C:\\Users\\HP\\AppData\\Local\\Programs\\Python\\Python310\\Scripts\\instaloader.exe -- -%postId%";
            ProcessBuilder instaloaderProcess = new ProcessBuilder("cmd.exe", "/c", instaloaderCommand);
            Process instaloader = instaloaderProcess.start();
            instaloader.waitFor();

            // Compile and run FindMaxVideoIndex.java
            ProcessBuilder compileProcess = new ProcessBuilder("javac", "FindMaxVideoIndex.java");
            Process compile = compileProcess.start();
            compile.waitFor();

            ProcessBuilder runProcess = new ProcessBuilder("java", "FindMaxVideoIndex");
            Process run = runProcess.start();
            run.waitFor();

            // Read the output of FindMaxVideoIndex.java
            BufferedReader reader = new BufferedReader(new InputStreamReader(run.getInputStream()));
            String maxId = reader.readLine();

            // Print the maxId
            System.out.println(maxId);

            // Change directory back to original or exit if failed
            ProcessBuilder changeDirBackProcess = new ProcessBuilder("cmd.exe", "/c", "cd -%postId%");
            Process changeDirBack = changeDirBackProcess.start();
            int exitCode = changeDirBack.waitFor();

            if (exitCode != 0) {
                System.out.println("Failed to change directory. Exiting...");
                System.exit(1);
            }

            // Rename and move the video file
            String renameCommand = "ren *.mp4 video" + maxId + ".mp4";
            ProcessBuilder renameProcess = new ProcessBuilder("cmd.exe", "/c", renameCommand);
            Process rename = renameProcess.start();
            rename.waitFor();

            String moveCommand = "move F:\\videos\\-%postId%\\video" + maxId + ".mp4 F:\\videos";
            ProcessBuilder moveProcess = new ProcessBuilder("cmd.exe", "/c", moveCommand);
            Process move = moveProcess.start();
            move.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
