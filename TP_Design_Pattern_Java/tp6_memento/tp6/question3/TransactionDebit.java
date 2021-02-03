package question3;

import question1.*;

public class TransactionDebit extends AbstractTransaction
{
    private Gardien aGardien;

    public TransactionDebit( final Cotisant pCotisant )
    {
        super( pCotisant );
        this.aGardien = new Gardien();
    } // TransactionDebit(.)

    public void beginTransaction()
    {
        this.aGardien.setMemento( new Memento( super.aCotisant ) );
    } // beginTransaction()

    public void endTransaction()
    {
        // nothing to do
    } // endTransaction()

    public  void rollbackTransaction()
    {
        this.aGardien.getMemento().setState( super.aCotisant );
    } // rollbackTransaction()
} // TransactionDebit