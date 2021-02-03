package question2;

import java.util.Iterator;
public class CarElementCountingVisitor implements CarElementVisitor<Integer> {

     @Override
    public Integer visit(AtomicCarElement element) {
        return 1; // a completer
    }
    
    @Override
    public Integer visit(CompositeCarElement c) {
        Integer count = 0;
        Iterator<CarElement> vIterator= c.iterator();
        while(vIterator.hasNext())  {
            count++;
        }
        return count; // a completer
    }
    
}