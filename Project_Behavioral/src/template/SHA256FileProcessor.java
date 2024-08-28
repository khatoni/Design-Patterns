package template;

import hash.ChecksumCalculator;
import hash.SHA256;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SHA256FileProcessor extends RegularFileProcessor{

    @Override
    String calculateHash(String fileName) throws IOException {
        ChecksumCalculator calculator = new SHA256();
        byte[] data = Files.readAllBytes(Paths.get(fileName));
        InputStream is = new ByteArrayInputStream(data);
        return calculator.calculate(is);
    }
}
