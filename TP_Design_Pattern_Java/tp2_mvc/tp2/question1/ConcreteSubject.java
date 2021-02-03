/**/ package question1;

/**/ import java.util.ArrayList;
/**/ import java.util.Observable;

/**
 * Decrivez votre classe ConcreteSubject ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
/**/ public class ConcreteSubject extends Observable
/**/ {
       /** une liste */
/**/   private ArrayList<String> list;

/**/   public ConcreteSubject()
/**/   {
/**/     list= new ArrayList<String>();
/**/   }

/**/   public void insert( String name )
/**/   {
/**/     list.add( name );
/**/     setChanged();
/**/     notifyObservers( name );
/**/   }

/**/   public String toString()
/**/   {
/**/     return list.toString();
/**/   }

/**/ } // ConcreteSubject
