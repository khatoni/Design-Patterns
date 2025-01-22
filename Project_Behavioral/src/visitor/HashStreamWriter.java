package visitor;

import exceptions.FailedCalculatingHashException;
import exceptions.FolderModifiedDuringScan;
import hash.ChecksumCalculator;
import memento.Memento;
import observer.ConsoleInputObservable;
import observer.Observable;
import observer.Observer;
import structure.AbstractFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashStreamWriter extends Visitor implements Observer {
    private OutputStream outputStream;

    private ChecksumCalculator calc;

    private AbstractFile startingDirectory;
    private String startDirName;

    private FileTime modificationTime = null;

    public HashStreamWriter() {
    }

    public HashStreamWriter(ChecksumCalculator calculator, AbstractFile startingDirectory, OutputStream hashFileToStore)
        throws FileNotFoundException {
        this.calc = calculator;
        this.startingDirectory = startingDirectory;
        this.startDirName = startingDirectory.getFilePath();
        ((Observable) calculator).addSubscriber(this);
        outputStream = hashFileToStore;
    }

    @Override
    protected void processFile(AbstractFile file) {
        try (InputStream is = new FileInputStream(file.getFilePath())) {
            outputStream.write(calc.calculate(is).getBytes());
            outputStream.write(" ".getBytes());
            outputStream.write((file.getFilePath() + System.lineSeparator()).getBytes());
        } catch (IOException e) {
            throw new FailedCalculatingHashException("Problems while calculating hash for file " + file.getFilePath());
        }
    }

    @Override
    public void update(Observable sender, Object message) {
        if (sender instanceof ConsoleInputObservable) {
            shouldStop.set(true);
        } else {
            notifyAllSubscribers(sender, message);
        }
    }

    @Override
    public void run() {

        if (shouldStop.get()) {
            shouldStop.set(false);
            try {
                modificationScan();
            } catch (IOException | FolderModifiedDuringScan e) {
                System.out.println("One of the already scanned file was modified");
                return;
            }
        }

        startingDirectory.accept(this);
    }

    public void setOutputStream(OutputStream outputStream) throws IOException {
        this.outputStream.flush();
        this.outputStream.close();
        this.outputStream = outputStream;
    }

    public Memento getMemento() {
        //calculate processedFileSizes:
        double processedBytes = 0;
        for (var file : processedFiles) {
            processedBytes += file.getSize();
        }
        return new HashStreamWriterMemento(processedFiles, startingDirectory, startingDirectory.getSize(),
            processedBytes);
    }

    public void restoreState(Memento memento) {
        var state = (HashStreamWriterMemento) memento;
        double totalBytes = state.totalBytes;
        double processedBytes = state.processedBytes;
        notifyAllSubscribers(this, "restore," + totalBytes + "," + processedBytes);
        this.processedFiles = state.processedFiles;
        this.startingDirectory = state.startingDirectory;
    }

    private static class HashStreamWriterMemento implements Memento {
        private Set<AbstractFile> processedFiles;
        private AbstractFile startingDirectory;
        private double totalBytes;
        private double processedBytes;

        public HashStreamWriterMemento(Set<AbstractFile> processedFiles, AbstractFile startingDirectory,
                                       double totalBytes, double processedBytes) {
            this.processedFiles = processedFiles;
            this.startingDirectory = startingDirectory;
            this.totalBytes = totalBytes;
            this.processedBytes = processedBytes;
        }
    }


    private void listFiles(String directoryName, List<File> files) {
        File directory = new File(directoryName);

        // Get all files from a directory.
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    files.add(file);
                    listFiles(file.getAbsolutePath(), files);
                }
            }
        }
    }

    private void modificationScan() throws IOException, FolderModifiedDuringScan {

        List<File> folderContent = new ArrayList<>();
        listFiles(startDirName, folderContent);

        Map<String, FileTime> fileMap = new HashMap<>();
        for (File file : folderContent) {
            fileMap.put(file.getCanonicalPath(), Files.getLastModifiedTime(file.toPath()));
        }

        for (var loadedFile : processedFiles) {
            String loadedFilePath = new File(loadedFile.getFilePath()).getCanonicalPath();
            if (fileMap.containsKey(loadedFilePath)) {
                if (loadedFile.getLastModificationTime().compareTo(fileMap.get(loadedFilePath)) < 0) {
                    throw new FolderModifiedDuringScan("The folder structure was modified");
                }
            } else {
                throw new FolderModifiedDuringScan("The folder structure was modified");
            }
        }
    }
}
