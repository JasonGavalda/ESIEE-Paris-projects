package question3;

import java.util.Stack;
import java.util.Observer;
import java.util.Observable;

public class PileModeleTest extends junit.framework.TestCase
{
  private question3.PileModele<Integer> pile;
  private Stack<Observable> stk= null;
  private int tailleAttendue= 0;

  @SuppressWarnings("unchecked")
  private class ObserverTest extends junit.framework.TestCase implements Observer
  {
    public void update( Observable obs, Object arg )
    {
      stk.push( obs );
      if ( tailleAttendue != ((question3.PileModele<Integer>)obs).taille() ) {
      // un pb de compilation ?  PileModele est-elle correcte ?
        fail( " est-ce une notification avant le changement d'etat du modele ???"
            + (tailleAttendue == 0 ? " voir depiler" : "") );
      }
    } // update()
  } // ObserverTest

  public void setUp()
  {
    pile= new question3.PileModele<Integer>(
        new question3.tp_pile.Pile2<Integer>( 5 ) );
    pile.addObserver( new ObserverTest() );
    stk= new Stack<Observable>();
  } // setUp()

  public void test_PileModele_empiler() throws Exception
  {
    try {
      tailleAttendue= 1;
      pile.empiler( 32 );
      assertTrue( "update sans effet, lors de empiler ???", pile.toString()
          .equals( stk.pop().toString() ) );
    }
    catch ( java.util.EmptyStackException e ) {
      fail( "empiler ne notifie pas les observateurs inscrits ?, update est sans effet ?" );
    }
  } // test_PileModele_empiler()

  public void test_PileModele_depiler() throws Exception
  {
    try {
      try {
        tailleAttendue= 1;
        pile.empiler( 32 );
        assertTrue( "update sans effet, lors de empiler ???", pile.toString()
            .equals( stk.pop().toString() ) );
      }
      catch ( java.util.EmptyStackException e ) {
        fail( "empiler ne notifie pas les observateurs inscrits ?, update est sans effet ?" );
      }
      //   		  System.out.println("pile: " + pile);
      tailleAttendue= 0;
      Integer i= pile.depiler();
      assertTrue( "l'element empile n'est pas restitue ???", i
          .equals( new Integer( 32 ) ) );
      assertTrue( "update sans effet, lors de depiler ?", pile.toString()
          .equals( stk.pop().toString() ) );
    }
    catch ( java.util.EmptyStackException e ) {
      fail( "depiler ne notifie pas les observateurs inscrits ???" );
    }
  } // test_PileModele_depiler()

  public void test_PileModele_depiler2() throws Exception
  {
    try {
      try {
        tailleAttendue= 1;
        pile.empiler( 32 );
        assertEquals( "update sans effet, lors de empiler ???",
            pile.toString(), stk.pop().toString() );
      }
      catch ( java.util.EmptyStackException e ) {
        fail( "empiler ne notifie pas les observateurs inscrits ?, update est sans effet ?" );
      }
      tailleAttendue= 0;
      pile.depiler();
      assertEquals( "update trop tot, est-il avant le depiler effectif ?", pile
          .toString(), stk.pop().toString() );
    }
    catch ( java.util.EmptyStackException e ) {
      fail( "depiler ne notifie pas les observateurs inscrits ???" );
    }
  } // test_PileModele_depiler2()

  public void test_PileModele_accesseurs() throws Exception
  {
    assertTrue( stk.empty() );
    pile.estVide();
    assertTrue( "update lors de estVide ??? ", stk.empty() );

    try {
      tailleAttendue= 1;
      pile.empiler( 32 );
      assertEquals( "update sans effet, lors de empiler ???", pile.toString(),
          stk.pop().toString() );
    }
    catch ( java.util.EmptyStackException e ) {
      fail( "empiler ne notifie pas les observateurs inscrits ???" );
    }
    pile.sommet();
    assertTrue( "update lors de sommet ??? ", stk.empty() );

    pile.estPleine();
    assertTrue( "update lors de estPleine ??? ", stk.empty() );
  } // test_PileModele_accesseurs()

} // PileModeleTest
