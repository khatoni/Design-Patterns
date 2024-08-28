package hash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SHA256Test {

    @Test
    void testCalculateHashEmptyString() throws IOException {
        ChecksumCalculator calculator = new SHA256();
        String initialData = "";
        InputStream inputStream = new ByteArrayInputStream(initialData.getBytes());
        String expectedResult = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
        Assertions.assertEquals(expectedResult, calculator.calculate(inputStream));
    }

    @Test
    void testCalculateHashOneWord() throws IOException {
        ChecksumCalculator calculator = new SHA256();
        String initialData = "abc";
        InputStream inputStream = new ByteArrayInputStream(initialData.getBytes());
        String expectedResult = "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad";
        Assertions.assertEquals(expectedResult, calculator.calculate(inputStream));
    }

    @Test
    void testCalculateHashWordsExpression() throws IOException {
        ChecksumCalculator calculator = new SHA256();
        String initialData = "Hello world!";
        InputStream inputStream = new ByteArrayInputStream(initialData.getBytes());
        String expectedResult = "c0535e4be2b79ffd93291305436bf889314e4a3faec05ecffcbb7df31ad9e51a";
        Assertions.assertEquals(expectedResult, calculator.calculate(inputStream));
    }

    @Test
    void testCalculateHashSomeLines() throws IOException {
        ChecksumCalculator calculator = new SHA256();
        String initialData = "Hello world\nthis simulates file content";
        InputStream inputStream = new ByteArrayInputStream(initialData.getBytes());
        String expectedResult = "8cd7a09fc5639a6712612bccf6bb86816bae01c60c398ab9fd9a00936601a6c9";
        Assertions.assertEquals(expectedResult, calculator.calculate(inputStream));
    }
}
