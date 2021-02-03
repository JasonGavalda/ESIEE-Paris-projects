package question2;

import java.util.Iterator;
public class CarElementMountingVisitor implements CarElementVisitor<String> {
    
    @Override
    public String visit(AtomicCarElement element) {
        return "\tMontage de l'element : "+element.getName()+"\n";     // a completer;
    }

    @Override
    public String visit(CompositeCarElement c) {
        StringBuilder visiter = new StringBuilder("Demarrage du montage de l'ensemble "+c.getName()+"\n");
        Iterator<CarElement> vIterator= c.iterator();
        while(vIterator.hasNext())
        {   
            visiter.append(vIterator.next().accept(this));
        }
        visiter.append("Fin du montage de l'ensemble "+c.getName()+"\n");
        return visiter.toString();
    }
}