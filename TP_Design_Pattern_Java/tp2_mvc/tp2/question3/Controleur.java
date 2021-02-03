package question3;

import question3.tp_pile.PileI;
import question3.tp_pile.Pile2;
import question3.tp_pile.PilePleineException;
import question3.tp_pile.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.Observable;
/**
 * Decrivez votre classe Controleur ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Controleur extends JPanel
{
  private JButton push, add, sub, mul, div, clear;
  protected PileModele<Integer> pile;
  private JTextField donnee;

  public Controleur( PileModele<Integer> pile )
  {
    super();
    this.pile= pile;
    this.donnee= new JTextField( 8 );

    this.push= new JButton( "push" );
    this.add= new JButton( "+" );
    this.sub= new JButton( "-" );
    this.mul= new JButton( "*" );
    this.div= new JButton( "/" );
    this.clear= new JButton( "[]" );

    class DonneeField extends Observable implements ActionListener
    {
        private String aNom;
        
        public DonneeField(String pNom)
        {
            this.aNom = pNom;
        }
        
        public void actionPerformed (ActionEvent ae)
        {
            setChanged();
            notifyObservers(this);
        }
    }
    
    class PushButton extends Observable implements ActionListener
    {
        private String aNom;
        
        public PushButton (String pNom)
        {
            this.aNom = pNom;
        }
    
        public void actionPerformed (ActionEvent ae)
        {
            try
            {
                pile.empiler(operande());
                actualiserInterface();
                donnee.setText("");
                setChanged();
                notifyObservers(this);
            }
            catch (PilePleineException pE)
            {
                donnee.setText("Pile Pleine ! (effacez ce message pour continuer) ");
            }
            catch (NumberFormatException pE)
            {
                donnee.setText("Ce nombre n'est pas entier ! (effacez ce message pour continuer) ");    
            }
        }
    }
    
    class AddButton extends Observable implements ActionListener
    {
        private String aNom;
        
        public AddButton (String pNom)
        {
            this.aNom = pNom;
        }
        
        public void actionPerformed (ActionEvent ae)
        {
            try
            {
                pile.empiler(pile.depiler()+pile.depiler());
                actualiserInterface();
                setChanged();
                notifyObservers(this);
            }
            catch (PilePleineException pE)
            {
                   donnee.setText("Pile Pleine ! (effacez ce message pour continuer) "); 
            }
            catch (PileVideException pE)
            {
                   donnee.setText("Pile Vide ! (effacez ce message pour continuer) ");
            }
        }
    }
    
    class SubButton extends Observable implements ActionListener
    {
        private String aNom;
        
        public SubButton (String pNom)
        {
            this.aNom = pNom;
        }
        
        public void actionPerformed (ActionEvent ae)
        {
            try
            {
                int x = pile.depiler();
                int y = pile.depiler();
                pile.empiler(y-x);
                actualiserInterface();
                setChanged();
                notifyObservers(this);
            }
            catch (PilePleineException pE)
            {
                  donnee.setText("Pile Pleine ! (effacez ce message pour continuer) ");  
            }
            catch (PileVideException pE)
            {
                donnee.setText("Pile Vide ! (effacez ce message pour continuer) ");
            }
        }
    }
    
    class MulButton extends Observable implements ActionListener
    {
        private String aNom;
        
        public MulButton (String pNom)
        {
            this.aNom = pNom;
        }
        
        public void actionPerformed (ActionEvent ae)
        {
            try
            {
                pile.empiler(pile.depiler()*pile.depiler());
                actualiserInterface();
                setChanged();
                notifyObservers(this);
            }
            catch (PilePleineException pE)
            {
                donnee.setText("Pile Pleine ! (effacez ce message pour continuer) ");
            }
            catch (PileVideException pE)
            {
                donnee.setText("Pile Vide ! (effacez ce message pour continuer) ");
            }
        }
    }
    
    class DivButton extends Observable implements ActionListener
    {
        private String aNom;
        
        public DivButton (String pNom)
        {
            this.aNom = pNom;
        }
        
        public void actionPerformed (ActionEvent ae)
        {
            try
            {   
                if (pile.sommet() != 0)
                {
                    int x = pile.depiler();
                    int y = pile.depiler();
                    pile.empiler((int)y/x);
                    actualiserInterface();
                    setChanged();
                    notifyObservers(this);
                }
                else donnee.setText ("Division par 0 !");
            }
            catch (PilePleineException pE)
            {
                donnee.setText("Pile Pleine ! (effacez ce message pour continuer) ");    
            }
            catch (PileVideException pE)
            {
                donnee.setText("Pile Vide ! (effacez ce message pour continuer) ");
            }
        }
    }
    
    class ClearButton extends Observable implements ActionListener
    {
        private String aNom;
        
        public ClearButton (String pNom)
        {
            this.aNom = pNom;
        }
        
        public void actionPerformed (ActionEvent ae)
        {
            try
            {   
                while (!pile.estVide())
                    pile.depiler();
                actualiserInterface();
                setChanged();
                notifyObservers(this);
            }
            catch (PileVideException pE)
            {
               donnee.setText("Pile Vide ! (effacez ce message pour continuer) "); 
            }
        }
    }
    
    setLayout( new GridLayout( 2, 1 ) );
    add( donnee );
    ActionListener bL = new DonneeField ("donnee");
    donnee.addActionListener(bL);
    JPanel boutons= new JPanel();
    boutons.setLayout( new FlowLayout() );
    boutons.add( push );
    ActionListener pL = new PushButton ("pushListener");
    push.addActionListener(pL);
    boutons.add( add );
    ActionListener aL = new AddButton ("addListener");
    add.addActionListener(aL);
    boutons.add( sub );
    ActionListener sL = new SubButton ("subListener");
    sub.addActionListener(sL);
    boutons.add( mul );
    ActionListener mL = new MulButton ("mulListener");
    mul.addActionListener(mL);
    boutons.add( div );
    ActionListener dL = new DivButton ("divListener");
    div.addActionListener(dL);
    boutons.add( clear );
    ActionListener cL = new ClearButton ("clearListener");
    clear.addActionListener(cL);
    add( boutons );
    boutons.setBackground( Color.red );
    actualiserInterface();
  } // Controleur()

  public void actualiserInterface()
  {
      if (this.pile.estPleine())
      {
          push.disable();
      }
      else if (this.pile.estVide())
      {
          add.disable();
          sub.disable();
          mul.disable();
          div.disable();
          clear.disable();
      }
      else
      {
          push.enable();
          add.enable();
          sub.enable();
          mul.enable();
          div.enable();
          clear.enable();
      }
      try
      {
          donnee.setText(pile.sommet().toString());
      }
      catch (PileVideException pE)
      {
      }
  } // actualiserInterface()

  private Integer operande() throws NumberFormatException
  {
    return Integer.parseInt( donnee.getText() );
  } // operande()
  // a completer
  // en cas d'exception comme division par zero, mauvais format de nombre
  // la pile doit rester en l'etat (intacte)

} // Controleur
