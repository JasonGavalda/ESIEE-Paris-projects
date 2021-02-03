package question2;

import question1.*;
import org.jdom.*;
import org.jdom.output.*;
import java.io.ByteArrayOutputStream;

public class Main
{
    public static void main( final String[] pArgs ) throws Exception
    {
        GroupeDeContributeurs vG = new GroupeDeContributeurs( "g" );
        vG.ajouter( new Contributeur( "g_a", 100 ) );
        vG.ajouter( new Contributeur( "g_b", 200 ) );
        vG.ajouter( new Contributeur( "g_c", 300 ) );
        GroupeDeContributeurs vG1 = new GroupeDeContributeurs( "g1" );
        //GroupeDeContributeurs g2 = new GroupeDeContributeurs("g2");
        vG1.ajouter(new Contributeur( "g1_a1", 100 ) );
        vG1.ajouter(new Contributeur( "g1_b1", 200 ) );
        //g1.ajouter(g2);
        vG.ajouter( vG1 );

        if ( vG.accepter( new CompositeValide() ) ) {
            System.out.println( arbreXML( vG ) );
            if ( vG.accepter( new DebitMaximal() ) >= 50 ) {
                vG.debit( 50 );
                System.out.println( arbreXML( vG ) );
            }
        } else {
            System.out.println( "Composite invalide" ); 
        }
    } // main(.)

    /** Retourne dans une String (un arbre en XML) utilisee en question4. */
    public static String arbreXML( final Cotisant pC ) throws Exception
    {
        ByteArrayOutputStream vBAOS = null;
        String vResult = "";
        Element vRacine = pC.accepter( new VisiteurToXML() );
        Document vDocument = new Document( vRacine );        
        XMLOutputter vOut = new XMLOutputter( Format.getPrettyFormat() );
        try {
            vBAOS = new ByteArrayOutputStream();
            vOut.output( vDocument, vBAOS );
            vResult = vBAOS.toString();
        } finally {
            vBAOS.close();
        }
        return vResult;
    } // arbreXML(.)
} // Main
