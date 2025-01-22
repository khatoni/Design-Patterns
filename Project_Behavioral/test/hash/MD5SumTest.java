package hash;

import exceptions.FailedCalculatingHashException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MD5SumTest {

    @Test
    void testCalculateHashEmptyString() throws IOException {
        ChecksumCalculator calculator = new MD5Sum();
        String initialData = "";
        InputStream inputStream = new ByteArrayInputStream(initialData.getBytes());
        String expectedResult = "d41d8cd98f00b204e9800998ecf8427e";
        Assertions.assertEquals(expectedResult, calculator.calculate(inputStream));
    }

    @Test
    void testCalculateHashOneWord() throws IOException {
        ChecksumCalculator calculator = new MD5Sum();
        String initialData = "abc";
        InputStream inputStream = new ByteArrayInputStream(initialData.getBytes());
        String expectedResult = "900150983cd24fb0d6963f7d28e17f72";
        Assertions.assertEquals(expectedResult, calculator.calculate(inputStream));
    }

    @Test
    void testCalculateHashWordsExpression() throws IOException {
        ChecksumCalculator calculator = new MD5Sum();
        String initialData = "Hello world!";
        InputStream inputStream = new ByteArrayInputStream(initialData.getBytes());
        String expectedResult = "86fb269d190d2c85f6e0468ceca42a20";
        Assertions.assertEquals(expectedResult, calculator.calculate(inputStream));
    }

    @Test
    void testCalculateHashSomeLines() throws IOException {
        ChecksumCalculator calculator = new MD5Sum();
        String initialData = "Hello world\nthis simulates file content";
        InputStream inputStream = new ByteArrayInputStream(initialData.getBytes());
        String expectedResult = "49868b4050daabc0d81c385a21464dad";
        Assertions.assertEquals(expectedResult, calculator.calculate(inputStream));
    }

    @Test
    void testCalculateHashFailedException() throws IOException {
        ChecksumCalculator calculator = new MD5Sum();
        InputStream inputStream = mock();
        when(inputStream.read(any())).thenThrow(new IOException());
        assertThrows(FailedCalculatingHashException.class, ()->calculator.calculate(inputStream));
    }
}
