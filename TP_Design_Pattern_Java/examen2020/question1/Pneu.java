package question1;


public class Pneu implements CarElement {
    
    private String aPosition;
    private float aPression;

    public Pneu(String position) {
        this.aPosition = position;
        this.aPression = 1.8f;
    }

    public String getPosition() {
        return this.aPosition; // a completer
    }
    
    public float getPression() {
        return this.aPression;  // a completer
    }
    
    public void setPression(float pression) {
         this.aPression = pression;
    }

    @Override
   public <T> T accept(CarElementVisitor<T> visitor) {
        return visitor.visit(this); // a completer
    }
    
    @Override
    public boolean safetyCheck () {
        return (this.aPression == 2.5f); // a completer
    }
    
    @Override
    public void setSafe() {
     this.setPression(2.5f);  
    }
}