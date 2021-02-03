package question2;

import java.io.File;
// import des classes de gestion des fichiers en ecriture
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.ObjectOutputStream;
// import des classes de gestion des fichiers en lecture
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
// exceptions susceptibles d'etre engendrees lors d'une operation de lecture ou d'ecriture
import java.io.IOException;
import java.io.FileNotFoundException;
// exception liee a la serialisation
import java.lang.ClassCastException;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
/* a completer */
import java.util.Set;

import java.util.Calendar;
import java.util.Locale;
import java.util.Iterator;
import java.util.Date;

import java.text.DateFormat;

// emploi de getResource (voir java.lang.Class)
import java.net.URL;

public class FileHandler  extends Handler<Float>
{
  private static final int MAX= 5;
  private String aFileName;
  private int aCounter;

  private SortedMap<Date, Float> aTable;

  public FileHandler( final String pFileName, final Handler<Float> pSuccessor )
  {
    super( pSuccessor );

    this.aFileName = pFileName;
    this.aCounter  = 0;
    this.aTable    = new TreeMap<Date, Float>();
  } // FileHandler(..)

  public boolean handleRequest( final Float pValue )
  {
    Calendar  vCal = Calendar.getInstance();
    DateFormat vDF = DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE );
    String   vDate = vDF.format( vCal.getTime() );
    this.aTable.put(vCal.getTime(), pValue);
    this.aCounter +=1;
    try {
        writeHTML();
    }
    catch (IOException pE)
    {
        System.out.println("Erreur IO");
    }
    return super.handleRequest( pValue );
  } // handleRequest(.)

  /** Cette methode genere un fichier HTML .
   */
  private void writeHTML() throws IOException
  {
    BufferedWriter vBW = new BufferedWriter( new PrintWriter(
        new FileWriter( this.aFileName + ".html" ) ) );
    vBW.write( "<html><head><title>mesures du taux d'humidité relative</title>" );
    vBW.write( "<meta http-equiv=\"refresh\" content=\"30\">" );
    vBW.write( "</head><body><br>\r\n<table border=\"2\" bgcolor=\"#CBFEEA\">" );

    vBW.write("<td><tr>"+this.aTable.lastKey()+"</td> <td>"+this.aTable.get(this.aTable.lastKey())+"</td></tr>");
    
    
    vBW.write( "</table>\r\n</body></html>" );
    vBW.close();
  } // writeHTML()

} // FileHandler
