package question1;

public class Moteur implements CarElement {

    private float aPression;
 
    public Moteur() {
        this.aPression = 2.0f;
    }
    
    public float getPressionHuile () {
        return this.aPression; // a completer
    }

    public void setPressionHuile (float pressionHuile) {
        this.aPression = pressionHuile;
    }

    @Override
    public <T> T accept(CarElementVisitor<T> visitor) {
        return visitor.visit(this); // a completer
    }

    @Override
    public boolean safetyCheck () {
        return (this.aPression == 3.0f); // a completer
    }

    @Override
    public void setSafe() {
        setPressionHuile(3.0f);
    }
}