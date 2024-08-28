package structure;

import visitor.HierarchyVisitor;

import java.util.ArrayList;
import java.util.List;

public class Folder extends AbstractFile {
    private List<AbstractFile> folderContent;

    public Folder(String dirName) {
        super(dirName);
        folderContent = new ArrayList<>();
    }

    public Folder(String dirname, List<AbstractFile> folderContent) {
        super(dirname);
        this.folderContent = folderContent;
        for(AbstractFile file : folderContent) {
            this.totalSize += file.totalSize;
        }
    }
    @Override
    public void addFile(AbstractFile file) {
        if (file == null) {
            throw new IllegalArgumentException("Null file to be added");
        }

        folderContent.add(file);
    }

    @Override
    public void addDirectory(AbstractFile dir) {
        if(dir == null) {
            throw new IllegalArgumentException("Null dir to be added");
        }
        folderContent.add(dir);
    }

    @Override
    public List<AbstractFile> getFolderContent() {
        return folderContent;
    }

    @Override
    public void accept(HierarchyVisitor visitor) {
        visitor.visitDirectory(this);
    }

}
