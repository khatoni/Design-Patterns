package visitor;

import hash.ChecksumCalculator;
import observer.Observable;
import observer.Observer;

import structure.AbstractFile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class HashStreamWriter extends Observable implements HierarchyVisitor, Observer{

    //private CheckSumCalculatorStrategy strategy;

    private static final String filePathToStoreHash = "./hashes.txt";
    private OutputStream outputStream;

    private ChecksumCalculator calc;

    public HashStreamWriter(ChecksumCalculator calculator) throws FileNotFoundException {
        this.calc = calculator;
        ((Observable)calculator).addSubscriber(this);
        outputStream = new FileOutputStream(filePathToStoreHash);
    }

    @Override
    public void visitFile(AbstractFile file) {
        notifyAllSubscribers(this, file.getFilePath());

        try {
            TimeUnit.SECONDS.sleep(0);
            InputStream is = new FileInputStream(file.getFilePath());
            outputStream.write(calc.calculate(is).getBytes());
            outputStream.write(" ".getBytes());
            outputStream.write(file.getFilePath().getBytes());
        }
        catch (IOException|InterruptedException e) {

        }
    }
    @Override
    public void visitDirectory(AbstractFile file) {
        notifyAllSubscribers(this, "Starting");
        for (AbstractFile abstractFile: file.getFolderContent()) {
            abstractFile.accept(this);
        }
    }

    @Override
    public void update(Observable sender, Object message) {
        notifyAllSubscribers(sender, message);
    }

    public void setCheckSumAlgorithm(ChecksumCalculator calculator) {
        this.calc = calculator;
    }
}
