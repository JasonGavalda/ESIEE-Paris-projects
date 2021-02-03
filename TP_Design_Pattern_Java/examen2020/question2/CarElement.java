package question2;

public abstract class CarElement {

    private String name;
    private CarElement parent;
    
    public CarElement (String name) {
        this.name=name;
    }
    
    public abstract <T> T accept(CarElementVisitor<T> visitor);
    
    public final String getName() {
        return name;
    }

    public CarElement getParent(){
        return this.parent;
    }

    public void setParent(CarElement parent){
        this.parent = parent;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+" - "+this.getName();
    }
    
}
