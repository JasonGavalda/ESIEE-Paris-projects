package tp4.question3;

/** NFP121/2006 question3, tp6
   source Java genere par l'intermediaire de votre visiteur "VisiteurInstToJava"
*/
public class BoucleFor2{

  public static void main(String[] args)throws Exception{
    int i=0;
    int j=1;

    while(i < 10){
      
      while(j < 10){
        j=(j + 1);
      };
      i=(i + 1);
      j=1;
    }
  }

}

