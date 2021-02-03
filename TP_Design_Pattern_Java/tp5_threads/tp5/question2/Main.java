package question2;

import question1.HTTPSensor;
import question1.CyclicAcquisition;

/**
 * Decrivez votre classe Main ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Main
{

  public static void main( final String[] pArgs ) throws Exception
  {
    HTTPSensor.setHttpProxy( "cache.esiee.fr", 3128 );
    HTTPSensor vDS2438;
    if ( pArgs.length == 0 )
      vDS2438 = new HTTPSensor();
    else
      vDS2438 = new HTTPSensor( pArgs[0] );

    //a completer :
    Handler<Float> vChain = new TraceHandler(new MinHandler(new MaxHandler(new FileHandler ("mesures", null))));

    CyclicAcquisition vAcquisition = new CyclicAcquisition( vDS2438, 1000,
        new ChainCommand<Float>( vChain ) );
    vAcquisition.start();

    Thread.sleep( 1000 * 10 );

    vAcquisition.stop();
  } // main()
} // Main
