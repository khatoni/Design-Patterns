package visitor;

import observer.Observable;
import structure.AbstractFile;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Visitor extends Observable implements HierarchyVisitor, Runnable {

    protected Set<AbstractFile> processedFiles = new HashSet<>();
    protected AtomicBoolean shouldStop = new AtomicBoolean(false);


    @Override
    public final void visitFile(AbstractFile file) {
        if (shouldStop.get() || processedFiles.contains(file)) {
            return;
        }

        notifyAllSubscribers(this, file.getFilePath());
        processFile(file);
        processedFiles.add(file);
    }

    @Override
    public final void visitDirectory(AbstractFile file) {
        if (shouldStop.get() || processedFiles.contains(file)) {
            return;
        }

        notifyAllSubscribers(this, "Starting");
        for (AbstractFile abstractFile : file.getFolderContent()) {
            abstractFile.accept(this);
        }

    }

    protected abstract void processFile(AbstractFile file);

}
