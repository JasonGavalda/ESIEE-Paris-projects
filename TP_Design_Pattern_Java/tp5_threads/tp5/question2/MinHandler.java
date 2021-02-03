package question2;

import java.util.Calendar;
import java.text.DateFormat;

/**
 * Decrivez votre classe MinHandler ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class MinHandler extends Handler<Float>
{

  public MinHandler( final Handler<Float> pSuccessor )
  {
    super( pSuccessor );
  } // MinHandler(.)

  public boolean handleRequest( final Float pValue )
  {
      if (0 <= pValue && pValue < 35)
      {
        System.out.println( "ds2438.value : " + pValue);
        return true;
      }
      return super.handleRequest( pValue );
  } // handleRequest(.)
} // MinHandler
