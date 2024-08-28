package template;

import hash.ChecksumCalculator;
import hash.MD5Sum;
import hash.SHA256;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MD5SUMFileProcessor extends RegularFileProcessor{

    @Override
    String calculateHash(String fileName) throws IOException {
        ChecksumCalculator calculator = new MD5Sum();
        byte[] data = Files.readAllBytes(Paths.get(fileName));
        InputStream is = new ByteArrayInputStream(data);
        return calculator.calculate(is);
    }
}
