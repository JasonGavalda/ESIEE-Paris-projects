package question1;

public class VisiteurToString implements Visiteur<String>
{
    @Override public String visite( final Contributeur pCpt )
    {
        return "<Contributeur : (" + pCpt.nom() + ", " + pCpt.solde() + ")>";
    } // visite()

    @Override public String visite( final GroupeDeContributeurs pGrp )
    {
        String vRes = "<Groupe " + pGrp.nom() + " : (";
        for ( Cotisant vC : pGrp )
            vRes = vRes + vC.accepter(this);

        return vRes + ">";
    } // visite(.)
} // VisiteurToString
