package builder;

import org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer;
import structure.AbstractFile;
import structure.File;
import structure.Folder;

public class NoFollowSymlinkBuilder extends DirectoryBuilder {

    public NoFollowSymlinkBuilder(String dirName) {
        super(dirName);
    }

    @Override
    public void buildFile(String fileName, double size){
        folderContent.add(new File(fileName, size));
    }

    @Override
    public void buildDirectory(AbstractFile folder) {
        folderContent.add(folder);
    }
}
