package question1;

import java.util.HashMap;

public class VisiteurSauvegardant implements Visiteur<HashMap<Cotisant, Integer>>
{
    private HashMap<Cotisant, Integer> aMap;
    
    public VisiteurSauvegardant()
    {
        this.aMap = new HashMap <Cotisant, Integer>();
    }
    
    @Override public HashMap<Cotisant, Integer> visite(Contributeur pC)
    {
        this.aMap.put(pC, pC.solde());
        return this.aMap;
    }
    
    @Override public HashMap<Cotisant, Integer> visite(GroupeDeContributeurs pC)
    {
        for (Cotisant vC : pC.getChildren())
        {
            vC.accepter(this);
        }
        return this.aMap;
    }
    
}