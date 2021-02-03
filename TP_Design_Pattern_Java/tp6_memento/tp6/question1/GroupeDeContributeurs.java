package question1;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant>
{
    private List<Cotisant> aListe;

    public GroupeDeContributeurs( final String pNomDuGroupe )
    {
        super( pNomDuGroupe );
        this.aListe = new ArrayList<Cotisant>();
    } // GroupeDeContributeurs(.)

    public void ajouter( final Cotisant pCotisant )
    {
        this.aListe.add(pCotisant);
        pCotisant.setParent(this);
    } // ajouter(.)

    public int nombreDeCotisants()
    {
        int vNombre = 0;
        for (Cotisant vC : this.aListe)
        {
            vNombre+= vC.nombreDeCotisants();
        }
        return vNombre;
    } // nombreDeCotisants()

    @Override public String toString()
    {
        String vStr = "";
        Iterator<Cotisant> iC = this.iterator();
        while (iC.hasNext())
        {
            vStr += iC.next()+" ";
        }
        return vStr;
    } // toString()

    public List<Cotisant> getChildren()
    {
        return new ArrayList(this.aListe);
    } // getChildren()
    
    public void debit( final int pSomme ) throws SoldeDebiteurException
    {
        if (pSomme < 0)
            throw new RuntimeException("nombre négatif !!!");
        else
        {   for (Cotisant vC : this.getChildren())
            {
                if ((vC.solde() - pSomme) < 0)
                    throw new SoldeDebiteurException("solde négatif !!!");
                else
                    vC.debit(pSomme);
            }
        }
    } // debit(.)

    public void credit( final int pSomme )
    {
        if (pSomme < 0)
            throw new RuntimeException("nombre négatif !!!");
        else
            for (Cotisant vC : this.aListe)
                    vC.credit(pSomme);
    } // credit(.)

    public int solde()
    {
        int vSolde = 0;
        for (Cotisant vC : this.aListe)
        {
            vSolde+= vC.solde();
        }
        return vSolde;
    } // solde()

    // méthodes fournies

    public Iterator<Cotisant> iterator()
    {
        return new GroupeIterator( this.aListe.iterator() );
    } // iterator()

    private class GroupeIterator implements Iterator<Cotisant>
    {
        private Stack<Iterator<Cotisant>> aStk;

        public GroupeIterator( final Iterator<Cotisant> pIterator )
        {
            this.aStk = new Stack<Iterator<Cotisant>>();
            this.aStk.push( pIterator );
        } // GroupeIterator(.)

        @Override public boolean hasNext()
        {
            if ( this.aStk.empty() )
                return false;

            Iterator<Cotisant> vIterator = this.aStk.peek();
            if( ! vIterator.hasNext() ) {
                this.aStk.pop();
                return this.hasNext();
            } else {
                return true;
            }
        } // hasNext()

       @Override public Cotisant next()
       {
            if ( this.hasNext() ) {
                Iterator<Cotisant> vIterator = this.aStk.peek();
                Cotisant vCpt = vIterator.next();
                if ( vCpt instanceof GroupeDeContributeurs ) {
                    GroupeDeContributeurs vGr = (GroupeDeContributeurs)vCpt;
                    this.aStk.push( vGr.aListe.iterator() );
                }
                return vCpt;
            } else {
                throw new NoSuchElementException();
            }
       } // next()

        @Override public void remove()
        {
            throw new UnsupportedOperationException();
        } // remove()
    } // GroupeIterator

    @Override public <T> T accepter( final Visiteur<T> pVisiteur )
    {
        return pVisiteur.visite( this );
    }
} // GroupeDeContributeurs
