package hash;

import observer.Observable;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

public class SHA256 extends Observable implements ChecksumCalculator {

    @Override
    public String calculate(InputStream is) throws IOException {
        byte[] b = createHash(is);
        StringBuilder result = new StringBuilder();
        for (byte value : b) {
            result.append(Integer.toString((value & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();

    }

    private byte[] createHash(InputStream is) throws IOException {
        byte[] buffer = new byte[4096];
        MessageDigest complete = null;
        try {
            complete = MessageDigest.getInstance("SHA256");
            int totalRead = 0;
            int numRead;

            do {
                numRead = is.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
                notifyAllSubscribers(this, totalRead+=numRead);
                try {
                    TimeUnit.SECONDS.sleep(1);
                }
                catch (InterruptedException e) {

                }
            } while (numRead != -1);


        } catch (NoSuchAlgorithmException e) {

        }
        return complete.digest();
    }
}
