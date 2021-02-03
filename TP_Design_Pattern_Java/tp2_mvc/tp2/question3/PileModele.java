package question3;

import question3.tp_pile.PileI;
import question3.tp_pile.Pile2;
import question3.tp_pile.PileVideException;
import question3.tp_pile.PilePleineException;

import java.util.Observable;
public class PileModele<T> extends Observable implements PileI<T>
{
  private PileI<T> pile;

  /* a completer */

  public PileModele( PileI<T> pile )
  {
    this.pile= pile;
  } // PileModele()

  public void empiler( T o ) throws PilePleineException
  {
    this.pile.empiler(o);
    setChanged();
    notifyObservers(this);
  } // empiler()

  public T depiler() throws PileVideException
  {
    T x = this.pile.depiler();
    setChanged();
    notifyObservers(this);
    return x;
  } // depiler()

  public T sommet() throws PileVideException
  {
    return this.pile.sommet();
  } // sommet()

  public int taille()
  {
    return this.pile.taille();
  } // taille()

  public int capacite()
  {
    return this.pile.capacite();
  } // capacite()

  public boolean estVide()
  {
    return this.pile.estVide();
  } // estVide()

  public boolean estPleine()
  {
    return this.pile.estPleine();
  } // estPleine()

  public String toString()
  {
    return this.pile.toString();
  } // toString()
  
} // PileModele

/** notez qu'un message d'alerte subsiste a la compilation (unsafe ...) 
 *  du a l'emploi de notifyObservers, incontournable et sans consequence ici
 */

