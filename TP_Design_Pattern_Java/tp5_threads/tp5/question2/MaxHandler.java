package question2;

/**
 * Decrivez votre classe MaxHandler ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
import java.util.Calendar;
import java.text.DateFormat;

public class MaxHandler extends Handler<Float>
{

  public MaxHandler( final Handler<Float> pSuccessor )
  {
    super( pSuccessor );
  } // MaxHandler(.)

  public boolean handleRequest( final Float pValue )
  {
    if (35 <= pValue && pValue <100)
    {
        System.out.println( "ds2438.value : " + pValue);
        return true;
    }
    return super.handleRequest( pValue );
  } // handleRequest(.)
} // MaxHandler
