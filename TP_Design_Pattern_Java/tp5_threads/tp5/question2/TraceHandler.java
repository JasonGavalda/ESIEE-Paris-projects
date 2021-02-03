package question2;

import java.util.Calendar;
import java.text.DateFormat;
import java.util.Locale;

public class TraceHandler extends Handler<Float>
{
  public TraceHandler( final Handler<Float> pSuccessor )
  {
    super( pSuccessor );
  } // TraceHandler(.)

  public boolean handleRequest( final Float pValue )
  {
    Calendar  vCal = Calendar.getInstance();
    DateFormat vDF = DateFormat.getDateInstance( DateFormat.SHORT, Locale.FRANCE );
    DateFormat vDT = DateFormat.getTimeInstance( DateFormat.SHORT, Locale.FRANCE );
    String   vDate = vDF.format( vCal.getTime() ) + "-" + vDT.format( vCal.getTime() );
    System.out.println( "[" + vDate + "] ds2438.value : " + pValue );

    return super.handleRequest( pValue );
  } // handleRequest(.)
} // TraceHandler
