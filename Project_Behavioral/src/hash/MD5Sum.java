package hash;

import observer.Observable;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MD5Sum extends Observable implements ChecksumCalculator {

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
        byte[] buffer = new byte[20000];
        MessageDigest complete = null;
        try {
            complete = MessageDigest.getInstance("MD5");
            int totalRead = 0;
            int numRead;

            do {
                numRead = is.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
                notifyAllSubscribers(this, numRead);
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
