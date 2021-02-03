package tp4.question3;

import tp4.question1.Expression;
import tp4.question1.Variable;

public class Affectation extends Instruction{
  private Variable v;
  private Expression exp;

  public Affectation(Variable v, Expression exp){
    this.v = v;
    this.exp = exp;
  }

  public <T> T accepter(VisiteurInstruction<T> vi){
    return vi.visite(this);
  }
  
  public Expression exp(){ return exp; }
  
  public Variable v(){ return v; }
  
}