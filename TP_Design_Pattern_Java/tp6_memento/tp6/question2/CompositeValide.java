package question2;

import question1.Cotisant;
import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question1.Visiteur;

public class CompositeValide implements Visiteur<Boolean>
{
  
  
  @Override public Boolean visite( final Contributeur pC )
  {
    return pC.solde()>=0;
  } // visite(.)
  
  @Override public Boolean visite( final GroupeDeContributeurs pG )
  {
    boolean vRes = true;
    int nbCotisants = pG.nombreDeCotisants();
    if (nbCotisants <=0)
        return false;
    for (Cotisant vC : pG.getChildren())
    {
        if (vC.solde() <= 0)
            vRes = false;
    }
    return vRes;
  } // visite(.)
} // CompositeValide