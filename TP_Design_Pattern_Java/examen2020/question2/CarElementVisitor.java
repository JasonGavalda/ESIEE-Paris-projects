package question2;

public interface CarElementVisitor <T> {
    
    public T visit(AtomicCarElement element);

    public T visit(CompositeCarElement system);
    
}