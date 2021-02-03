package question3;

import question1.PolygoneRegulier;

public class UneUtilisation
{
  public static void main( final String[] pArgs ) throws Exception
  {
    PileI<PolygoneRegulier> p1 = new Pile2<PolygoneRegulier> (6);
    PileI<PileI<PolygoneRegulier>> p2 = new Pile2<PileI<PolygoneRegulier>> (10);
    
    // // p1 est ici une pile de polygones reguliers PolygoneRegulier.java
    p1.empiler( new PolygoneRegulier(4,100) );
    p1.empiler( new PolygoneRegulier(5,100) );

    System.out.println( " la pile p1 = " + p1 ); // Quel est le resultat ?
                                          
    p2.empiler( p1 );
    System.out.println( " la pile p2 = " + p2 ); 

    try {
      p1.empiler( new PolygoneRegulier(5,100) ); 
      // // ....
      try {
      String s = p1.depiler().toString();     //desormais une erreur de compilation
      }
      catch (Exception PileVideException)   {
      }
    } catch( final Exception pE ) {
        pE.printStackTrace();
    }
  } // main()
} // UneUtilisation