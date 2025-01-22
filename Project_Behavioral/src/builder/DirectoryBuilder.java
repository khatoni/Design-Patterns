package builder;

import structure.AbstractFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class DirectoryBuilder {

    protected List<AbstractFile> folderContent;

    public DirectoryBuilder() {
        folderContent = new ArrayList<>();
    }

    public abstract AbstractFile build(String dirname) throws IOException;
}