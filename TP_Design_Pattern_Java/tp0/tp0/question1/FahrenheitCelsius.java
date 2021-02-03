    package question1;

/**
 * D�crivez votre classe FahrenheitCelsius ici.
 * 
 * @author (votre nom) 
 * @version (un num�ro de version ou une date)
 */
public class FahrenheitCelsius
{
     /** 
      * le point d'entr�e de cette application, 
      * dont le commentaire est � compl�ter
      *
      * @param args Un tableau de temp�ratures en degr� Fahrenheit sous forme
      * de String � convertir en degr� Celsius.
      */
     public static void main( String[] args )
     {
       // pour tous les param�tres de la ligne de commande
       for (int i=0; i<args.length; i++) {
       int fahrenheit = Integer.parseInt(args[i]);
       float celsius  = fahrenheitEnCelsius(fahrenheit);
       celsius = (float)((int)(celsius*10))/10;
       System.out.println( fahrenheit + "\u00B0F -> " + celsius + "\u00B0C" );
       }// format impos�
     } // main()
     
     /** 
      * la m�thode � compl�ter. 
      * @param f  la valeur en degr� Fahrenheit
      * @return   la conversion en degr� Celsius
      */
     public static float fahrenheitEnCelsius( int f )
     {
         return 5.0f/9.0f*(f-32);// � compl�ter en rempla�ant 0.F par l'appel de la fonction de conversion
     } // fahrenheitEnCelsius()

} // FahrenheitCelsius
