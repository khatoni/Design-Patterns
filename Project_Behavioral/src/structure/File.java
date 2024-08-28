package structure;

import visitor.HierarchyVisitor;

import java.util.List;

public class File extends AbstractFile {

    private final double fileSize;

    public File(String fileName, double size) {
        super(fileName);
        this.fileSize = size;
        this.totalSize += fileSize;
    }


    @Override
    public void addFile(AbstractFile file) {
        throw new RuntimeException("Attempt to add file into regular file");
    }

    @Override
    public void addDirectory(AbstractFile dir) {
        throw new RuntimeException("Attempt to add dir into regular file");
    }

    @Override
    public void accept(HierarchyVisitor visitor) {
        visitor.visitFile(this);
    }

    public double getFileSize(){
        return fileSize;
    }

    @Override
    public List<AbstractFile> getFolderContent() {
        throw new UnsupportedOperationException("getFolderContent called from File, not Directory");
    }

}
