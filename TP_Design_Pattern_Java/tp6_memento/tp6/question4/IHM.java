package question4;

import java.io.IOException;
import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question2.*;
import question3.*;
import static question2.Main.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jdom.*;
import org.jdom.output.*;
import java.io.ByteArrayOutputStream;

public class IHM extends JFrame
{
    protected JTextArea  aTResultat = new JTextArea( "", 7, 60 );
    private JButton    aBDebiter  = new JButton( "débiter"   );
    private JButton    aBCrediter = new JButton( "créditer"  );
    protected JTextField aTSomme    = new JTextField( 4 );

    protected GroupeDeContributeurs aGC;

    public IHM()
    {
        class DebiterListener implements ActionListener
        {
            private String aNom;
            
            public DebiterListener(String pNom)
            {
                this.aNom = pNom;
            }
            
            public void actionPerformed (ActionEvent aE)
            {
                try
                {
                    TransactionDebit transaction = new TransactionDebit(aGC);
                    transaction.debit(Integer.parseInt(aTSomme.getText()));
                    aTResultat.setText( Main.arbreXML( aGC ) );
                }
                catch (question1.SoldeDebiteurException pE)
                {
                    try
                    {
                        aTResultat.setText( Main.arbreXML( aGC ) );
                    }
                    catch (final Exception pE1)
                    {
                        
                    }
                }
                catch ( final Exception pE2)
                {
                    
                }
            }
            
        }
        
        class CrediterListener implements ActionListener
        {
            private String aNom;
            
            public CrediterListener(String pNom)
            {
                this.aNom = pNom;
            }
            
            public void actionPerformed (ActionEvent aE)
            {
                    aGC.credit(Integer.parseInt(aTSomme.getText()));
                    try
                    {
                        aTResultat.setText( Main.arbreXML( aGC ) );
                    }
                    catch (final Exception pE1)
                    {
                        
                    }
            }
            
        }
        
        this.setTitle( "Cotisant" );
        Container vContainer = this.getContentPane();
        this.aTSomme.setText( "40" );
        vContainer.setLayout( new BorderLayout() );

        vContainer.add( aTResultat, BorderLayout.NORTH );
        JPanel vP = new JPanel( new FlowLayout() );
        vP.add( this.aTSomme );
        vP.add( this.aBDebiter  );
        vP.add( this.aBCrediter );
        vContainer.add( vP, BorderLayout.SOUTH );

        this.aGC = new GroupeDeContributeurs( "g" );
        this.aGC.ajouter( new Contributeur( "g_a", 100 ) );
        this.aGC.ajouter( new Contributeur( "g_b",  50 ) );
        this.aGC.ajouter( new Contributeur( "g_c", 150 ) );
        GroupeDeContributeurs vG1 = new GroupeDeContributeurs( "g1" );
        vG1.ajouter( new Contributeur( "g1_a1",  70 ) );
        vG1.ajouter( new Contributeur( "g1_b1", 200 ) );
        this.aGC.ajouter( vG1 );

        try {
            this.aTResultat.setText( Main.arbreXML( this.aGC ) ); //actualiser();
        } catch( final Exception pE ) {
            // System.err.println( pE );
        }

        ActionListener vDL = new DebiterListener ("DebiterL");
        ActionListener vCL = new CrediterListener ("CrediterL");
        
        this.aBDebiter.addActionListener(  vDL );
        this.aBCrediter.addActionListener( vCL );

        this.pack();
        this.setVisible( true );
    } // IHM()
    
    public static void main() // ce n'est pas la veritable methode main !
    {
        new IHM();    
    } // main()
} // IHM
