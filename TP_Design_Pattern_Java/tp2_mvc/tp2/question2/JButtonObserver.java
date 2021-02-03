package question2;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

/**
 * Decrivez votre classe JButtonObserver ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class JButtonObserver implements ActionListener
{ // a completer
  private String nom;
  private TextArea contenu;

  /**
   * Constructeur d'objets de classe JButtonObserver
   */
  public JButtonObserver( String nom, TextArea contenu )
  {
    this.nom= nom;
    this.contenu= contenu;
  } // JButtonObserver()

  /** affichage d'un message dans la zone de texte
   * ce message est de la forme observateur this.nom : clic du bouton nom_du_bouton
   * exemple : observateur jbo1 : clic du bouton A
   * @param ???
   */
  public void actionPerformed(ActionEvent ae)
  { // a completer
    String message= "observateur" + this.nom + " : " + "clic du bouton " + ae.getActionCommand() ;
    contenu.append(message + "\n" );
  } // actionPerformed()
  
} // JButtonObserver
