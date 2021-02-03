package question1;

/**
 * Decrivez votre classe Main ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Main
{

  public static void main( final String[] pArgs )
  {
    HTTPSensor.setHttpProxy( "cache.esiee.fr", 3128 );

    HTTPSensor vDS2438;
    if ( pArgs.length == 0 )
      vDS2438 = new HTTPSensor();
    else
      vDS2438 = new HTTPSensor( pArgs[0] );

    try {
      
      System.out.println( "requête auprès du ds2438 : "+ vDS2438.value() + vDS2438.value());
      
    }
    catch ( final Exception pE ) {
      pE.printStackTrace();
    }
  } // main(.)
} // Main
