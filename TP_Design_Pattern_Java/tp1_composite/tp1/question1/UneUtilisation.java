package question1;

public class UneUtilisation
{
  public static void main( final String[] pArgs ) throws Exception
  { 
    Pile p1 = new Pile(6);
    Pile p2 = new Pile(10);
    // p1 est ici une pile de polygones réguliers PolygoneRegulier.java
    
    p1.empiler( new PolygoneRegulier(5,100) );
    p1.empiler( "polygone" );
    p1.empiler( new Integer(100) );
    System.out.println( " la pile p1 = " + p1 ); // Quel est le resultat ?              

    try {
      p1.empiler( new PolygoneRegulier(5,100) );
      // ....
      Boolean vB = (Boolean)p1.depiler(); // verifiez qu'une exception se produit
    } catch ( final Exception pE ) {
      pE.printStackTrace();
    } // catch
  } // main()
} // UneUtilisation
