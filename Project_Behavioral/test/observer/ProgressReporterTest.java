package observer;

import hash.ChecksumCalculator;
import hash.MD5Sum;
import org.junit.jupiter.api.Test;
import visitor.HashStreamWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

public class ProgressReporterTest {

    @Test
    void testUpdateIllegalMessageReceiver() {
        ProgressReporter reporter = new ProgressReporter(10000);
        assertThrows(IllegalArgumentException.class, () -> reporter.update(null, "test"),
            "IllegalArgumentException was expected");
    }

    @Test
    void testUpdateStartingNewFile() {
        ProgressReporter reporter = new ProgressReporter(1000);
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             PrintStream pr = new PrintStream(byteArrayOutputStream)) {
            System.setOut(pr);
            HashStreamWriter toTest = mock();
            reporter.update(toTest, "TEST_FILE");
           String result = byteArrayOutputStream.toString();
            assertTrue(result.contains("Processing TEST_FILE... 0 byte(s) read"));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testUpdateBytesFromCalculator() {
        ProgressReporter reporter = new ProgressReporter(100);
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             PrintStream pr = new PrintStream(byteArrayOutputStream)) {
            System.setOut(pr);
            HashStreamWriter toTestWriter = mock();
            MD5Sum toTestAlgorithm = mock();
            reporter.update(toTestWriter, "TEST_FILE");
            reporter.update(toTestAlgorithm, 150);
            String result = byteArrayOutputStream.toString();
            assertTrue(result.contains("Processing TEST_FILE... 150 byte(s) read"));
        } catch (IOException e) {
            fail();
        }
    }
}
