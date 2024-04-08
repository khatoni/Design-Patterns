public abstract class AbstractFile {
    protected String filePath;
    protected double size;

    public abstract void addFile(AbstractFile file);

    protected String getFilePath() {
        return filePath;
    }

    protected double getSize() {
        return size;
    }
}
