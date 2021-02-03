package question1;

public class Contributeur extends Cotisant
{
    private int aSolde;

    public Contributeur( final String pNom, final int pSomme )
    {
        super( pNom );
        if (pSomme >= 0 )
            this.aSolde = pSomme;
        else
            throw new RuntimeException ("Valeur n�gative !!!");
    } // Contributeur(.)

    public int solde()
    {
        return this.aSolde;
    } // solde()

    public int nombreDeCotisants()
    {
        return 1;
    } // nombreDeCotisants()

    /**
     * throws RuntimeException new RuntimeException("nombre n�gatif !!!");
     */
    public void debit( final int pSomme ) throws SoldeDebiteurException
    {
        if (pSomme < 0)
            throw new RuntimeException("nombre n�gatif !!!");
        else
        {
            if ((this.aSolde - pSomme) < 0)
                throw new SoldeDebiteurException("solde n�gatif !!!");
            else
                this.aSolde -= pSomme;
        }
    } // debit(.)

    /**
     * throws RuntimeException new RuntimeException("nombre n�gatif !!!");
     */
    public  void credit( final int pSomme )
    {
        if (pSomme < 0)
            throw new RuntimeException("nombre n�gatif !!!");
        else
            this.aSolde += pSomme;
    } // credit(.)

    /**
     * throws RuntimeException new RuntimeException("nombre n�gatif !!!");
     */
    public void affecterSolde( final int pSomme )
    {
        if (pSomme < 0)
        {
            throw new RuntimeException("nombre n�gatif !!!");
        }
        try {
            this.debit( this.solde() );
            this.credit( pSomme );  // mode �l�gant ... 
        } catch( final SoldeDebiteurException sde ) { 
            // exception peu probable
            this.aSolde = pSomme; // mode efficace ...
        }
    } // affecterSolde(.)

    public <T> T accepter( final Visiteur<T> pVisiteur )
    {
        return pVisiteur.visite( this );
    } // accepter(.)

    @Override public String toString()
    {
        return "<Contributeur : " + super.nom() + "," + this.solde() + ">";
    } // toString()
} // Contributeur
