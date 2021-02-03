package question3;

import question1.*;
import java.util.*;

public class Memento
{
    private HashMap<Cotisant, Integer> etatCotisants;
    private VisiteurRestituant vR;
    private VisiteurSauvegardant vS;

    public Memento( final Cotisant pC )
    {
        this.vS = new VisiteurSauvegardant();
        this.etatCotisants = pC.accepter(this.vS);
        vR = new VisiteurRestituant(this.etatCotisants);
    } // Memento(.)

    public void setState( final Cotisant pC )
    {
        pC.accepter(this.vR);
    } // setState(.)
} // Memento