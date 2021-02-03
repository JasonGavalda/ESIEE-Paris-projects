package tp4.question3;

/* Classe abstraite Instruction, racine du Composite
 *
 */
public abstract class Instruction implements java.io.Serializable {
  /** accepter un visiteur, 
   * @param vi le Visiteur des instructions
   */
  public abstract <T> T accepter(VisiteurInstruction<T> vi);
  
}