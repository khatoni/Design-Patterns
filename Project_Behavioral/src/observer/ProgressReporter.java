package observer;

import hash.ChecksumCalculator;
import visitor.HashStreamWriter;

public class ProgressReporter implements Observer {
    private String fileName = null;
    private Integer bytesRead = 0;

    private final int totalNumberBytes;
    private int currentFileBytes = 0;
    private int processedNumberBytes = 0;
    private long startingTime;
    private double totalElapsedTimeInSeconds = 0;

    public ProgressReporter(int totalNumberBytes) {
        this.totalNumberBytes = totalNumberBytes;
    }

    @Override
    public void update(Observable sender, Object message) {


        if (sender instanceof ChecksumCalculator) {
            bytesRead = (Integer) message;
            processedNumberBytes += bytesRead;
            currentFileBytes += bytesRead;
            totalElapsedTimeInSeconds = (double) (System.currentTimeMillis() - startingTime) / 1000;
        } else if (sender instanceof HashStreamWriter && ((String) message).equals("Starting")) {
            startingTime = System.currentTimeMillis();
            return;
        } else if (sender instanceof HashStreamWriter) {
            currentFileBytes = 0;
            fileName = (String) message;
            totalElapsedTimeInSeconds = (double) (System.currentTimeMillis() - startingTime) / 1000;

        } else {
            throw new IllegalArgumentException("Unexpected message");
        }
        refresh();
    }

    private void refresh() {
        double remainingSeconds =
            (double) ((totalNumberBytes - processedNumberBytes) /
                (double) (processedNumberBytes / totalElapsedTimeInSeconds));
        System.out.print(
            "\rProcessing " +
                fileName +
                "... " +
                currentFileBytes +
                " byte(s) read  " + remainingSeconds + " remaining seconds"
        );

    }
}
