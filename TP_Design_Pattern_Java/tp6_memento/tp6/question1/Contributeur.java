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
            throw new RuntimeException ("Valeur négative !!!");
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
     * throws RuntimeException new RuntimeException("nombre négatif !!!");
     */
    public void debit( final int pSomme ) throws SoldeDebiteurException
    {
        if (pSomme < 0)
            throw new RuntimeException("nombre négatif !!!");
        else
        {
            if ((this.aSolde - pSomme) < 0)
                throw new SoldeDebiteurException("solde négatif !!!");
            else
                this.aSolde -= pSomme;
        }
    } // debit(.)

    /**
     * throws RuntimeException new RuntimeException("nombre négatif !!!");
     */
    public  void credit( final int pSomme )
    {
        if (pSomme < 0)
            throw new RuntimeException("nombre négatif !!!");
        else
            this.aSolde += pSomme;
    } // credit(.)

    /**
     * throws RuntimeException new RuntimeException("nombre négatif !!!");
     */
    public void affecterSolde( final int pSomme )
    {
        if (pSomme < 0)
        {
            throw new RuntimeException("nombre négatif !!!");
        }
        try {
            this.debit( this.solde() );
            this.credit( pSomme );  // mode élégant ... 
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
