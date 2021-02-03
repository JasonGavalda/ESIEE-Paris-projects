package question2;

public class AtomicCarElement extends CarElement {
    
    public AtomicCarElement(String name) {
        super(name);
        // a completer
    }

    @Override
    public <T> T  accept(CarElementVisitor<T> visitor) {
        return visitor.visit(this); // a completer
    }
}