package question1;

public abstract class Cotisant
{
    private String   aNom;
    private Cotisant aParent;

    public Cotisant( final String pNom ) { this( pNom, null ); }
    public Cotisant( final String pNom, final Cotisant pParent ) { this.aNom=pNom; this.aParent=pParent; }

    public abstract void debit(  final int pSomme ) throws SoldeDebiteurException;
    public abstract void credit( final int pSomme );
    public abstract int solde();
    public abstract int nombreDeCotisants();

    public String nom() { return this.aNom; }
    public boolean equals( final Object pO ) { return this.aNom.equals( ((Cotisant)pO).aNom ); }
    public void setParent( final Cotisant pParent ) { this.aParent=pParent; }
    public Cotisant getParent() { return this.aParent; }

    public abstract <T> T accepter( final Visiteur<T> pVisiteur );
} // Cotisant
