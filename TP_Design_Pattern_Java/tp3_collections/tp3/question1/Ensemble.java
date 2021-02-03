package question1;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Vector;

public class Ensemble<T> extends AbstractSet<T>
{
  private Vector<T> aTable;

  public Ensemble() { this.aTable = new Vector<T>(); }
  
  @Override public int size() { return this.aTable.size(); }

  @Override public Iterator<T> iterator() { return this.aTable.iterator(); }

  @Override public boolean add( final T t )
  { //A compléter
    if (!this.contains(t))
    {
        return this.aTable.add(t);
    }
    else
        return false;
  } // add()
  
  public Ensemble<T> union( final Ensemble<? extends T> e )
  { 
    // a completer pour la question1-2
    Ensemble<T> U = new Ensemble<T>();
    U.addAll(this);
    U.addAll(e);
    return U;
  } // union()
  
  public Ensemble<T> inter( final Ensemble<? extends T> e )
  {
    // a completer pour la question1-2
    Ensemble<T> I = new Ensemble<T>();
    I.addAll(this);
    I.retainAll(e);
    return I;
  } // inter()
  
  public Ensemble<T> diff( final Ensemble<? extends T> e )
  {
    // a completer pour la question1-2
    Ensemble<T> D = new Ensemble<T>();
    D.addAll(this);
    D.removeAll(e);
    return D;
  } // diff()
  
  Ensemble<T> diffSym( final Ensemble<? extends T> e )
  {
    // a completer pour la question1-2
    return this.union(e).diff(this.inter(e));
  } // diffSym()
} // Ensemble
