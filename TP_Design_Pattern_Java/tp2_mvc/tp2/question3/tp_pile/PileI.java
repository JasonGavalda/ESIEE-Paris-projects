package question3.tp_pile;

public interface PileI<T>
{

  public final static int CAPACITE_PAR_DEFAUT= 10;

  public void empiler( T o ) throws PilePleineException;

  public T depiler() throws PileVideException;

  public T sommet() throws PileVideException;

  public int capacite();

  public int taille();

  public boolean estVide();

  public boolean estPleine();

  public boolean equals( Object o );

  public String toString();

} // PileI
