package question3;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.Observable;
import java.util.Observer;

/**
 * Decrivez votre classe Controleur ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Vue extends JPanel
{// à completer 
  private JLabel etatPile;
  private PileModele<Integer> pile;

  public Vue( PileModele<Integer> pile )
  {
    super();
    this.pile= pile;
    this.etatPile= new JLabel( "entrez des nombres entiers" );
    setLayout( new FlowLayout( FlowLayout.LEFT ) );
    add( etatPile );
    setBackground( Color.green );
    // inscription aupres du modele comme observateur
  } // Vue()

  public void update( Observable obs, Object arg )
  {
    etatPile.setText( pile.toString() ); // ou obs.toString()
  } // update()

} // Vue
