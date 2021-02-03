package question2;

import question1.PilePleineException;
import question1.PileVideException;

public interface PileI
{  
  // a n'utiliser que lorsque le parametre du constructeur
  // est absent ou negatif :
  public final static int CAPACITE_PAR_DEFAUT = 6;
    
  public void empiler( final Object pO ) throws PilePleineException;
  
  public Object depiler() throws PileVideException;
  
  // retourne le sommet de la pile :
  public Object sommet() throws PileVideException;
  
  // nb de cases :
  public int capacite();
  
  // nb d'elements presents :
  public int taille();
  
  public boolean estVide();
  
  public boolean estPleine();
  
  public String toString();

  public boolean equals( final Object pO );
  
  public int hashCode();
  
  public Object clone() throws CloneNotSupportedException;
} // PileI