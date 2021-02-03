package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile2 implements PileI, Cloneable
{
  /** par delegation : utilisation de la class Stack */
  private Stack<Object> stk;

  /** la capacite de la pile */
  private int capacite;
  
  /** Creation d'une pile.
   * @param taille la taille de la pile, la taille doit etre > 0
   */
  public Pile2( final int pCapacite )
  {
    this.stk = new Stack<Object>();
    if (pCapacite <= 0)
        this.capacite = CAPACITE_PAR_DEFAUT;
    else
        this.capacite = pCapacite;
  } // Pile2(.)

  // constructeur fourni
  public Pile2()
  {
    this( 0 );
  } // Pile2()

  public void empiler( final Object pO ) throws PilePleineException
  {
    if ( this.estPleine() )   throw new PilePleineException();
    this.stk.push(pO);
  } // empiler(.)

  public Object depiler() throws PileVideException
  {
    if ( this.estVide() ) throw new PileVideException();
    return this.stk.pop();
  } // depiler()

  public Object sommet() throws PileVideException
  {
    if ( this.estVide() ) throw new PileVideException();
    return this.stk.peek();
  } // sommet()
  
  /** Effectue un test de l'etat de la pile.
   * @return vrai si la pile est vide, faux autrement
   */
  public boolean estVide()
  {
    return this.stk.empty();
  } // estVide()

  /** Effectue un test de l'etat de la pile.
   * @return vrai si la pile est pleine, faux autrement
   */   
  public boolean estPleine()
  {
    return (this.taille() == this.capacite);
  } // estPleine()

  /** Retourne le nombre d'element d'une pile.
   * @return le nombre d'elements presents
   */ 
  public int taille()
  {
    return this.stk.size();
  } // taille()
  
  /** Retourne la capacite de cette pile.
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
    String s = "[";
    int t = this.taille();
    try {
    Object PileAux = this.clone();
    for (int i = 0; i<t; i++) {
        try {
            s+= ((Pile2)PileAux).depiler();
        }
        catch (Exception PileVideException) {
        }
        finally {
            if (((Pile2)PileAux).estVide() == false)
                 s+= ", ";
        }
    }
    }
    catch (Exception CloneNotSupportedException) {
    }
    return s + "]";
  } // toString()
  
  @Override public boolean equals( final Object pO )
  {
    if (this.hashCode() == pO.hashCode())
        return true;
    else if (pO==null)
        return false;
    else if (this.getClass()!= pO.getClass())
        return false;
    else if (this.taille() != ((Pile2)pO).taille())
        return false;
    else {
        return (this.toString() == pO.toString());
    }
  } // equals(.)
  
  // fonction fournie
  @Override public int hashCode()
  {
    return this.toString().hashCode();
  } // hashCode()
  
  // fonction fournie
  @SuppressWarnings("unchecked")
  @Override public Object clone() throws CloneNotSupportedException
  {
    Pile2 vP = new Pile2( this.capacite() );
    vP.stk = (Stack<Object>)this.stk.clone();
    return vP;
  }
} // Pile2
