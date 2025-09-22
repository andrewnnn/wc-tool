package org.example;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class CcWc {
    public static void main(String[] args) throws IOException {
        boolean bytesFlag = false;
        boolean lineFlag = false;
        boolean wordFlag = false;
        boolean charFlag = false;
        boolean defaultFlag = true;
        String inputFile = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-c")) {
                bytesFlag = true;
            } else if (args[i].equals("-l")) {
                lineFlag = true;
            } else if (args[i].equals("-w")) {
                wordFlag = true;
            } else if (args[i].equals("-m")) {
                charFlag = true;
            } else {
                inputFile = args[i];
            }
        }

        if (inputFile == null && args[0].equals("-l")) {
            // running from stdin
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String line = reader.readLine();


            reader.close();
        }

        if (bytesFlag && inputFile != null) {
            long bytes = getBytes(inputFile);
            System.out.println(" " + bytes + " " + inputFile);
        }

        if (lineFlag && inputFile != null) {
            try {
                long lines = getLines(inputFile);
                System.out.println(lines + " " + inputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (args[0].equals("-lf")) {
            String filePath = "input.txt"; // Replace with your file path
            Path path = Paths.get(inputFile);

            try (Stream<String> lines = Files.lines(path)) {
                long lineCount = lines.count();
                System.out.println(lineCount + " " + inputFile);
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }

        if (args[0].equals("-w")) {
            long words = 0;
            try {
                words = getWords(inputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(words + " " + inputFile);
        }

        if (args[0].equals("-m")) {
            getChars(inputFile);
        }

        if (args.length == 1) {
            inputFile = args[0];
            try {
                long bytes = getBytes(inputFile);
                long words = getWords(inputFile);
                long lines = getLines(inputFile);
                System.out.println(lines + "   " + words + "  " + bytes + " " + inputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static long getWords(String fileInput) throws IOException {
        BufferedReader reader = (fileInput != null ?
                new BufferedReader(new FileReader(fileInput)) :
                new BufferedReader(new InputStreamReader(System.in)));

        long wordCount = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            long wordsPerLine = Arrays.stream(line.trim().split("\\s+")).filter(str -> !str.isEmpty()).count();
            wordCount += wordsPerLine;
        }
        return wordCount;
    }

    private static long getLines(String fileInput) throws IOException {
        BufferedReader reader = (fileInput != null ?
                new BufferedReader(new FileReader(fileInput)) :
                new BufferedReader(new InputStreamReader(System.in)));

        String line;
        long lineCount = 0;
        while ((line = reader.readLine()) != null) {
            lineCount++;
        }
        return lineCount;
    }

    private static long getBytes(String fileInput) throws IOException {
        InputStreamReader reader = (fileInput != null ?
                new InputStreamReader(new FileInputStream(fileInput)) :
                new InputStreamReader(System.in));

        int bytes = 0;
        while (reader.read() != -1) {
            bytes++;
        }
        return bytes;
    }

    private static long getChars(String fileInput) throws IOException {
        InputStreamReader reader = (fileInput != null ?
                new InputStreamReader(new FileInputStream(fileInput)) :
                new InputStreamReader(System.in));

        long charCount = 0;
        while (reader.read() != -1) {
            charCount++;
        }
        return charCount;
    }
}