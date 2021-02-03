package question1;

public abstract interface Visiteur<T>
{    
    public abstract T visite( final Contributeur pC );
    public abstract T visite( final GroupeDeContributeurs pG );
} // Visiteur
