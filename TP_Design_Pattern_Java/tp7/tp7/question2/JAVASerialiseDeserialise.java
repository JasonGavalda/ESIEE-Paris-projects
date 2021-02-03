package question2;


import tp4.question1.*;
import tp4.question2.*;
import tp4.question3.*;

import java.io.*;

/**
 * D�crivez votre classe JAVASerialiseDeserialise ici.
 * 
 * @author (votre nom) 
 * @version (un num�ro de version ou une date)
 */
public class JAVASerialiseDeserialise
{
      public static void serialjava(IProgr p, String nomDuFichier){
          try
          {
              FileOutputStream fout = new FileOutputStream(nomDuFichier);
              ObjectOutputStream out = new ObjectOutputStream(fout);
              out.writeObject(p);
	  }
	  catch (FileNotFoundException pE)
	  {
	      System.out.println("FileNotFoundException");
	  }
	  catch (IOException pE)
	  {
	      System.out.println("IOException");
	  }
    }

      public static IProgr deserialjava(String nomDuFichier){
          IProgr p = null;
          try
          {
              FileInputStream fin = new FileInputStream(nomDuFichier);
              ObjectInputStream in = new ObjectInputStream(fin);
              p = (IProgr)in.readObject();
          }
          catch (FileNotFoundException pE)
	  {
	      System.out.println("FileNotFoundException");
	  }
          catch (IOException pE)
	  {
	      System.out.println("IOException");
	  }
          catch (ClassNotFoundException pE)
          {
              System.out.println("ClassNotFoundException");
          }
          return p;
    }    
}
