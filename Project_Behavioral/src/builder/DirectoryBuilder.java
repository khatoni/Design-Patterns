package builder;

import structure.AbstractFile;
import structure.File;
import structure.Folder;

import java.util.ArrayList;
import java.util.List;

public abstract class DirectoryBuilder {

    protected String folderName;
    protected List<AbstractFile> folderContent;

    public DirectoryBuilder(String folderName) {
        this.folderName = folderName;
        folderContent = new ArrayList<>();
    }
    public abstract void buildFile(String fileName, double size);
    public abstract void buildDirectory(AbstractFile folder);

    public AbstractFile build() {
        return new Folder(folderName, folderContent);
    }
}