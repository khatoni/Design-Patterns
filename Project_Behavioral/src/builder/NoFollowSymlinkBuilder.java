package builder;

import structure.AbstractFile;
import structure.File;
import structure.Folder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NoFollowSymlinkBuilder extends DirectoryBuilder {

    public NoFollowSymlinkBuilder() {
        super();
    }

    @Override
    public AbstractFile build(String fileName) throws IOException {
        Path path = Path.of(fileName);
        if (Files.isRegularFile(path)) {
            return new File(fileName, path.toFile().length(), Files.getLastModifiedTime(path));
        } else {
            Folder directory = new Folder(fileName, Files.getLastModifiedTime(path));
            List<AbstractFile> children = new ArrayList<>();
            for (var files : path.toFile().listFiles()) {
                children.add(build(files.getPath()));
            }
            directory.addFolderContent(children);
            return directory;
        }
    }
}
