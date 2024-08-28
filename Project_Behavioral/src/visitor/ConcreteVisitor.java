package visitor;
import structure.AbstractFile;
public class ConcreteVisitor implements HierarchyVisitor{

    @Override
    public void visitFile(AbstractFile file){
        System.out.println();
    }

    @Override
    public void visitDirectory(AbstractFile file){
       /* for(AbstractFile file : file.content) {
            file.accept(this);
        }*/
    }
}
