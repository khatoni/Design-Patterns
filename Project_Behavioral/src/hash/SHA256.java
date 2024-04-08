package hash;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;

public class SHA256 implements ChecksumCalculator {

    @Override
    public String calculate(InputStream is) throws IOException {
        return DigestUtils.sha256Hex(is);
    }
}