import org.example.CcWc;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WcTests {

    @Test
    public void testFileBytes() {
        // Save the original System.out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);

        // Redirect System.out to the ByteArrayOutputStream
        System.setOut(newOut);

        try {
            // run main program get output stream
            CcWc.main(new String[]{"-c", "test.txt"});

            String capturedOutput = baos.toString();
            assertEquals("342190 test.txt", capturedOutput.trim());

        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
        }
    }

    @Test
    public void testLines() {
        // Save the original System.out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);

        // Redirect System.out to the ByteArrayOutputStream
        System.setOut(newOut);

        try {
            // run main program get output stream
            CcWc.main(new String[]{"-l", "test.txt"});

            String capturedOutput = baos.toString();
            assertEquals("7145 test.txt", capturedOutput.trim());

        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
        }
    }

    @Test
    public void testLinesFunc() {
        // Save the original System.out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);

        // Redirect System.out to the ByteArrayOutputStream
        System.setOut(newOut);

        try {
            // run main program get output stream
            CcWc.main(new String[]{"-lf", "test.txt"});

            String capturedOutput = baos.toString();
            assertEquals("7145 test.txt", capturedOutput.trim());

        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
        }
    }

    @Test
    public void testWordCount() {
        // Save the original System.out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);

        // Redirect System.out to the ByteArrayOutputStream
        System.setOut(newOut);

        try {
            // run main program get output stream
            CcWc.main(new String[]{"-w", "test.txt"});

            String capturedOutput = baos.toString();
            assertEquals("58164 test.txt", capturedOutput.trim());

        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
        }
    }

    @Test
    public void testCharCount() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);

        // Redirect System.out to the ByteArrayOutputStream
        System.setOut(newOut);

        try {
            // run main program get output stream
            CcWc.main(new String[]{"-m", "test.txt"});

            String capturedOutput = baos.toString();
            assertEquals("339292 test.txt", capturedOutput.trim());

        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
        }
    }

    @Test
    public void testDefaultOption() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);

        // Redirect System.out to the ByteArrayOutputStream
        System.setOut(newOut);

        try {
            // run main program get output stream
            CcWc.main(new String[]{"test.txt"});

            String capturedOutput = baos.toString();
            assertEquals("7145   58164  342190 test.txt", capturedOutput.trim());

        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
        }
    }

    @Test
    public void testStdin() throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("cat test.txt | ./ccwc.sh -l");

        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line=input.readLine();

        assertEquals("7145", line.trim());
    }
}
