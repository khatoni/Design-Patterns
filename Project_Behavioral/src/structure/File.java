package structure;

import visitor.HierarchyVisitor;

import java.nio.file.attribute.FileTime;
import java.util.List;

public class File extends AbstractFile {


    public File(String fileName, double size, FileTime lastModificationTime) {
        super(fileName, lastModificationTime);
        this.size = size;
    }

    @Override
    public void accept(HierarchyVisitor visitor) {
        visitor.visitFile(this);
    }

    @Override
    public List<AbstractFile> getFolderContent() {
        throw new UnsupportedOperationException("getFolderContent called from File, not Directory");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof File)) {
            return false;
        }

        return this.filePath.equals(((File) other).filePath);
    }

}
