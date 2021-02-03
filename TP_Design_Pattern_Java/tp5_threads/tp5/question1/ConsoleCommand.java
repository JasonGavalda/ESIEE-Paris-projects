package question1;

import java.util.Calendar;
import java.text.DateFormat;

/**
 * Decrivez votre classe ConsoleCommand ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class ConsoleCommand<T> implements Command<T>
{

  public void make( final T pF )
  {
    String vDate = DateFormat.getTimeInstance().format(
        Calendar.getInstance().getTime() );
    System.out.println( "[" + vDate + "] ds2438.value : " + pF );
  } // make(.)

} // ConsoleCommand
