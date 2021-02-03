package question2;

import question1.Cotisant;
import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

public class DebitMaximal implements Visiteur<Integer>
{
  @Override public Integer visite( final Contributeur pC )
  {
    return pC.solde();
  } // visite(.)
  
  @Override public Integer visite( final GroupeDeContributeurs pG )
  {
    int vRes =Integer.MAX_VALUE;
    for (Cotisant vC : pG.getChildren())
    {
        if (vC.solde() < vRes)
            vRes = vC.solde();
    }
    return vRes;
  } // visite(.)
} // DebitMaximal