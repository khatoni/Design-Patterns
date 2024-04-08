public class File extends AbstractFile {

    @Override
    public void addFile(AbstractFile file) {
        throw new RuntimeException("Attempt to add file into regular file");
    }
}
