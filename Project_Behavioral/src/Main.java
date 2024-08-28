

import builder.NoFollowSymlinkBuilder;
import hash.MD5Sum;
import observer.Observable;
import observer.Observer;
import observer.ProgressReporter;
import strategy.MD5SumAlgorithm;
import strategy.StrategyContext;
import structure.AbstractFile;
import visitor.ConcreteVisitor;
import visitor.HashStreamWriter;
import visitor.HierarchyVisitor;
import visitor.ReportWriter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("Enter file directory: ");

        Scanner sc = new Scanner(System.in);

        String dirName = sc.nextLine();

        File dir = new File(dirName);
        HierarchyBuilder builder = new HierarchyBuilder(new NoFollowSymlinkBuilder(dirName), dir);
        AbstractFile structure = builder.create();
        try {
            HierarchyVisitor visitor = new HashStreamWriter(new MD5Sum());
            Observer obs = new ProgressReporter((int) structure.getTotalSize());
            ((Observable) visitor).addSubscriber(obs);
            structure.accept(visitor);
            System.out.println("gotovo");
        }
        catch (IOException e) {

        }
    }
}