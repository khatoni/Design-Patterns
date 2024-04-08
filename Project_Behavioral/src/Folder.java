import java.util.List;

public class Folder extends AbstractFile {
    private List<AbstractFile> folderContent;

    @Override
    public void addFile(AbstractFile file) {
        if (file == null) {
            throw new IllegalArgumentException("Null file to be added");
        }

        folderContent.add(file);
        size += file.size;
    }


}
