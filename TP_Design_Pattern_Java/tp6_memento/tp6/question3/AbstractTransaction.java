package question3;

import question1.*;

public abstract class AbstractTransaction
{ 
    protected Cotisant aCotisant;
    private int aSomme;

    public abstract void beginTransaction();     // Template Method
    public abstract void endTransaction();
    public abstract void rollbackTransaction();

    public AbstractTransaction( final Cotisant pCotisant )
    {
        this.aCotisant = pCotisant;
    } // AbstractTransaction

    final public void debit( final int pSomme ) throws SoldeDebiteurException
    {
        try {
            this.beginTransaction();
            this.aSomme = pSomme;
            this.aCotisant.debit( pSomme );
            this.endTransaction();
        } catch ( final SoldeDebiteurException pE ) {
            this.rollbackTransaction();
            throw pE;
        }
    } // debit(.)
} // AbstractTransaction
