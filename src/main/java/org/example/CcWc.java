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
        String inputFile = "";

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

        if (bytesFlag || lineFlag || wordFlag || charFlag) {
            defaultFlag = false;
        }

        if (bytesFlag) {
            System.out.println(getBytes(inputFile) + " " + inputFile);
        }

        if (lineFlag) {
            System.out.println(getLines(inputFile) + " " + inputFile);
        }

        if (wordFlag) {
            System.out.println(getWords(inputFile) + " " + inputFile);
        }

        if (charFlag) {
            System.out.println(getChars(inputFile) + " " + inputFile);
        }

        if (defaultFlag) {
            System.out.println(getLines(inputFile) + " " +
                    getWords(inputFile) + " " +
                    getBytes(inputFile) + " " + inputFile);
        }

    }

    private static long getWords(String inputFile) throws IOException {
        BufferedReader reader = (inputFile.isEmpty() ?
                new BufferedReader(new InputStreamReader(System.in)) :
                new BufferedReader(new FileReader(inputFile)));

        long wordCount = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            long wordsPerLine = Arrays.stream(line.trim().split("\\s+")).filter(str -> !str.isEmpty()).count();
            wordCount += wordsPerLine;
        }
        return wordCount;
    }

    private static long getLines(String inputFile) throws IOException {
        BufferedReader reader = (inputFile.isEmpty() ?
                new BufferedReader(new InputStreamReader(System.in)) :
                new BufferedReader(new FileReader(inputFile)));

        String line;
        long lineCount = 0;
        while ((line = reader.readLine()) != null) {
            lineCount++;
        }
        return lineCount;
    }

    private static long getBytes(String inputFile) throws IOException {
        InputStream is = (inputFile.isEmpty() ?
                System.in :
                new FileInputStream(inputFile));

        int bytes = 0;
        while (is.read() != -1) {
            bytes++;
        }
        return bytes;
    }

    private static long getChars(String inputFile) throws IOException {
        InputStreamReader reader = (inputFile.isEmpty() ?
                new InputStreamReader(System.in) :
                new InputStreamReader(new FileInputStream(inputFile)));

        long charCount = 0;
        while (reader.read() != -1) {
            charCount++;
        }
        return charCount;
    }
}