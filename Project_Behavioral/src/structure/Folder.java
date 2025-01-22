package structure;

import visitor.HierarchyVisitor;

import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;

public class Folder extends AbstractFile {
    private List<AbstractFile> folderContent;

    public Folder(String dirName, FileTime lastModificationTime) {
        super(dirName, lastModificationTime);
        folderContent = new ArrayList<>();
    }

    public void addFolderContent(List<AbstractFile> folderContent) {
        this.folderContent.addAll(folderContent);
        for (var file : folderContent) {
            this.size += file.size;
        }
    }

    @Override
    public List<AbstractFile> getFolderContent() {
        return folderContent;
    }

    @Override
    public void accept(HierarchyVisitor visitor) {
        visitor.visitDirectory(this);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Folder)) {
            return false;
        }

        for (int i = 0; i < folderContent.size(); i++) {
            if (!(this.folderContent.get(i).equals(((Folder) other).getFolderContent().get(i)))) {
                return false;
            }
        }
        return true;
    }

}
