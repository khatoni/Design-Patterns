package hash;

import java.io.IOException;
import java.io.InputStream;

public interface ChecksumCalculator {
    String calculate(InputStream is) throws IOException;
}
