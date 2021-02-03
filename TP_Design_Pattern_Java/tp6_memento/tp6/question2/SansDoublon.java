package question2;

import java.util.ArrayList;
import question1.Cotisant;
import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

public class SansDoublon implements Visiteur<Boolean>
{
  private ArrayList aL;

  @Override public Boolean visite( final Contributeur pC )
  {
    this.aL = new ArrayList();
    this.aL.add(pC.nom());
    return this.aL.contains(pC);
  } // visite(.)
  
  @Override public Boolean visite( final GroupeDeContributeurs pG )
  {
    boolean vRes = false;
    this.aL = new ArrayList();
    for (Cotisant vC : pG.getChildren())
    {
        this.aL.add(vC.nom());
        if (aL.contains(vC))
            vRes = true;
    }
    return vRes;
  } // visite(.)
} // SansDoublon