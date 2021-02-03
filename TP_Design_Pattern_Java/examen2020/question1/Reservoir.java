package question1;

public class Reservoir implements CarElement {

    private boolean aFull;

    public Reservoir()  {
        this.aFull = false;
    }
    
    public boolean getFull() {
        return this.aFull; // a completer
    }

    public void setFull() {
        this.aFull = true;
    }

    @Override
    public <T> T accept(CarElementVisitor<T> visitor) {
        return visitor.visit(this); // a completer
    }

    @Override
    public boolean safetyCheck () {
        return this.aFull; // a completer
    }

    @Override
    public void setSafe() {
       setFull(); 
    }
}