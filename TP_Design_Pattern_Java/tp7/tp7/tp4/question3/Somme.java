package tp4.question3;

/** NFP121 question3, tp6
   source Java genere par l'intermediaire d'un visiteur "toJava"
*/
public class Somme{

  public static void main(String[] args)throws Exception{
    int i=0;
    int n=100;
    int s=0;

    while(i < n){
      i=(i + 1);
      s=(s + i);
    };
    System.out.println(s);;
    
    assert (s == 5050);
  }

}
