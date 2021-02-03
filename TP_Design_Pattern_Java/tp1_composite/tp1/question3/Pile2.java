package question3;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile2<T> implements PileI<T>
{
  /** par delegation : utilisation de la class Stack */
  private Stack<T> stk;
  /** la capacite de la pile */
  private int capacite;
  
  /** Creation d'une pile.
   * @param capacite la "taille maximale" de la pile, doit etre > 0
   */
  public Pile2( final int pCapacite )
  {
    this.stk = new Stack<T>();
    if (pCapacite <=0)
        this.capacite = CAPACITE_PAR_DEFAUT;
    else
        this.capacite = pCapacite;
  } // Pile2(.)

  public Pile2()
  {
    this.stk = new Stack<T>();
    this.capacite = CAPACITE_PAR_DEFAUT;
  } // Pile2()

  public void empiler( final T pO ) throws PilePleineException
  {
    if ( this.estPleine() )   throw new PilePleineException();
    this.stk.push (pO);
  } // empiler(.)
  
  public T depiler() throws PileVideException
  {
    if ( this.estVide() )   throw new PileVideException();
    return this.stk.pop();
  } // depiler()

  public T sommet() throws PileVideException
  {
    if ( this.estVide() )   throw new PileVideException();
    return this.stk.peek();
  } // sommet()
  
  /** Effectue un test de l'etat de la pile.
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide()
  {
    return this.stk.empty();
  } // estVide()
  
  public boolean estPleine()
  {
    return this.capacite == this.stk.size();
  } // estPleine()
  
  /** Retourne le nombre d'elements d'une pile.
   * @return le nombre d'elements presents
   */ 
  public int taille()
  {
    return this.stk.size();
  } // taille()
  
  /** Retourne le nombre d'elements d'une pile.
   * @return le nombre d'elements possibles
   */ 
  public int capacite()
  {
    return this.capacite;
  } // capacite()
  
  /** Retourne une representation en String d'une pile, 
   * contenant la representation en String de chaque element.
   * @return une representation en String d'une pile
   */ 
  @Override public String toString()
  {
    String vS = "[";
    for ( int vI = this.taille()-1; vI>=0; vI-- ) {
      vS = vS + this.stk.elementAt(vI);
      if (vI>0)   vS = vS + ", ";
    }
    return vS + "]";
  } // toString()
} // Pile2