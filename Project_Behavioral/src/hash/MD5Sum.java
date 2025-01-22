package hash;

import exceptions.FailedCalculatingHashException;
import observer.Observable;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

public class MD5Sum extends Observable implements ChecksumCalculator {

    private boolean shouldStop = false;

    @Override
    public String calculate(InputStream is) throws FailedCalculatingHashException {
        byte[] b = createHash(is);
        StringBuilder result = new StringBuilder();
        for (byte value : b) {
            result.append(Integer.toString((value & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();

    }

    private byte[] createHash(InputStream is) throws FailedCalculatingHashException {
        byte[] buffer = new byte[50000];
        MessageDigest complete = null;
        try {
            complete = MessageDigest.getInstance("MD5");
            int totalRead = 0;
            int numRead;
            do {
                numRead = is.read(buffer);
                totalRead += numRead;
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
                notifyAllSubscribers(this, numRead);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new FailedCalculatingHashException("Failed calculating hash");
                }
            }
            while (numRead != -1);

        } catch (NoSuchAlgorithmException | IOException e) {
            throw new FailedCalculatingHashException("Failed calculating hash");
        }
        return complete.digest();
    }
}
