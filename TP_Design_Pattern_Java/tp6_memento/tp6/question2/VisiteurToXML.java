package question2;

import question1.Cotisant;
import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question1.Visiteur;
import org.jdom.*;

public class VisiteurToXML implements Visiteur<Element>
{
    @Override public Element visite( final Contributeur pC )
    {
        Element vElt = new Element( "contributeur" );
        vElt.setAttribute( "nom", pC.nom() );
        vElt.setAttribute( "solde", Integer.toString( pC.solde() ) );
        return vElt;
    } // visite(.)

    @Override public Element visite( final GroupeDeContributeurs pG )
    {
        Element vElt = new Element( "groupe" );
        vElt.setAttribute( "nom", pG.nom() );
        vElt.setAttribute( "solde", Integer.toString( pG.solde() ) );
        for ( Cotisant vC  : pG.getChildren() ) {
            vElt.addContent( vC.accepter( this ) );
        }
        return vElt;
    } // visite(.)
} // VisiteurToXML