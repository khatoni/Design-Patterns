package visitor;

import structure.AbstractFile;

public interface HierarchyVisitor {

    void visitFile(AbstractFile file);
    void visitDirectory(AbstractFile file);
}
