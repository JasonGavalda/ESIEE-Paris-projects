package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Vector;
/**
 * Decrivez votre classe PileVector ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Pile3 implements PileI
{
  private Vector<Object> v;

  public Pile3( final int pCapacite )
  {
    if (pCapacite <=0)
        this.v = new Vector<Object>(CAPACITE_PAR_DEFAUT);
    else
        this.v = new Vector<Object>(pCapacite);
  } // Pile3(.)
  
  public Pile3()
  {
    this( 0 );
  } // Pile3()
  
  public void empiler( final Object pO ) throws PilePleineException
  {
      if ( this.estPleine() )   throw new PilePleineException();
      this.v.add(pO);
  } // empiler(.)
  
  public Object depiler() throws PileVideException
  {
    if (this.estVide() ) throw new PileVideException();
    return this.v.remove(this.v.size() - 1);
  } // depiler()
  
  public Object sommet() throws PileVideException
  {
    return this.v.lastElement();
  } // sommet()
  
  public int taille()
  {
    return this.v.size();
  } // taille()
  
  public int capacite()
  {
    return this.v.capacity();
  } // capacite()
  
  public boolean estVide()
  {
    return this.v.isEmpty();
  } // estVide()
  
  public boolean estPleine()
  {
    return (this.taille()== this.capacite());
  } // estPleine()
  
  @Override public String toString()
  {
    String s = "[";
    int t = this.taille();
    try {
    Object PileAux = this.clone();
    for (int i = 0; i<t; i++) {
        try {
            s+= ((Pile3)PileAux).depiler();
        }
        catch (Exception PileVideException) {
        }
        finally {
            if (((Pile3)PileAux).estVide() == false)
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
    else if (this.taille() != ((Pile3)pO).taille())
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
    Pile3 vP = new Pile3( this.capacite() );
    vP.v = (Vector<Object>)this.v.clone();
    return vP;
  }
}
