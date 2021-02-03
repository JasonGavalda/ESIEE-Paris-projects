package question1;

import java.util.HashMap;

public class VisiteurRestituant implements Visiteur<HashMap<Cotisant, Integer>>
{
    private HashMap<Cotisant, Integer> aMap;
    
    public VisiteurRestituant (final HashMap<Cotisant, Integer> pMap)
    {
        this.aMap = pMap;
    }
    
    @Override public HashMap<Cotisant, Integer> visite (Contributeur pC)
    {
        pC.affecterSolde(this.aMap.get(pC));
        return this.aMap;
    }
    
    @Override public HashMap<Cotisant, Integer> visite (GroupeDeContributeurs pC)
    {
        for (Cotisant vC : pC.getChildren())
            vC.accepter(this);
        return this.aMap;
    }
}
