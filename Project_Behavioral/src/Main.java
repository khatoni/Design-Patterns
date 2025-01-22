import builder.DirectoryBuilder;
import builder.NoFollowSymlinkBuilder;
import hash.MD5Sum;
import memento.Memento;
import observer.ConsoleInputObservable;
import observer.Observable;
import observer.Observer;
import observer.ProgressReporter;
import structure.AbstractFile;
import visitor.HashStreamWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePathToStoreHash = "./hashes.txt";
        OutputStream outputStream = new FileOutputStream(filePathToStoreHash);
        System.out.println("Enter directory to scan");
        Scanner sc = new Scanner(System.in);
        String dirName = sc.nextLine();
        ConsoleInputObservable cio = new ConsoleInputObservable();
        var thread = Executors.newCachedThreadPool();
        DirectoryBuilder builder = new NoFollowSymlinkBuilder();
        AbstractFile structure = builder.build(dirName);
        Observable visitor = new HashStreamWriter(new MD5Sum(), structure, outputStream);
        cio.addSubscriber((Observer) visitor);
        ProgressReporter reporter = new ProgressReporter((int) structure.getSize());
        visitor.addSubscriber(reporter);
        thread.submit((Runnable) visitor);
        Memento hashState = null;
        Memento reporterState = null;
        while (true) {
            if (sc.hasNextLine()) {
                if (sc.nextLine().equals("continue")) {
                    ((HashStreamWriter) visitor).restoreState(hashState);
                    thread.submit((Runnable) visitor);
                }
                if (sc.nextLine().equals("quit")) {
                    thread.shutdown();
                    break;
                }
                if (sc.nextLine().equals("s")) {
                    cio.notifyAllSubscribers(cio, "Stop command");
                    System.out.println();
                    System.out.println("Waiting to finish hashing process");
                    hashState = ((HashStreamWriter) visitor).getMemento();
                }
            }
        }

        System.out.println("Program is terminated");
    }
}