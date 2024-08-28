import builder.DirectoryBuilder;
import builder.NoFollowSymlinkBuilder;
import structure.AbstractFile;

import java.io.File;

public class HierarchyBuilder {

    File startingPoint;
    DirectoryBuilder builder;

    public HierarchyBuilder(DirectoryBuilder builder, File startingPoint) {
        this.startingPoint = startingPoint;
        this.builder = builder;
    }
    private AbstractFile create(DirectoryBuilder builder, File startingPoint) {
        for(File file: startingPoint.listFiles()) {

            if(file.isDirectory()) {
                DirectoryBuilder db = new NoFollowSymlinkBuilder(file.getAbsolutePath());
                builder.buildDirectory(create(db,file));
            }
            else {
                builder.buildFile(file.getAbsolutePath(), file.length());
            }
        }
        return builder.build();
    }

    public AbstractFile create() {
        return create(this.builder,this.startingPoint);
    }
}
