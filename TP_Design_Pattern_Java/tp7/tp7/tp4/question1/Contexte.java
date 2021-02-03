package tp4.question1;
import java.util.Iterator;

public interface Contexte extends Iterable,java.io.Serializable{

  public Integer lire(String adresse);
  public void ecrire(String adresse, Integer valeur);
  
  public Iterator<String> iterator();
  
}
