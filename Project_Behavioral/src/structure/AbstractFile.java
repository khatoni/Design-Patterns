package structure;

import visitor.HierarchyVisitor;

import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractFile {
    protected String filePath;

    protected double size;

    protected FileTime lastModificationTime;

    public AbstractFile(String filePath, FileTime lastModificationTime) {
        this.filePath = filePath;
        this.lastModificationTime = lastModificationTime;
    }

    public FileTime getLastModificationTime() {
        return lastModificationTime;
    }
    public String getFilePath() {
        return filePath;
    }

    public double getSize() {
        return size;
    }

    public abstract List<AbstractFile> getFolderContent();


    public abstract void accept(HierarchyVisitor visitor);

}
