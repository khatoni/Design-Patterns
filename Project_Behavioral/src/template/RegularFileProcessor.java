package template;

import java.io.IOException;
import java.io.OutputStream;

public abstract class RegularFileProcessor {

    public final void processRegularFile(String fileName, OutputStream outputStream) throws IOException {
        String fileHash = calculateHash(fileName);
        String output = fileHash + " " + fileName;
        outputStream.write(output.getBytes());

    }

    abstract String calculateHash(String fileName) throws IOException;
}
