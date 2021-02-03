    package question1;

/**
 * Décrivez votre classe FahrenheitCelsius ici.
 * 
 * @author (votre nom) 
 * @version (un numéro de version ou une date)
 */
public class FahrenheitCelsius
{
     /** 
      * le point d'entrée de cette application, 
      * dont le commentaire est à compléter
      *
      * @param args Un tableau de températures en degré Fahrenheit sous forme
      * de String à convertir en degré Celsius.
      */
     public static void main( String[] args )
     {
       // pour tous les paramètres de la ligne de commande
       for (int i=0; i<args.length; i++) {
       int fahrenheit = Integer.parseInt(args[i]);
       float celsius  = fahrenheitEnCelsius(fahrenheit);
       celsius = (float)((int)(celsius*10))/10;
       System.out.println( fahrenheit + "\u00B0F -> " + celsius + "\u00B0C" );
       }// format imposé
     } // main()
     
     /** 
      * la méthode à compléter. 
      * @param f  la valeur en degré Fahrenheit
      * @return   la conversion en degré Celsius
      */
     public static float fahrenheitEnCelsius( int f )
     {
         return 5.0f/9.0f*(f-32);// à compléter en remplaçant 0.F par l'appel de la fonction de conversion
     } // fahrenheitEnCelsius()

} // FahrenheitCelsius
