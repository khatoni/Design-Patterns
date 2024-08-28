package visitor;

import structure.AbstractFile;

public class ReportWriter implements HierarchyVisitor{

    @Override
    public void visitFile(AbstractFile file) {
        System.out.println("FileName: "+ file.getFilePath() + " FileSize: " + file.getTotalSize());
    }

    @Override
    public void visitDirectory(AbstractFile file) {
        System.out.println("DirectoryName: " + file.getFilePath() + " FileSize " + file.getTotalSize());
        for(AbstractFile abstractFile: file.getFolderContent()) {
            abstractFile.accept(this);
        }
    }
}
