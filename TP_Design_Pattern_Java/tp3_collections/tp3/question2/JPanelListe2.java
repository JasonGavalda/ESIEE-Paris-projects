package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.lang.String;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class JPanelListe2 extends JPanel implements ActionListener, ItemListener {

    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();

    private List<String> liste;
    private Map<String, Integer> occurrences;
    private Caretaker saves;

    public JPanelListe2(List<String> liste, Map<String, Integer> occurrences) {
        this.liste = liste;
        this.occurrences = occurrences;       
        this.saves = new Caretaker();
        
        cmd.setLayout(new GridLayout(3, 1));

        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);


        if(liste!=null && occurrences!=null){
            afficheur.setText(liste.getClass().getName() + " et "+ occurrences.getClass().getName());
            texte.setText(liste.toString());
        }else{
            texte.setText("la classe Chapitre2CoreJava semble incomplète");
        }

        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");

        boutonRechercher.addActionListener(this);
        boutonRetirer.addActionListener(this);
        boutonOccurrences.addActionListener(this);
        boutonAnnuler.addActionListener(this);
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("résultat de la recherche de : "
                    + saisie.getText() + " -->  " + res);
            } else if (ae.getSource() == boutonRetirer) {
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie
                    .getText());
                afficheur
                .setText("résultat du retrait de tous les éléments commençant par -->  "
                    + saisie.getText() + " : " + res);
            } else if (ae.getSource() == boutonOccurrences) {
                Integer occur = occurrences.get(saisie.getText());
                if (occur != null)
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                else
                    afficheur.setText(" -->  ??? ");
            } else if (ae.getSource() == boutonAnnuler) {
                Memento m = this.saves.getMemento();
                liste = restoreListFromMemento(m);
                occurrences = restoreMapFromMemento(m);
            }
            texte.setText(liste.toString());

        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        Memento m = storeInMemento();
        this.saves.addMemento(m);
        if (ie.getSource() == ordreCroissant)
            Collections.sort(liste);
        else if (ie.getSource() == ordreDecroissant)
            Collections.sort(liste, Collections.reverseOrder());

        texte.setText(liste.toString());
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
        boolean resultat = false;
        String mC = prefixe;
        for (Iterator iT = liste.iterator(); iT.hasNext();)
        {
            String mot = (String) iT.next();
            if(mot.startsWith(prefixe))
            {
                mC = mot;
                break;
            }
        }
        
        if (liste.contains(mC))
        {
            Memento m = storeInMemento();
            this.saves.addMemento(m);
        }
        
        for (Iterator iT = liste.iterator(); iT.hasNext();)
        {
            String motComplet = (String) iT.next();
            if(motComplet.startsWith(prefixe))
            {
                iT.remove();
                resultat = true;
                occurrences.replace(motComplet, occurrences.get(motComplet), occurrences.get(motComplet)-1);
            }
        }
        return resultat;
    }

    public Memento storeInMemento() {
        LinkedList<String> savedList = new LinkedList<String>();
        savedList.addAll(this.liste);
        HashMap<String, Integer> savedOccurrences = new HashMap<String, Integer>();
        savedOccurrences.putAll(this.occurrences);
        return new Memento(savedList, savedOccurrences);
    }
    
    public List restoreListFromMemento(Memento memento)  {
        return memento.getSavedList();
    }
    
    public Map restoreMapFromMemento(Memento memento)   {
        return memento.getSavedMap();
    }
}