package question2;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.TextArea;

/**
 * Decrivez votre classe JMouseObserver ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class JMouseObserver implements MouseListener
{
  private String nom;
  private TextArea contenu;

  /**
   * Constructeur d'objets de classe JButtonObserver
   */
  public JMouseObserver( String nom, TextArea contenu )
  {
    this.nom= nom;
    this.contenu= contenu;
  } // JMouseObserver(()

  public void mouseClicked( MouseEvent e )
  {
  }

  /** affichage d'un message dans la zone de texte
   * ce message est de la forme observateur this.nom : souris entree en (X,Y)
   * exemple : observateur jmo1 : souris entree en (15,20)
   * @param ???
   */
  public void mouseEntered( MouseEvent e )
  { // a completer
    String message= "observateur" + this.nom + " : " + "souris entree en (" + e.getX() + ", " + e.getY() + ")" ;
    contenu.append( message + "\n" );
  } // mouseEntered()

  public void mouseExited( MouseEvent e )
  {
    String message= "observateur" + this.nom + " : " + "souris sortie en (" + e.getX() + ", " + e.getY() + ")" ;
    contenu.append( message + "\n" );
  }

  public void mousePressed( MouseEvent e )
  {
  }

  public void mouseReleased( MouseEvent e )
  {
  }

} // JMouseObserver
