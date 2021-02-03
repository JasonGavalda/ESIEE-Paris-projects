package tp4.question3;

/** NFP121 question3, tp6
   source Java genere par l'intermediaire d'un visiteur "toJava"
*/
public class Boucle{

  public static void main(String[] args){
    int i=0;
    int j=1;

    for(i = 0;(i < 5);j = (j + 1)){
      i = (i + 1);
    };
    System.out.println(j);
  }

}
