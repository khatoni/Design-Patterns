package visitor;

import structure.AbstractFile;
import structure.File;

public class ReportWriter extends Visitor {

    private final AbstractFile startingFile;

    public ReportWriter(AbstractFile startingFile) {
        this.startingFile = startingFile;
    }

    @Override
    public void processFile(AbstractFile file) {
        System.out.println("FileName: " + file.getFilePath() + " FileSize: " + file.getSize());
    }

    @Override
    public void run() {
        visitDirectory(startingFile);
    }

}
