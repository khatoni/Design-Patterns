package structure;

import visitor.HierarchyVisitor;

import java.util.List;

public abstract class AbstractFile {
    protected String filePath;

    protected int totalSize;

    public AbstractFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public double getTotalSize() {
        return totalSize;
    }

    public abstract List<AbstractFile> getFolderContent();


    public abstract void addFile(AbstractFile file);

    public abstract void addDirectory(AbstractFile dir);

    public abstract void accept(HierarchyVisitor visitor);

}
