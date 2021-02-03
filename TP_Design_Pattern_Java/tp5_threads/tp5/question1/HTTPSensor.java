// ligne 53 a completer pour q1.2 <<< <<< <<<
package question1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.*;

import java.net.URL;
import java.net.URLConnection;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;

import java.text.NumberFormat;

/** Classe de lecture d'un capteur sur le Web
 * @version de test
 * @author jm Douin
 */
public class HTTPSensor extends HumiditySensor
{
  public static final long ONE_MINUTE= 60L * 1000L;
//  public final static String DEFAULT_HTTP_SENSOR= "http://lmi92.cnam.fr:8999/ds2438/";
  public final static String DEFAULT_HTTP_SENSOR= "http://localhost:8999/ds2438/";

  /** l'URL associee au capteur */
  private String aUrlSensor;

  /** Constructeur d'une connexion avec un Capteur, valeur par defaut
   * @param  urlSensor l'URL du capteur sur le Web en protocole HTTP
   */
  public HTTPSensor()
  {
    this( DEFAULT_HTTP_SENSOR );
  } // HTTPSensor()

  /** Constructeur d'une connexion avec un Capteur, syntaxe habituelle
   * http://site:port/
   * @param  urlSensor l'URL du capteur sur le Web en protocole HTTP
   */
  public HTTPSensor( String pUrlSensor )
  {
    this.aUrlSensor = pUrlSensor;
  } // HTTPSensor(.)

  /** Lecture de la valeur de humidite relative
   **/
  public float value() throws Exception
  {
    StringTokenizer stk = new StringTokenizer(this.request(), "=");
    stk.nextToken();
    String s;
    float resultat = Float.parseFloat(stk.nextToken());
    if (resultat < 10)
    {
        s = ""+resultat;
        s = s.substring(0,3);
        return Float.parseFloat(s);
    }
    s = ""+resultat;
    s = s.substring(0,4);
    return Float.parseFloat(s); // point de rosee
  } // value()

  // NE PAS MODIFIER :
  /** periode minimale de scrutation
   * @return la periode minimale (en local ou a distance)
   */
  public long minimalPeriod()
  {
    if ( !this.aUrlSensor.contains( ".cnam." ) )
      return 500L;
    else
      return ONE_MINUTE;
  } // minimalPeriod()

  /** lecture de l'URL
   * @return l'url associee a ce capteur
   */
  public String getUrl()
  {
    return this.aUrlSensor;
  } // getUrl()

  /** Lecture des informations issues de ce capteur
   * @return la totalite de la page lue
   * @throws Exception en cas d'erreur
   */
  public String request() throws Exception
  {
    return this.request( new Properties() );
  } // request()

  /** Lecture des informations issues de ce capteur
   * @param params les parametres 
   * @return la totalite de la page lue
   * @throws Exception en cas d'erreur
   */
  public String request( final Properties params ) throws Exception
  {
    String vParametres = "";
    for ( Enumeration vE = params.keys(); vE.hasMoreElements(); ) {
      String vKey = (String)vE.nextElement();
      String vValue= (String)params.get( vKey );
      vParametres += vKey + "=" + vValue;
      if ( vE.hasMoreElements() )
        vParametres += "&";
    } // for
    if ( !vParametres.equals( "" ) ) {
      this.aUrlSensor += "?" + vParametres;
    }

    URL vUrl= new URL( this.aUrlSensor );
    URLConnection vConnection= vUrl.openConnection();

    BufferedReader vIn= new BufferedReader( 
        new InputStreamReader( vConnection.getInputStream() ) );
    String vResult = "";
    String vInputLine= vIn.readLine();
    while ( vInputLine != null ) {
      vResult += vInputLine;
      vInputLine = vIn.readLine();
    }
    vIn.close();
    return vResult;
  } // request()

  /** Mise en place du proxy si necessaire
   *  rappel a l'esiee : proxyHost=cache.esiee.fr proxyPort=3128
   *  attention, aucune verification de la validite de l'URL transmise n'est effectuee
   *  @param proxyHost adresse du proxy
   *  @param proxyPort le port du proxy
   */
  public static void setHttpProxy( final String pProxyHost, final int pProxyPort )
  {
    Properties vProp = System.getProperties();
    vProp.put( "proxySet", "true" );
    vProp.put( "http.proxyHost", pProxyHost );
    vProp.put( "http.proxyPort", Integer.toString( pProxyPort ) );
  } // setHttpProxy(..)

  // bloc d'initialisation statique pour l'esiee
  private static final boolean ESIEE_INSIDE = true;
  static {
    if ( ESIEE_INSIDE ) {
      setHttpProxy( "cache.esiee.fr", 3128 );
    }
  } // static
} // HTTPSensor
