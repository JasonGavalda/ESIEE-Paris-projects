package question2;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class CompositeCarElement extends CarElement implements Iterable<CarElement>  {
    
    private ArrayList aElementList;
    
    public CompositeCarElement (String modele) {
        super(modele);
        this.aElementList = new ArrayList<CarElement>();
    }
    
    public CompositeCarElement addCarElement(CarElement element) throws Exception{
        try {
            element.setParent(this);
            this.aElementList.add(element);
        }
        catch (Exception pE)
        {}
        return this;
    }
    
    public CompositeCarElement removeCarElement(CarElement element) throws Exception{
        try {
            this.aElementList.remove(element);
        }
        catch (Exception pE)
        {}
        return this;
    }
    
    @Override
    public Iterator<CarElement> iterator(){
        return this.aElementList.iterator();
    }
        
    @Override
    public <T> T  accept(CarElementVisitor<T> visitor) {
        return visitor.visit(this);     // a completer
    }
    
}